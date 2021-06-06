package aes.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
	public static final String PLEASE_CALL_TO_ENCRYPT_METHOD = "Please call to encrypt method";

	Cipher cipher;
	SecretKeySpec secretKeySpec;
	IvParameterSpec ivParameterSpec;

	private String padding;
	private String iv;
	private String plainText;
	private String encryptText;
	private String decryptText;

	public Encrypt(String iv, String padding) {
		this.iv = iv;
		this.padding = padding;
	}

	private void valid() throws InvalidKeyException {
		if (null == this.cipher || null == secretKeySpec || null == ivParameterSpec) {
			throw new InvalidKeyException(PLEASE_CALL_TO_ENCRYPT_METHOD);
		}
	}

	public String getEncryptText() {
		return encryptText;
	}

	public String getDecryptText() {
		return decryptText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public void setEncryptText(String encryptText) {
		this.encryptText = encryptText;
	}

	public void encrypt(final String aesSecretKey) throws
		NoSuchPaddingException,
		NoSuchAlgorithmException {

		cipher = Cipher.getInstance(padding);
		secretKeySpec = new SecretKeySpec(iv.getBytes(), aesSecretKey);
		ivParameterSpec = new IvParameterSpec(iv.getBytes());
	}

	public void encode() throws
		BadPaddingException,
		IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

		valid();

		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		this.encryptText = Base64.getEncoder().encodeToString(encrypted);
	}

	public void decode() throws
		BadPaddingException,
		IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

		valid();

		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] decodedBytes = Base64.getDecoder().decode(encryptText);
		byte[] decrypted = cipher.doFinal(decodedBytes);
		this.decryptText = new String(decrypted, StandardCharsets.UTF_8);
	}

}
