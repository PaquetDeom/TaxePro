package fr.paquet.framework.authentication;

import fr.paquet.framework.ProgItem_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-06T11:57:22.968+0100")
@StaticMetamodel(User.class)
public class User_ extends ProgItem_ {
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> salt;
	public static volatile SingularAttribute<User, String> nom;
	public static volatile SingularAttribute<User, String> prenom;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile ListAttribute<User, Permission> permissions;
}
