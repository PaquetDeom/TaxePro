package fr.paquet.dataBase.initData;

import java.io.File;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.paquet.dataBase.Connect;

public class InitDataFactory extends Connect {

	private static InitDataFactory uniqInstance = null;

	/**
	 * 
	 * @param Id
	 * @return les donnees d'une entreprise suivant son id</br>
	 * @throws Exception la donnee n'est pas dans la base</br>
	 */
	public InitData findData(String Id) throws Exception {

		Query query = getEm().createQuery("SELECT initData FROM InitData initData where initData.id=:id");
		query.setParameter("id", Id);
		return (InitData) query.getSingleResult();
	}

	/**
	 * 
	 * @return L'ensemble des donnees de la table</br>
	 * @throws Exception il n'y a pas de donnee dan la base<br/>
	 */
	public List<InitData> findDatas() throws Exception {

		Query query = getEm().createQuery("SELECT initData FROM InitData initData");
		@SuppressWarnings("unchecked")
		List<InitData> initDatas = (List<InitData>) query.getResultList();
		if (initDatas.isEmpty())
			throw new Exception("Il n'y a pas de données dans la base de donnee");
		return initDatas;

	}

	/**
	 * 
	 * @return l'intance unique de la class</br>
	 */
	public static InitDataFactory getUniqInstance() {
		if (uniqInstance == null)
			uniqInstance = new InitDataFactory();
		return uniqInstance;
	}

	/**
	 * 
	 * @param file.csv de Pronote</br>
	 */
	public void loadCsv(File file) {
		
		getEm().createQuery("COPY INITDATA FROM" + file.getAbsolutePath() + "WITH DELIMITER ';'");
		
	}

	/**
	 * supprime la donnée initData</br>
	 * @param initData
	 */
	public void delete(InitData initData) {
		
		EntityTransaction t = getEm().getTransaction();
		try {
			t.begin();
			getEm().remove(initData);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			throw (e);
		}
	
				
	}

}
