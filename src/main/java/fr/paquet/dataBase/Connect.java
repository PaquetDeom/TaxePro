package fr.paquet.dataBase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {

	/**
	 * @author Nathanaël
	 * 
	 *         Connection à la base de donnée<br/>
	 */

	private static EntityManagerFactory emf = null;

	/**
	 * 
	 * @return la connection a la base de donnee "progress"<br/>
	 */
	public static EntityManagerFactory getEmf() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("TaxePro");
		if (emf.isOpen())
			return emf;
		return emf;

	}
}
