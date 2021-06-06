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

	@Test
	public void URL_PARAMETER_FIND() {
		// String url = "http://localhost:8080?encParam=dfjkdfjk&core=23jj43&type=abnormal";
		String url = "encParam=dfjkdfjk&core=23jj43&type=abnormal";
		String encryptText = null;
		String decryptText = null;
		String plainUrl;
		String encryptParameter;

		EncryptNormal encryptNormal;

		try {
			// encode
			encryptNormal = new EncryptNormal();

			int encodeIndex = url.indexOf("?");
			if (encodeIndex == -1) {
				encryptText = encryptNormal.getEncryptText(url);
				/*실제로는 decrypt는 사용 안한다.*/
				decryptText = encryptNormal.getDecryptText(encryptText);
				/**/
			}
			// 실제 적용시 위 if문에 return 처리하고 아래 if는 삭제
			if (encodeIndex >= 0) {
				++encodeIndex;
				plainUrl = url.substring(0, encodeIndex);
				encryptParameter = encryptNormal.getEncryptText(url.substring(encodeIndex));

				StringBuilder urlBuilder = new StringBuilder(plainUrl);
				urlBuilder.append("encParam=").append(encryptParameter);
				encryptText = urlBuilder.toString();

				/*실제로는 decrypt는 사용 안한다.*/
				decryptText = encryptNormal.getDecryptText(encryptParameter);
				urlBuilder = new StringBuilder(plainUrl);
				urlBuilder.append(encryptNormal.getDecryptText(encryptParameter));
				decryptText = urlBuilder.toString();
				/**/
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertThat(encryptText).isNotEmpty();
		assertThat(decryptText).isNotEmpty();
		assertThat(url).isEqualTo(decryptText);

	}

	@Test
	public void 파라미터_암호화() {
		String url = "http://localhost:8080?encParam=dfjkdfjk&core=23jj43&type=abnormal";
		// String url = "encParam=dfjkdfjk&core=23jj43&type=abnormal";
		String encryptText = null;

		try {
			// encode
			encryptText = getEncryptParameter(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertThat(encryptText).isNotEmpty();

	}

	private String getEncryptParameter(String url) throws
		NoSuchPaddingException,
		NoSuchAlgorithmException,
		InvalidKeyException,
		BadPaddingException,
		InvalidAlgorithmParameterException,
		IllegalBlockSizeException {

		EncryptNormal encryptNormal = new EncryptNormal();
		int encodeIndex = url.indexOf("?");
		if (encodeIndex == -1) {
			return encryptNormal.getEncryptText(url);
		}

		final String encryptParameter = encryptNormal.getEncryptText(url.substring(encodeIndex + 1));
		return url.substring(0, encodeIndex + 1) + "encParam=" + encryptParameter;
	}

}
