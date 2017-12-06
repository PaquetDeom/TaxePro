package fr.paquet.io;

import java.io.File;


public abstract class CSVFileIntegration {
	private File file=null;
	
	public CSVFileIntegration(File file) {
		this.setFile(file);
	}
	
	protected File getFile() {
		return file;
	}
	protected void setFile(File file) {
		this.file = file;
	}

		
	public abstract void integre();

}
