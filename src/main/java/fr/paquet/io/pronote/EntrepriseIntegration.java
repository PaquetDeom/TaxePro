package fr.paquet.io.pronote;

import java.io.File;

import fr.paquet.dataBase.initData.InitDataFactory;
import fr.paquet.io.CSVFileIntegration;

public class EntrepriseIntegration extends CSVFileIntegration {

	public EntrepriseIntegration(File file) {
		super(file);
	}

	@Override
	public void integre() {

		InitDataFactory.getUniqInstance().loadCsv(getFile());

	}

}
