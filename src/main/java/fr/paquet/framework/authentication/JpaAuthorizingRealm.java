package fr.paquet.framework.authentication;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.authz.permission.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * cette classe gère la connexion entre les données
 * d'authentification/autorisation de la base de données et le moteur SHIRO de
 * gestion des droits
 * 
 * @author bda
 *
 */
public class JpaAuthorizingRealm extends AuthorizingRealm {

	public static final String REALM_NAME = "REALM_AUTHENTICATE";
	public static final int HASH_ITERATIONS = 200;
	
	public JpaAuthorizingRealm() {
		super(JpaSecurityUtil.getCredentialMatcher());
	}
	
	/**
	 * Cette methode charge le username,  
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		final String username = (String) principals.getPrimaryPrincipal();

		final User user = User.findByLogin(username);

		final Set<String> roles = new HashSet<>(user.getRoles().size());
		for (final Role role : user.getRoles()) {
			roles.add(role.getRoleName());
		}

		final Set<org.apache.shiro.authz.Permission> permissions = new HashSet<>(
				user.getPermissions().size());
		for (final Permission permission : user.getPermissions()) {
			permissions.add(new WildcardPermission(permission.getPermission()));
		}

		final SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
		authorizationInfo.setObjectPermissions(permissions);

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (!(token instanceof UsernamePasswordToken)) {
			throw new IllegalStateException("Token has to be instance of UsernamePasswordToken class");
		}

		final UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;

		if (userPassToken.getUsername() == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		final User user = User.findByLogin(userPassToken.getUsername());

		final SimpleAccount simpleAccount = new SimpleAccount(user.getLogin(), user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), REALM_NAME);
		return simpleAccount;
	}

}
