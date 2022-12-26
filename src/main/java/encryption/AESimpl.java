package encryption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESimpl implements AES {
	
	private final static String standard = "AES";
	private final static String algorithm = "AES/CBC/PKCS5Padding";

	// This method allow generating a secret key for AES (generating from a random number).
	// Return SecretKey.
	public SecretKey generateKey(int keySize) throws NoSuchAlgorithmException {
		
		SecretKey key;
		KeyGenerator keyGenerator;
		
		keyGenerator = KeyGenerator.getInstance(standard);
		keyGenerator.init(keySize);
		key = keyGenerator.generateKey();
		
		return key;
	}
	
	public String convertSecretKeyToString(SecretKey secretKey) {
		byte[] rawData = secretKey.getEncoded();
	    String encodedKey = Base64.getEncoder().encodeToString(rawData);
	    return encodedKey;
	}
	

	// Initialization vector is a pseudo-random value and has the same size as the block that is encrypted.
	public IvParameterSpec genrateInitializationVector() {
		
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		
		return new IvParameterSpec(iv);
	}
	
	public SecretKey converStringToSecretKey(String secretKey) {
		byte[] decodedKey = Base64.getDecoder().decode(secretKey);
	    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, standard);
	    return originalKey;
	}
	
	
    public byte[] encrypt(byte[] input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher;
		
		cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
	
		return cipher.doFinal(input);
		
	}
	
	
	public byte[] decrypt(byte[] input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher;
		
		cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		
		return cipher.doFinal(input);
		
	}


	// Encrypt String.
	// Return encrypted input as Base64.
	public String encryptString(String input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] output = encrypt(input.getBytes(), secretKey, ivParameterSpec);
		
		return Base64.getEncoder().encodeToString(output);
	}
	
	
	// Decrypt String.
	// Expects input in Base64.
	public String decryptString(String input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] inputToDecode = Base64.getDecoder().decode(input);
		byte[] output = decrypt(inputToDecode, secretKey, ivParameterSpec);
		
		return new String(output);
		
	}


	public byte[] encryptFile(String inputPathToFile, String outputPathToFile, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException {
		
		File inputFile = new File(inputPathToFile);
		File outputFile = new File(outputPathToFile);
		
		byte[] fileInBytes = Files.readAllBytes(inputFile.toPath());
		
		byte[] output = encrypt(fileInBytes, secretKey, ivParameterSpec);
		
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		
		fileOutputStream.write(output);
		fileOutputStream.close();
		
		return output;
	}


	public byte[] decryptFile(String inputPathToFile, String outputPathToFile, SecretKey secretKey,
			IvParameterSpec ivParameterSpec) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		
		File inputFile = new File(inputPathToFile);
		File outputFile = new File(outputPathToFile);
		
		byte[] fileInBytes = Files.readAllBytes(inputFile.toPath());
		
		byte[] output = decrypt(fileInBytes, secretKey, ivParameterSpec);
		
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		
		fileOutputStream.write(output);
		fileOutputStream.close();
		
		return output;
	}

	

}
