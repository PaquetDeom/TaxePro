package fr.paquet.framework.authentication;

import fr.paquet.framework.ProgItem_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-06T11:53:39.913+0100")
@StaticMetamodel(Permission.class)
public class Permission_ extends ProgItem_ {
	public static volatile SingularAttribute<Permission, Integer> id;
	public static volatile SingularAttribute<Permission, String> permission;
	public static volatile SingularAttribute<Permission, User> user;
}
