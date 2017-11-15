package fr.paquet.framework.authentication;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import fr.paquet.framework.ProgItem;
import fr.paquet.framework.ui.Ihm;

@Ihm(libelle = "Roles")
@Entity
@Table(name = "XROL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AttributeOverrides({ @AttributeOverride(name = "topSup", column = @Column(name = "XRXRSUP")),
		@AttributeOverride(name = "createUser", column = @Column(name = "XRXCRTUSR")),
		@AttributeOverride(name = "createOrigin", column = @Column(name = "XRXCRTPGM")),
		@AttributeOverride(name = "createDate", column = @Column(name = "XRXCRTDAT")),
		@AttributeOverride(name = "modifyUser", column = @Column(name = "XRXMAJUSR")),
		@AttributeOverride(name = "modifyOrigin", column = @Column(name = "XRXMAJPGM")),
		@AttributeOverride(name = "modifyDate", column = @Column(name = "XRXMAJDAT")),
		@AttributeOverride(name = "deleteUser", column = @Column(name = "XRXSUPUSR")),
		@AttributeOverride(name = "deleteOrigin", column = @Column(name = "XRXSUPPGM")),
		@AttributeOverride(name = "deleteDate", column = @Column(name = "XRXSUPDAT")) })
public class Role extends ProgItem implements Comparable<Role> {

	@Id
	@GeneratedValue
	@Column(name = "XRXRID")
	private int id;

	@NotNull
	@Column(name = "XRXRROL", length = 20)
	@Ihm(ordre = 2, grid = "Role", form = "Role", description = "Veuillez renseigner le Role")
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {
		super();
	}

	public Role(String RoleName) {
		this();
		setRoleName(RoleName);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int compareTo(Role o) {
		// TODO Auto-generated method stub
		return getRoleName().compareTo(o.getRoleName());
	}
}