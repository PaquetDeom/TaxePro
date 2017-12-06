package fr.paquet.etablissement;

import java.util.EnumSet;

public enum Section {
	
	BOIS, ELECTRICITE, PLOMBERIE, FINITION, ALUVERRE, DTMS, TEBEE, TEBAA, MACON;
	
	public static Section getSections(String section) throws Exception {
		for (Section sec : EnumSet.allOf(Section.class)) {
			if(sec.sectionName().trim().toUpperCase() == section.trim().toUpperCase())
				return sec;
		}
		throw new Exception("Section invalide");
		}

	private String sectionName() {
		switch(this) {
		case BOIS :
			return "Pôle bois";
		case ELECTRICITE :
		return "Pôle Electricité";
		case PLOMBERIE :
			return "Pôle installateur sanitaire";
		case FINITION :
			return "Pôle aménagement finition";
		case ALUVERRE :
			return "Pôle meunuiserie alu-verre";
		case DTMS :
			return "Pôle métier du spectacle";
		case TEBEE :
			return "Pôle Assistant d'architecte";
		case TEBAA :
			return "pôle Assistant d'architecte";
		case MACON :
			return "Pôle gros oeuvre";
		}
		return null;
	}

}
