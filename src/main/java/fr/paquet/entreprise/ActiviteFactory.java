package fr.paquet.entreprise;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;
import fr.paquet.dataBase.initData.InitData;

public class ActiviteFactory extends Connect {

	private static ActiviteFactory uniqInstance = null;

	/**
	 * 
	 * @return l'instance unoque de la class</br>
	 */
	public static ActiviteFactory getInstance() {
		if (uniqInstance == null)
			uniqInstance = new ActiviteFactory();
		return uniqInstance;
	}

	public void save(Activite act) {
		EntityTransaction t = getEm().getTransaction();

		try {
			t.begin();
			getEm().persist(act);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	}

	public void integre(InitData initData) {
		try {
			find(initData.getMetiers());
		} catch (Exception e) {
			save(new Activite(initData));
		}

	}

	public Activite find(String metiers) {

		Query query = getEm().createQuery("SELECT act FROM Activite act where initData.metiers=:metiers");
		query.setParameter("metiers", metiers);
		return (Activite) query.getSingleResult();
	}

}
