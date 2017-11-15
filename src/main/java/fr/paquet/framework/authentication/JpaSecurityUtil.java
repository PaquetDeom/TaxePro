package fr.paquet.framework.authentication;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public final class JpaSecurityUtil {
	public static final int SALT_LENGTH = 80;
	public static final int PASSWORD_LENGTH = 64;

	public static String getSalt() {
		return new SecureRandomNumberGenerator().nextBytes(60).toBase64();
	}

	public static String hashPassword(final String value, final String salt) {
		final Sha256Hash sha256Hash = new Sha256Hash(value, salt, JpaAuthorizingRealm.HASH_ITERATIONS);
		return sha256Hash.toHex();
	}

	public static CredentialsMatcher getCredentialMatcher() {
		final HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		credentialsMatcher.setHashIterations(JpaAuthorizingRealm.HASH_ITERATIONS);
		return credentialsMatcher;
	}

	public SimpleHash getHash() {
		final Sha256Hash sha256Hash = new Sha256Hash();
		sha256Hash.setIterations(JpaAuthorizingRealm.HASH_ITERATIONS);
		return sha256Hash;
	}
}
