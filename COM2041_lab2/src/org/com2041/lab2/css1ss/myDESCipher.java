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

public class myDESCipher {
	Cipher ecipher; // DES encryption cipher
	Cipher dcipher; // DES decryption cipher

	myDESCipher(byte[] keyBytes, byte[] ivBytes) {
		SecretKey key = new SecretKeySpec(keyBytes, "DES");
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		try {
			ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key, iv);
			dcipher.init(Cipher.DECRYPT_MODE, key, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException e) {
			System.err.println("Cipher initialization error: " + e.getMessage());
		}
	}

	myDESCipher(byte[] keybytes) {
		SecretKey key = new SecretKeySpec(keybytes, "DES");
		try {
			ecipher = Cipher.getInstance("DES/ECB/NoPadding");
			dcipher = Cipher.getInstance("DES/ECB/NoPadding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.err.println("Cipher initialization error: " + e.getMessage());
		}
	}

	public byte[] encrypt(byte[] databytes) {
		byte[] enc = null;
		try {
			enc = ecipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.err.println("Encryption error: " + e.getMessage());
		}
		return enc != null ? enc : new byte[0]; // Return empty byte array on failure
	}

	public byte[] decrypt(byte[] databytes) {
		byte[] dec = null;
		try {
			dec = dcipher.doFinal(databytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.err.println("Decryption error: " + e.getMessage());
		}
		return dec != null ? dec : new byte[0]; // Return empty byte array on failure
	}
}
