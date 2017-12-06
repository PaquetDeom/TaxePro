package fr.paquet.entreprise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import fr.paquet.dataBase.initData.InitData;

@Entity
@Table(name = "GERANT")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "GEGEID")),
		@AttributeOverride(name = "nom", column = @Column(name = "GEGENOM", length = 20)),
		@AttributeOverride(name = "prenom", column = @Column(name = "GEGEPRENOM", length = 50)),
		@AttributeOverride(name = "masculin", column = @Column(name = "GEGEMA")) })
public class Gerant extends Personne {

	@OneToMany(fetch = FetchType.LAZY)
	private List<Entreprise> entreprises = null;

	/**
	 * Constructeur vide pour la gestion de la DB</br>
	 */
	public Gerant() {
		super();
	}

	/**
	 * Constructeur de la class<br/>
	 * 
	 * @param id
	 */
	public Gerant(int id, Entreprise entreprise) {
		this();
		setId(id);
		addEntreprise(entreprise);
	}

	public Gerant(InitData initData, Entreprise entreprise) {
		super();
		setNom(initData.getNom());
		setPrenom(initData.getPrenom());
		setMasculinFromCivilite(initData.getCivilite());
		addEntreprise(entreprise);
	}

	/**
	 * 
	 * @return la liste des entreprises geree</br>
	 */
	public List<Entreprise> getEntreprises() {
		if (entreprises == null)
			entreprises = new ArrayList<Entreprise>();
		return entreprises;
	}

	public void addEntreprise(Entreprise entreprise) {
		getEntreprises().add(entreprise);
	}

}
