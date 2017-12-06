package fr.paquet.entreprise;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {

	@Id
	@GeneratedValue
	@Column(name = "PEPEID")
	private int id = 0;

	@Column(name = "ENENNO", length = 50)
	private String nom = null;

	@Column(name = "ENENPR", length = 50)
	private String prenom = null;

	@Column(name = "ENENMA")
	private boolean masculin = true;

	public Personne() {
		super();
	}

	public Personne(boolean masculin, String nom, String prenom) {
		super();
		setMasculin(masculin);
		setNom(nom);
		setPrenom(prenom);
	}

	protected void setNom(String nom) {
		this.nom = nom.trim().toUpperCase();
	}

	protected void setPrenom(String prenom) {
		this.prenom = prenom.trim();
	}

	/**
	 * 
	 * @return le nom en majuscule san espace a droite et a gauche</br>
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * 
	 * @return le prenom sans espace a droite et a gauche</br>
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * 
	 * @return true si la personne est un homme</br>
	 */
	public boolean isMasculin() {
		return masculin;
	}

	protected void setMasculin(boolean masculin) {
		this.masculin = masculin;
	}

	protected void setMasculinFromCivilite(String civilite) {
		if (civilite.equals("Mme.") || civilite.equals("Me") || civilite.equals("Mlle")) {
			setMasculin(false);
		} else
			setMasculin(true);
	}

	/**
	 * 
	 * @return l'id pour la gestion de la DB</br>
	 */
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}
}
