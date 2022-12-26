package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class AESimplTest {
	
	@Test
	void givenTextAfterEcpryptAndDecryptShouldBeTheSame() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		//given
		AES aes = new AESimpl();
		SecretKey secretKey;
		IvParameterSpec ivParameterSpec;
		String encrypted;
		String decrypted;
		String expected = "Hello my AES algorithm !";
		
		//when
		secretKey = aes.generateKey(128);
		ivParameterSpec = aes.genrateInitializationVector();
		encrypted = aes.encryptString(expected, secretKey, ivParameterSpec);
		decrypted = aes.decryptString(encrypted, secretKey, ivParameterSpec);
		
		//then
		assertEquals(decrypted, expected);
		
	}

}
