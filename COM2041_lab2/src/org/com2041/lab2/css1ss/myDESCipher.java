package org.com2041.lab2.css1ss;

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
import java.math.BigInteger;

public class myDESCipher {
	private Cipher ecipher; // DES encryption cipher
	private Cipher dcipher; // DES decryption cipher

	public myDESCipher(byte[] keyBytes, byte[] ivBytes) {
		if (keyBytes.length != 8) {
			throw new IllegalArgumentException("Key must be 8 bytes long for DES.");
		}
		if (ivBytes.length != 8) {
			throw new IllegalArgumentException("IV must be 8 bytes long for DES.");
		}

		SecretKey key = new SecretKeySpec(keyBytes, "DES");
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		try {
			ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key, iv);
			dcipher.init(Cipher.DECRYPT_MODE, key, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException e) {
			throw new RuntimeException("Cipher initialization error: " + e.getMessage(), e);
		}
	}

	public myDESCipher(byte[] keybytes) {
		if (keybytes.length != 8) {
			throw new IllegalArgumentException("Key must be 8 bytes long for DES.");
		}

		SecretKey key = new SecretKeySpec(keybytes, "DES");
		try {
			ecipher = Cipher.getInstance("DES/ECB/NoPadding");
			dcipher = Cipher.getInstance("DES/ECB/NoPadding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new RuntimeException("Cipher initialization error: " + e.getMessage(), e);
		}
	}

	public byte[] encrypt(byte[] databytes) {
		try {
			return ecipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException("Encryption error: " + e.getMessage(), e);
		}
	}

	public byte[] decrypt(byte[] databytes) {
		try {
			return dcipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException("Decryption error: " + e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		// Example usage
		String keyHex = "13579BDF02468ACE"; // Example key
		byte[] key = new BigInteger(keyHex, 16).toByteArray();

		// Ensure key length is correct (DES requires 8 bytes)
		if (key.length != 8) {
			key = new byte[] { 0, key[0], key[1], key[2], key[3], key[4], key[5], key[6] };
		}

		myDESCipher cipher = new myDESCipher(key);

		String message = "Hello";
		byte[] encrypted = cipher.encrypt(message.getBytes());

		System.out.println("Encrypted Text (hex): " + new BigInteger(1, encrypted).toString(16).toUpperCase());

		byte[] decrypted = cipher.decrypt(encrypted);
		System.out.println("Decrypted Text (ascii): " + new String(decrypted));
	}
}
