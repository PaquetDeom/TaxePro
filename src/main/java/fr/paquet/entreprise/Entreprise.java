package fr.paquet.entreprise;

import java.util.regex.Pattern;
import javax.persistence.*;

import fr.paquet.dataBase.initData.InitData;
import fr.paquet.etablissement.Section;

/**
 * Class qui gere les entreprises</br>
 * 
 * @author Nathanaël
 *
 */

@Entity
@Table(name = "ENTREPRISE")
public class Entreprise {

	@Id
	@GeneratedValue
	@Column(name = "enenid")
	private int id = 0;

	@Column(name = "enensi", length = 20)
	private String siret = null;

	@Enumerated(EnumType.STRING)
	private Statut statut = null;

	@Column(name = "enende", length = 100)
	private String denomination;

	@ManyToOne
	private Activite activite = null;

	@Enumerated(EnumType.STRING)
	private Section section = null;

	@ManyToOne
	private Gerant gerant = null;

	@Column(name = "enenas")
	private boolean assujetiALaTa = false;

	@Column(name = "enenad", length = 150)
	private String adresse = null;

	@Column(name = "enenco", length = 10)
	private String codePostal = null;

	@Column(name = "enencom", length = 50)
	private String commune = null;

	@Column(name = "enenma", length = 100)
	private String mail = null;

	/**
	 * Constructeur pour la gestion de la DB</br>
	 */
	public Entreprise() {
		super();
	}

	/**
	 * Constructeur de la Class</br>
	 * 
	 * @param siret
	 * @param statut
	 * @param denomination
	 * @param assujeti
	 * @throws Exception
	 *             si le siret est invalide</br>
	 */
	public Entreprise(String siret, Statut statut, String denomination, boolean assujeti) throws Exception {
		super();
		setSiret(siret);
		setStatut(statut);
		setDenomination(denomination);
		setAssujeti(assujeti);
	}

	public Entreprise(String siret, Statut statut, String denomination, boolean assujeti, String adresse,
			String codePostal, String commune) throws Exception {
		this(siret, statut, denomination, assujeti);
		setAdresse(adresse);
		setCodePostal(codePostal);
		setCommune(commune);
	}

	public Entreprise(String siret, Statut statut, String denomination, boolean assujeti, String adresse,
			String codePostal, String commune, String mail) throws Exception {
		this(siret, statut, denomination, assujeti, adresse, codePostal, commune);
		setMail(mail);
	}

	public Entreprise(InitData initData, Activite activite) throws Exception {
		super();
		setId(initData.getId());
		setAdresse(initData.getAdresse());
		setAssujetiFromString(initData.getTaxeApprentissage());
		setCodePostal(initData.getCodePostal());
		setCommune(initData.getVille());
		setDenomination(initData.getRaisonSociale());
		setMail(initData.getMail());
		setActivite(activite);
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	private void setActivite(Activite activite) {
		this.activite = activite;

	}

	private void setAssujetiFromString(String taxeApprentissage) {
		if (taxeApprentissage.equals("Est assujetti à la taxe d'apprentissage")) {
			setAssujeti(true);
		} else
			setAssujeti(false);
	}

	private void setDenomination(String denomination) {
		this.denomination = denomination.trim();
	}

	private void setStatut(Statut statut) {
		this.statut = statut;
	}

	private void setSiret(String siret) throws Exception {

		// test si la valeur est nulle ou vide
		if (siret == null || siret.equals(""))
			throw new Exception("Veuillez saisir un code siret");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])", siret);
		if (a == false)
			throw new Exception("Code Ape invalide");

		this.siret = siret.trim();
	}

	/**
	 * 
	 * @return le statut de l'entreprise</br>
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * 
	 * @return le nom de l'entreprise</br>
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * 
	 * @return le N° de siret de l'entreprise</br>
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * 
	 * @return le gerant de l'entreprise</br>
	 */
	public Gerant getGerant() {
		return gerant;
	}

	/**
	 * 
	 * @return l'activite principale de l'entreprise</br>
	 */
	public Activite getActivite() {
		return activite;
	}

	/**
	 * 
	 * @return true si l'entreprise est assujetti a la TA</br>
	 */
	public boolean isAssujeti() {
		return assujetiALaTa;
	}

	private void setAssujeti(boolean assujetiALaTa) {
		this.assujetiALaTa = assujetiALaTa;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) throws Exception {

		// test si la valeur est nulle ou vide
		if (codePostal == null || codePostal.equals(""))
			throw new Exception("Veuillez saisir un code postal");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][0-9][0-9])", codePostal);
		if (a == false)
			throw new Exception("Code postal invalide");

		this.codePostal = codePostal.trim();
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) throws Exception {

		// test si la valeur est nulle ou vide
		if (mail == null || mail.equals(""))
			throw new Exception("Veuillez saisir une adresse mail");

		// test si elle correspond a l'expression reguliere
		// TODO verif regexp
		boolean a = false;
		a = Pattern.matches("(*.@*.*)", mail);
		if (a == false)
			throw new Exception("Adresse mail invalide");

		this.mail = mail.trim();
	}
}
