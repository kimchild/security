package aes.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class EncryptNormal extends Encrypt {
	public final String AES_SECRET_KEY = "AES"; // AES or Rijndael

	public EncryptNormal() {
		super("0123456789012345", "AES/CBC/PKCS5Padding");
	}

	public void encrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
		super.encrypt(AES_SECRET_KEY);
	}

}
