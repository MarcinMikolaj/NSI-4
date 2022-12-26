package encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public interface AES {

	// This method allow generating a secret key for AES (generating from a random number).
	// Return SecretKey.
	public SecretKey generateKey(int keySize) throws NoSuchAlgorithmException;
	
	public String convertSecretKeyToString(SecretKey secretKey);
	
	// secretKey as base64
	public SecretKey converStringToSecretKey(String secretKey);
	
	// Initialization vector is a pseudo-random value and has the same size as the block that is encrypted.
	public IvParameterSpec genrateInitializationVector();
	
	// Encrypt.
	// Return encrypted input as Base64.
	public String encryptString(String input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	// Decrypt.
	// Expects input in Base64.
	public String decryptString(String input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public byte[] encryptFile(String inputPathToFile, String outputPathToFile, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException;
	public byte[] decryptFile(String inputPathToFile, String outputPathToFile, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException;
}
