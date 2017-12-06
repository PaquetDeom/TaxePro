package fr.paquet.entreprise;

import java.util.EnumSet;

public enum Statut implements Comparable<Statut> {

	SARL, EURL, SA, SAS, SCOP, EI, SNC, SCP;

	public static Statut getStatut(String statut) throws Exception {

		for (Statut sta : EnumSet.allOf(Statut.class)) {
			if (sta.statutName().toUpperCase().equals(statut.toUpperCase()))
				return sta;
		}
		throw new Exception("Satut invalide");
	}

	public String statutName() {
		switch (this) {
		case SARL:
			return "SARL";
		case EURL:
			return "EURL";
		case SA:
			return "SA";
		case SAS:
			return "SAS";
		case SCOP:
			return "SCOP";
		case EI:
			return "Entreprise individuelle";
		case SNC:
			return "SNC";
		case SCP:
			return "SCP";
		}
		return null;
	}

}
