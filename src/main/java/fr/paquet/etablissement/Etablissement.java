package fr.paquet.etablissement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ETABLISSEMENT")
public class Etablissement {

	@Id
	@Column(name = "ETETID", length = 20)
	private String codeRNE = null;

	@Column(name = "ETETDENOMINATION_PRINCIPALE", length = 200)
	private String denominationPrincipale = null;

	@Enumerated(EnumType.STRING)
	private List<Section> sections = null;

	public Etablissement(String rne) {
		this();
		setCodeRNE(rne);
	}

	/**
	 * Constructeur vide pour la gestion de la DB<br/>
	 */
	public Etablissement() {
		super();
	}

	public void setCodeRNE(String rne) {
		this.codeRNE = rne.trim().toUpperCase();

	}

	public void setDenominationPrincipale(String denominationPrincipale) throws Exception {
		if (denominationPrincipale == null)
			throw new Exception("Denomination non saisi");
		this.denominationPrincipale = denominationPrincipale.trim();
	}

	public void addSection(Section section) {
		getSections().add(section);
	}

	/**
	 * 
	 * @return Le code RNE de l'etablissement sans espace a droite et a gauche et en
	 *         majuscule<br/>
	 *         est l'id pour la gestion de la DB<br/>
	 */
	public String getCodeRne() {
		return codeRNE;
	}

	/**
	 * 
	 * @return denimination sans espace a droite et a gauche<br/>
	 * @throws si
	 *             denomination est null<br/>
	 */
	public String getDenominationPrincipale() {
		return denominationPrincipale;
	}

	/**
	 * 
	 * @return la liste de sections d'un etablissement scolaire<br/>
	 */
	public List<Section> getSections() {
		if (sections == null)
			sections = new ArrayList<Section>();
		return sections;
	}

}
