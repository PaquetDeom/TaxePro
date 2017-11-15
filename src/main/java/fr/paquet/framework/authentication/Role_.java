package fr.paquet.framework.authentication;

import fr.paquet.framework.ProgItem_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-06T11:53:39.933+0100")
@StaticMetamodel(Role.class)
public class Role_ extends ProgItem_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SingularAttribute<Role, String> roleName;
	public static volatile SetAttribute<Role, User> users;
}
