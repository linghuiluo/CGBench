
package onlinechat.crypto;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyManagment {
	private static int count = 5000;

	public static SecretKey getKey(char[] pwd, byte[] salt) throws GeneralSecurityException {
		PBEKeySpec spec = new PBEKeySpec(pwd, salt, count, 128);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		Key tmpKey = skf.generateSecret(spec);
		SecretKeySpec ret = new SecretKeySpec(tmpKey.getEncoded(), "AES");
		spec.clearPassword();
		return ret;
	}

}
