package fr.paquet.ihm.importCsv;

import java.util.EnumSet;

import fr.paquet.io.CSVFileIntegration;
import fr.paquet.io.pronote.EntrepriseIntegration;



public enum CSVFiles implements Comparable<CSVFiles> {

	ENTREPRISE;

	public static CSVFiles getDocument(String fileName) {
		for (CSVFiles file : EnumSet.allOf(CSVFiles.class)) {
			if (file.fileName().toUpperCase().equals(fileName.toUpperCase()))
				return file;
		}
		return null;
	}

	public String fileName() {
		switch (this) {
		case ENTREPRISE:
			return "entreprise.csv";
		}
		return null;
	}
	
	public Class<? extends CSVFileIntegration> getIntegrator() {
		switch (this) {
		case ENTREPRISE:
			return EntrepriseIntegration.class;
		}
		return null;
	}

}