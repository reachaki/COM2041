package org.com2041.lab2.css1ss;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test; // JUnit 4 import
import static org.junit.Assert.*; // JUnit 4 import

public class myDESCipherTest {

	@Test
	public void test1() {
		System.out.println("============= TEST1 =============");
		byte[] cipher;
		byte[] plain;
		byte[] keybytes;

		keybytes = new BigInteger("13579BDF02468ACE", 16).toByteArray();

		String message = "Alphabet"; // Updated to avoid issues with new String
		myDESCipher dc = new myDESCipher(keybytes);
		cipher = dc.encrypt(message.getBytes());
		System.out.println("Encrypted message: " + new String(cipher));

		byte[] ans = new BigInteger("5DEA6FA34EDDA339", 16).toByteArray(); // returns leading zero for bit sign

		assertTrue(Arrays.equals(cipher, ans));

		plain = dc.decrypt(cipher);

		System.out.println("Decrypted Ciphertext (hex): " + new BigInteger(1, plain).toString(16).toUpperCase());
		System.out.println("Decrypted Ciphertext (ascii): " + new String(plain));

		assertEquals(message, new String(plain));
	}

	@Test
	public void test2() {
		System.out.println("============= TEST2 =============");
		byte[] cipher;
		byte[] plain;
		byte[] keybytes;

		String key = "13579BDF02468ACE";
		String iv = "A0A1A2A3A4A5A6A7";

		keybytes = new BigInteger(key, 16).toByteArray();
		byte[] ivbytes = hextStringToByteArray(iv);
		System.out.println("Key: " + key.length());
		System.out.println("IVbytes:  " + iv.length());

		String message = "Second Test";
		System.out.println("Initial message: " + message);

		myDESCipher dc = new myDESCipher(keybytes, ivbytes);
		cipher = dc.encrypt(message.getBytes());
		System.out.println("Encrypted message: " + new String(cipher));

		byte[] leadingZero = new BigInteger("C9A476194958FB9716A59B869F5F4351", 16).toByteArray(); // returns leading
																									// zero for bit sign
		byte[] ans = Arrays.copyOfRange(leadingZero, 1, leadingZero.length);

		assertTrue(Arrays.equals(cipher, ans));

		plain = dc.decrypt(cipher);
		System.out.println("Decrypted Ciphertext (hex): " + new BigInteger(1, plain).toString(16).toUpperCase());
		System.out.println("Decrypted Ciphertext (ascii): " + new String(plain));

		assertEquals(message, new String(plain));
	}

	@Test
	public void testDecryptCiphertext() {
		System.out.println("============= DECRYPT TEST =============");
		String hexCiphertext = "B4528C2E87081C4AD54A77EE912956AAD24CD1211E00623F";
		byte[] ciphertext = hextStringToByteArray(hexCiphertext);
		byte[] key = new BigInteger("13579BDF02468ACE", 16).toByteArray();

		myDESCipher dc = new myDESCipher(key);
		byte[] decryptedText = dc.decrypt(ciphertext);
		System.out.println("Decrypted Text (hex): " + new BigInteger(1, decryptedText).toString(16).toUpperCase());
		System.out.println("Decrypted Text (ascii): " + new String(decryptedText));
	}

	public static byte[] hextStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("org.com2041.lab2.css1ss.myDESCipherTest");
	}

}
