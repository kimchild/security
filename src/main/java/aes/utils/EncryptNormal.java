package aes.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptNormal {
	// AES or Rijndael
	public static final String AES_SECRET_KEY = "AES";
	public static final String IV = "0123456789012345";
	/*
	   Java only use PCS5Padding and PCS5Padding is same PKCS7Padding of machanism
	   https://crypto.stackexchange.com/questions/9043/what-is-the-difference-between-pkcs5-padding-and-pkcs7-padding
	 */
	public static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";

	private Encrypt encrypt;

	public EncryptNormal() throws NoSuchAlgorithmException, NoSuchPaddingException {
		encrypt = new Encrypt(IV, AES_CBC_PKCS_5_PADDING);
		this.initalizeEncrypt();
	}

	private void initalizeEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
		encrypt.encrypt(AES_SECRET_KEY);
	}

	public String getEncryptText(String plainText) throws
		IllegalBlockSizeException,
		InvalidKeyException,
		InvalidAlgorithmParameterException,
		BadPaddingException {

		encrypt.setPlainText(plainText);
		encrypt.encode();
		return encrypt.getEncryptText();
	}

	public String getDecryptText(String encryptText) throws
		IllegalBlockSizeException,
		InvalidKeyException,
		InvalidAlgorithmParameterException,
		BadPaddingException {

		encrypt.setEncryptText(encryptText);
		encrypt.decode();
		return encrypt.getDecryptText();
	}

}
