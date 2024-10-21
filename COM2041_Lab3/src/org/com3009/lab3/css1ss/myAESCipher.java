package org.com3009.lab3.css1ss;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class myAESCipher {
	Cipher ecipher; // AES encryption cipher
	Cipher dcipher; // AES decryption cipher

	// Constructor for CBC mode
	myAESCipher(byte[] keyBytes, byte[] ivBytes) {
		SecretKey key = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		try {
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key, iv);
			dcipher.init(Cipher.DECRYPT_MODE, key, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	// Constructor for ECB mode
	myAESCipher(byte[] keybytes) {
		SecretKey key = new SecretKeySpec(keybytes, "AES");
		try {
			ecipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	// Encrypts the input data
	public byte[] encrypt(byte[] databytes) {
		byte[] enc = null;
		try {
			enc = ecipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return enc;
	}

	// Decrypts the input data
	public byte[] decrypt(byte[] databytes) {
		byte[] dec = null;
		try {
			dec = dcipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return dec;
	}

	// Checks if padding is correct
	public boolean paddingQuery(byte[] databytes) {
		try {
			dcipher.doFinal(databytes);
		} catch (IllegalBlockSizeException e) {
			return false;
		} catch (BadPaddingException e) {
			return false;
		}
		return true;
	}

	// Checks if the decrypted bytes are printable ASCII characters
	public static boolean printable(byte[] databytes) {
		for (byte b : databytes) {
			if (b < 32 || b > 126) { // 32 to 126 are printable ASCII
				return false;
			}
		}
		return true;
	}

	// Brute force search for the last byte of the key
	public static void bruteForce1(byte[] keybytes, byte[] ciphertext) {
		boolean answer;
		byte[] possiblePlain;

		for (int k = 0; k < 256; k++) {
			keybytes[15] = (byte) k; // Change the last byte
			myAESCipher dc = new myAESCipher(keybytes);
			answer = dc.paddingQuery(ciphertext);
			if (answer) {
				possiblePlain = dc.decrypt(ciphertext);
				if (printable(possiblePlain)) {
					String text = new String(possiblePlain);
					System.out.println("Possible decryption: " + text);
					System.out.println("    Key = " + new BigInteger(1, keybytes).toString(16).toUpperCase());
				}
			}
		}
	}

	// Brute force search for the last 2 bytes of the key
	public static void bruteForce2(byte[] keybytes, byte[] ciphertext) {
		boolean answer;
		byte[] possiblePlain;

		for (int j = 0; j < 256; j++) {
			keybytes[14] = (byte) j; // Change the second last byte
			for (int k = 0; k < 256; k++) {
				keybytes[15] = (byte) k; // Change the last byte
				myAESCipher dc = new myAESCipher(keybytes);
				answer = dc.paddingQuery(ciphertext);
				if (answer) {
					possiblePlain = dc.decrypt(ciphertext);
					if (printable(possiblePlain)) {
						String text = new String(possiblePlain);
						System.out.println("Possible decryption: " + text);
						System.out.println("    Key = " + new BigInteger(1, keybytes).toString(16).toUpperCase());
					}
				}
			}
		}
	}

	// Brute force search for the last 3 bytes of the key
	public static void bruteForce3(byte[] keybytes, byte[] ciphertext) {
		boolean answer;
		byte[] possiblePlain;

		for (int i = 0; i < 256; i++) {
			keybytes[13] = (byte) i; // Change the third last byte
			for (int j = 0; j < 256; j++) {
				keybytes[14] = (byte) j; // Change the second last byte
				for (int k = 0; k < 256; k++) {
					keybytes[15] = (byte) k; // Change the last byte
					myAESCipher dc = new myAESCipher(keybytes);
					answer = dc.paddingQuery(ciphertext);
					if (answer) {
						possiblePlain = dc.decrypt(ciphertext);
						if (printable(possiblePlain)) {
							String text = new String(possiblePlain);
							System.out.println("Possible decryption: " + text);
							System.out.println("    Key = " + new BigInteger(1, keybytes).toString(16).toUpperCase());
						}
					}
				}
			}
		}
	}
}
