package aes.utils;

import static org.assertj.core.api.Assertions.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

public class EncryptTest {

	@Test
	public void 복호화테스트() throws
		NoSuchPaddingException,
		InvalidKeyException,
		NoSuchAlgorithmException,
		IllegalBlockSizeException,
		BadPaddingException,
		InvalidAlgorithmParameterException {

		final String plainText = "암호화 텍스트";

		EncryptNormal encryptNormal = new EncryptNormal();
		final String encryptText = encryptNormal.getEncryptText(plainText);
		System.out.println("value : " + encryptText);

		System.out.println("value : " + encryptNormal.getDecryptText(encryptText));
		System.out.println(plainText);
		assertThat(plainText).isEqualTo(encryptNormal.getDecryptText(encryptText));
	}

	@Test
	public void 복호화예외처리테스트() {

		final String plainText = "암호화 텍스트";
		String decryptText = null;

		EncryptNormal encryptNormal;

		try {
			// encode
			encryptNormal = new EncryptNormal();
			final String encryptText = encryptNormal.getEncryptText(plainText);
			System.out.println("value : " + encryptText);

			// decode
			decryptText = encryptNormal.getDecryptText(encryptText);
			System.out.println("value : " + encryptNormal.getDecryptText(encryptText));
			System.out.println(plainText);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("value : " + decryptText);
		System.out.println(plainText);
		assertThat(plainText).isEqualTo(decryptText);
	}
}
