package fr.paquet.entreprise;

import javax.persistence.EntityTransaction;

import fr.paquet.dataBase.Connect;
import fr.paquet.dataBase.initData.InitData;

public class GerantFactory extends Connect {

	private static GerantFactory uniqInstance = null;

	/**
	 * 
	 * @return L'intance unique de la class</br>
	 */
	public static GerantFactory getInstance() {
		if (uniqInstance == null)
			uniqInstance = new GerantFactory();
		return uniqInstance;
	}

	public void save(Gerant gerant) {

		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().persist(gerant);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}

	}

	public void integre(InitData initData) throws Exception {
		save(new Gerant(initData, EntrepriseFactory.getInstance().find(initData.getRaisonSociale())));

	}
}
