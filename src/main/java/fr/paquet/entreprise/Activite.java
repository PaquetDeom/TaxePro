package fr.paquet.entreprise;

import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.*;

import fr.paquet.dataBase.initData.InitData;

@Entity
@Table(name = "ACTIVITE")
public class Activite {

	/**
	 * @author Nathanaël
	 * 
	 *         Class qui gere l'activite d'une entreprise</br>
	 */

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id = 0;
	
	@Column(name = "acacco", length = 10)
	private String codeApe = null;

	@Column(name = "acacac", length = 50)
	private String activite = null;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Entreprise> entreprises = null;

	/**
	 * Constructeur vide pour la gestion de la DB</br>
	 */
	public Activite() {
		super();
	}

	/**
	 * Constructeur de la Classe
	 * 
	 * @param codeApe
	 * @throws Exception 
	 */
	public Activite(String codeApe, String activite) throws Exception {
		this();
		setCodeApe(codeApe);
		setActivite(activite);
	}

	public Activite(InitData initData) {
		super();
		setActivite(initData.getMetiers());
	}

	private void setId(int id) {
		this.id = id;
		
	}
	
	public int getId() {
		return id;
	}

	private void setActivite(String activite) {
		this.activite = activite.trim();

	}

	private void setCodeApe(String codeApe) throws Exception {

		// test si la valeur est nulle ou vide
		if (codeApe == null || codeApe.equals(""))
			throw new Exception("Veuillez saisir un code Ape");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][A-Z])", codeApe);
		if (a == false)
			throw new Exception("Code Ape invalide");

		this.codeApe = codeApe.trim();
	}
	
	/**
	 * 
	 * @return une activite sans espace a droite et a gauche</br>
	 */
	public String getActivite() {
		return activite;
	}
	
	/**
	 * 
	 * @return un code Ape sans espace a droite et a gauche</br>
	 */
	public String getCodeApe() {
		return codeApe;
	}

}
