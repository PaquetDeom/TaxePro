package fr.paquet.ihm.importCsv;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.vaadin.server.VaadinService;

import fr.paquet.dataBase.initData.InitData;
import fr.paquet.dataBase.initData.InitDataFactory;
import fr.paquet.entreprise.ActiviteFactory;
import fr.paquet.entreprise.EntrepriseFactory;
import fr.paquet.entreprise.GerantFactory;
import fr.paquet.io.PronoteIntegration;

public class PronoteImport {

	public interface PronoteImportChangedListener {
		public void PronoteImportChanged();
	}

	/**
	 * 
	 * @return Le répertoire d'accueil des fichiers Pronote<br/>
	 */
	private static Path getPathFolder() {
		return Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/");
	}

	/**
	 * 
	 * @return le chemin d'accés au repertoire d'import<br/>
	 */
	public Path getPathFolderImport() {
		Path path = Paths.get(getPathFolder().toString() + "/entreprise/");
		return path;
	}

	public void createDirectories() throws Exception {

		// verifie si le repertoire existe si non cree
		File file = new File(getPathFolder().toString() + "/entreprise");
		if (!file.exists()) {
			Files.createDirectories(getPathFolderImport());
			System.out.println("Repertoire créé " + getPathFolderImport().toString());
		}
	}

	private static PronoteImport getImportFromDirectory() throws Exception {
		PronoteImport import0 = new PronoteImport();
		// verifie si le repertoire existe si non cree
		File Directory = new File(getPathFolder().toString() + "/entreprise");
		if (!Directory.exists())
			import0.createDirectories();
		return import0;
	}

	public ArrayList<CSVFiles> getDirectoryContaint() throws IOException {
		ArrayList<CSVFiles> directoryContaint = new ArrayList<CSVFiles>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(getPathFolderImport(), "*.csv");
		try {
			Iterator<Path> iterator = stream.iterator();
			while (iterator.hasNext()) {
				Path p = iterator.next();
				for (CSVFiles file : EnumSet.allOf(CSVFiles.class)) {
					if (p.toFile().getName().toUpperCase().equals(file.fileName().toUpperCase()))
						directoryContaint.add(file);
				}
			}
		} finally {
			stream.close();
		}
		return directoryContaint;
	}

	public Boolean hasDocument(CSVFiles file) throws IOException {
		return getDirectoryContaint().contains(file);
	}

	public boolean addDocument(File file) {
		CSVFiles doc = CSVFiles.getDocument(file.getName());
		return (doc != null);
	}

	public void deleteFile(String fileName) throws Exception {

		Path path = Paths.get(getPathFolderImport().toString() + fileName);

		try {

			Files.delete(path);

		} catch (NoSuchFileException nsfe) {
			nsfe.printStackTrace();
			System.err.println("Fichier ou repertoire " + path + " n'existe pas");

		} catch (DirectoryNotEmptyException dnee) {
			dnee.printStackTrace();
			System.err.println("Le repertoire " + path + " n'est pas vide");

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Impossible de supprimer " + path + " : " + ioe);

		}

	}

	// ** ajout du fichier dans la liste des fichiers si et seulement si le fichier
	// est autorisé
	public File createFile(String fileName) throws Exception {
		// recupéré le code document si autorisé
		CSVFiles doc = CSVFiles.getDocument(fileName);
		if (doc == null)
			throw (new Exception("Fichier non admis !!"));
		File file = getFile(fileName);
		file.createNewFile();
		System.out.println("Creation du fichier " + file.getAbsolutePath());
		fireChangeEvent();
		return file;
	}

	private void fireChangeEvent() {
		for (PronoteImportChangedListener listener : listeners) {
			listener.PronoteImportChanged();
		}
	}

	private File getFile(String fileName) throws Exception {
		return new File(getPathFolderImport().toString() + "/" + fileName);
	}

	ArrayList<PronoteImportChangedListener> listeners = null;

	public void addChangeListener(PronoteImportChangedListener documentCheckbox) {
		if (listeners == null)
			listeners = new ArrayList<PronoteImportChangedListener>();
		listeners.add(documentCheckbox);
	}

	public boolean isAllLoaded() throws Exception {
		for (CSVFiles doc : EnumSet.allOf(CSVFiles.class)) {
			if (!hasDocument(doc))
				return false;
		}
		return true;
	}

	/**
	 * cette methode integre l'ensemble des fichiers du RNE
	 */
	public void integre() throws Exception {
		for (CSVFiles file : EnumSet.allOf(CSVFiles.class)) {
			new PronoteIntegration(file, getPathFolderImport() + "/" + file.fileName()).integre();
		}
	}

	/**
	 * Charge les tables de la DB</br>
	 * 
	 * @param initDatas
	 * @throws Exception
	 */
	public void chargeTables(List<InitData> initDatas) {
		
		try {
			
			for (InitData initData : initDatas) {
				EntrepriseFactory.getInstance().integre(initData);
				ActiviteFactory.getInstance().integre(initData);
				GerantFactory.getInstance().integre(initData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * Suppression de l'ensemble des donnee de la table INITDATA</br>
	 * @param findDatas
	 */
	public void deleteTable(List<InitData> findDatas) {
		for (InitData initData : findDatas) {
			InitDataFactory.getUniqInstance().delete(initData);
		}
	}
}
