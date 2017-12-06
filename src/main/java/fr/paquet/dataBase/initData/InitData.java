package fr.paquet.dataBase.initData;

import javax.persistence.*;

@Entity
@Table(name = "INITDATA")
public class InitData {
	
	@Id
	@Column(name = "ININID", length = 10)
	private String id = null;
	
	@Column(name = "ININRA", length = 100)
	private String raisonSociale;
	
	@Column(name = "ININME", length = 50)
	private String metiers;
	
	@Column(name = "ININTA", length = 70)
	private String taxeApprentissage;
	
	@Column(name = "ININAD", length = 150)
	private String adresse;
	
	@Column(name = "ININCP", length = 10)
	private String codePostal;
	
	@Column(name = "ININVI", length = 50)
	private String ville;
	
	@Column(name = "ININMA", length = 100)
	private String Mail;
	
	@Column(name = "ININCI", length = 10)
	private String civilite;
	
	@Column(name = "ININNO", length = 50)
	private String nom;
	
	@Column(name = "ININPR", length = 50)
	private String prenom;
	
	
	public InitData() {
		super();
	}
	
	public int getId() {
		return Integer.parseInt(id);
	}
	
	public String getRaisonSociale() {
		return raisonSociale;
	}
	
	public String getMetiers() {
		return metiers;
	}
	
	public String getTaxeApprentissage() {
		return taxeApprentissage;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public String getMail() {
		return Mail;
	}
	
	public String getCivilite() {
		return civilite;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getAdresse() {
		return adresse;
	}

}
