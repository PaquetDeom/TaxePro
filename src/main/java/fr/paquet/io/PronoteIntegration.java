package fr.paquet.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.paquet.ihm.importCsv.CSVFiles;


public class PronoteIntegration {

	/**
	 * @author Nathanaël
	 * 
	 * 
	 *         Class qui gere tous les fichiers siecle<br/>
	 * 
	 * 
	 */
	private static DocumentBuilderFactory factory = null;
	private static DocumentBuilder builder = null;
	private CSVFiles file = null;
	private Path path = null;
	

	public PronoteIntegration(CSVFiles file, String path) {
		super();
		setFile(file);
		setPath(path);
	}

		
	/**
	 * 
	 * @return une ArrayList Hashtable de fileName et Document<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public Document getDocument() throws SAXException, IOException, ParserConfigurationException {
		File file = new File(getPath().toString());
		return getDocumentBuilder().parse(file);
	}

	/**
	 * 
	 * @return le DocumentBuilderFactory<br/>
	 */
	private static DocumentBuilderFactory getDocumentBuilderFactory() {
		if (factory == null)
			factory = DocumentBuilderFactory.newInstance();
		return factory;
	}

	/**
	 * 
	 * @return Le DocumentBuilder<br/>
	 * @throws ParserConfigurationException
	 *             si la source est fausse<br/>
	 */
	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (builder == null)
			builder = getDocumentBuilderFactory().newDocumentBuilder();
		return builder;
	}

	public CSVFiles getFile() {
		return file;
	}

	public void setFile(CSVFiles file) {
		this.file = file;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(String path) {
		Path path0 = Paths.get(path);
		this.path = path0;
	}

	public void integre() throws Exception {
		CSVFileIntegration integrator = file.getIntegrator().newInstance();
		integrator.integre();
	}

}
