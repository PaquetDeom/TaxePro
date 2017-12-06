package fr.paquet.framework.authentication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import fr.paquet.framework.TaItem;
import fr.paquet.framework.ui.Ihm;

@Ihm(libelle = "Permissions")
@Entity
@Table(name = "XPRM")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AttributeOverrides({ @AttributeOverride(name = "topSup", column = @Column(name = "XPXPSUP")),
		@AttributeOverride(name = "createUser", column = @Column(name = "XPXCRTUSR")),
		@AttributeOverride(name = "createOrigin", column = @Column(name = "XPXCRTPGM")),
		@AttributeOverride(name = "createDate", column = @Column(name = "XPXCRTDAT")),
		@AttributeOverride(name = "modifyUser", column = @Column(name = "XPXMAJUSR")),
		@AttributeOverride(name = "modifyOrigin", column = @Column(name = "XPXMAJPGM")),
		@AttributeOverride(name = "modifyDate", column = @Column(name = "XPXMAJDAT")),
		@AttributeOverride(name = "deleteUser", column = @Column(name = "XPXSUPUSR")),
		@AttributeOverride(name = "deleteOrigin", column = @Column(name = "XPXSUPPGM")),
		@AttributeOverride(name = "deleteDate", column = @Column(name = "XPXSUPDAT")) })
public class Permission extends TaItem {

	@Id
	@GeneratedValue
	@Column(name = "XPXPID")
	private int id;

	@NotNull
	@Column(name = "XPXPPRM", length = 100)
	@Ihm(ordre = 2, grid = "Permission", form = "Permission", description = "Veuillez renseigner la permission")
	private String permission;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@ManyToOne()
	@JoinColumn(name = "USER_ID")
	private User user;

	// getters and setters omitted
}