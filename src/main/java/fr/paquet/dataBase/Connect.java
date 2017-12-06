package fr.paquet.dataBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connect {

	/**
	 * @author Nathanaël
	 * 
	 *         Connection à la base de donnée<br/>
	 */

	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;

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

	/**
	 * @author Nathanaël
	 * 
	 *         Classe mére de toutes les class (Factory) du package référentiel<br/>
	 */
	public static EntityManager getEm() {
		if (em == null)
			em = getEmf().createEntityManager();
		return em;
	}
}
