package fr.paquet.framework;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@MappedSuperclass
public class ProgItem {
	@Column(name="topSup", length=1)
	private String topSup="N";
	
	
	//**********************************************************************//
	//** MOUCHARD DE CREATION                                               //
	//**********************************************************************//
	/** 
	 * User de création
	 */
	@Column(name="createUser")
	private String createUser=null;

	/**
	 * Chaine identifiant l'origine de la création
	 */
	@Column(name="createOrigin")
	private String createOrigin=null;

	/**
	 * Date de création
	 */
	@Column(name="createDate")
	@Temporal(TemporalType.DATE)
	private Date createDate=null;
	
	//**********************************************************************//
	//** MOUCHARD DE MODIFICATION                                           //
	//**********************************************************************//
	/**
	 * User de modification
	 */
	@Column(name="modifyUser")
	private String modifyUser=null;

	/**
	 * Chaine identifiant l'origine de la modification
	 */
	@Column(name="modifyOrigin")
	private String modifyOrigin=null;
	
	/**
	 * Date de modification
	 */
	@Column(name="modifyDate")
	@Temporal(TemporalType.DATE)
	private Date modifyDate=null;
	
	//**********************************************************************//
	//** MOUCHARD DE SUPPRESSION                                            //
	//**********************************************************************//
	 /**
	  * User de suppression
	  */
	@Column(name="deleteUser")
	private String deleteUser=null;

	/**
	 * Chaine identifiant l'origine de la modification
	 */
	@Column(name="modifyOrigin")
	private String deleteOrigin=null;
	
	/**
	 * Date de suppression
	 */
	@Column(name="deleteDate")
	@Temporal(TemporalType.DATE)
	private Date deleteDate=null;

	public String getTopSup() {
		return topSup;
	}

	/**
	 * renseigne le coockie de création 
	 * @param origin : chaine permetttant de determiner quel ecran/methode à générer la création
	 */
	public void setCreateCoockie(String origin) {
		setCreateDate(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		setCreateUser((currentUser.getPrincipal()==null?"null":currentUser.getPrincipal().toString()));
		setCreateOrigin(origin);
		setModifyDate(null);
		setModifyUser("");
		setModifyOrigin("");
	}
	
	/**
	 * renseigne le coockie de Modification 
	 * @param origin : chaine permetttant de determiner quel ecran/methode à générer la création
	 */
	public void setModifyCoockie(String origin) {
		setModifyDate(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		setModifyUser(currentUser.toString());
		setModifyOrigin(origin);
	}

	/**
	 * renseigne le coockie de Suppression 
	 * @param origin : chaine permetttant de determiner quel ecran/methode à générer la création
	 */
	public void setDeleteCoockie(String origin) {
		setTopSup("O");
		setDeleteDate(new Date());
		Subject currentUser = SecurityUtils.getSubject();
		setDeleteUser(currentUser.toString());
		setDeleteOrigin(origin);
	}

	
	public void setTopSup(String topSup) {
		this.topSup = topSup;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getCreateOrigin() {
		return createOrigin;
	}

	public void setCreateOrigin(String createOrigin) {
		this.createOrigin = createOrigin;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyOrigin() {
		return modifyOrigin;
	}

	public void setModifyOrigin(String modifyeOrigin) {
		this.modifyOrigin = modifyeOrigin;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public String getDeleteOrigin() {
		return deleteOrigin;
	}

	public void setDeleteOrigin(String deleteOrigin) {
		this.deleteOrigin = deleteOrigin;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	
}
