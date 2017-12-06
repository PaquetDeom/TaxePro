package fr.paquet.entreprise;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;
import fr.paquet.dataBase.initData.InitData;

public class EntrepriseFactory extends Connect {

	private static EntrepriseFactory uniqInstance = null;

	/**
	 * 
	 * @return l'instance unique de la class</br>
	 */
	public static EntrepriseFactory getInstance() {
		if (uniqInstance == null)
			uniqInstance = new EntrepriseFactory();
		return uniqInstance;
	}
	
	public void save(Entreprise entreprise) {

		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(entreprise);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}

	}

	public void integre(InitData initData) throws Exception {
		save(new Entreprise(initData, ActiviteFactory.getInstance().find(initData.getMetiers())));

	}

	/**
	 * 
	 * @param rs
	 * @return une entreprise suivant sa raison sociale</br>
	 * @throws Exception
	 */
	public Entreprise find(String rs) throws Exception{

		Query query = getEm().createQuery("SELECT ent FROM Entreprise ent where initData.raisonSocial=:rs");
		query.setParameter("rs", rs);
		return (Entreprise) query.getSingleResult();
	}

}
