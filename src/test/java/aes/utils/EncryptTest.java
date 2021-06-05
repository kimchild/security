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
		encryptNormal.setPlainText(plainText);
		encryptNormal.encrypt();
		encryptNormal.encode();

		final String encryptText = encryptNormal.getEncryptText();
		System.out.println("value : " + encryptText);

		encryptNormal.setEncryptText(encryptText);
		encryptNormal.decode();
		System.out.println("value : " + encryptNormal.getDecryptText());
		System.out.println(plainText);
		assertThat(plainText).isEqualTo(encryptNormal.getDecryptText());
	}

	@Test
	public void 복호화예외처리테스트() {

		final String plainText = "암호화 텍스트";

		EncryptNormal encryptNormal = new EncryptNormal();
		encryptNormal.setPlainText(plainText);


		// encode
		try {
			encryptNormal.encrypt();
			encryptNormal.encode();

			final String encryptText = encryptNormal.getEncryptText();

			// decode
			encryptNormal.setEncryptText(encryptText);
			encryptNormal.decode();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("value : " + encryptNormal.getDecryptText());
		System.out.println(plainText);
		assertThat(plainText).isEqualTo(encryptNormal.getDecryptText());
	}
}
