package org.com2041.lab2.css1ss;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class myDESCipherTest {

	@Test
	public void test1() {
		System.out.println("============= TEST1 =============");
		byte[] cipher;
		byte[] plain;
		byte[] keybytes;

		// Generate key bytes from hex string
		keybytes = new BigInteger("13579BDF02468ACE", 16).toByteArray();

		String message = "Alphabet";
		myDESCipher dc = new myDESCipher(keybytes);
		cipher = dc.encrypt(message.getBytes());
		System.out.println("Encrypted message (hex): " + bytesToHex(cipher));

		// Expected output
		byte[] expected = new BigInteger("5DEA6FA34EDDA339", 16).toByteArray();
		assertTrue(Arrays.equals(cipher, expected), "Cipher does not match expected output");

		plain = dc.decrypt(cipher);
		System.out.println("Decrypted Ciphertext (hex): " + bytesToHex(plain));
		System.out.println("Decrypted Ciphertext (ascii): " + new String(plain));

		assertEquals(message, new String(plain), "Decrypted message does not match original");
	}

	@Test
	public void test2() {
		System.out.println("============= TEST2 =============");
		byte[] cipher;
		byte[] plain;
		byte[] keybytes;

		String key = "13579BDF02468ACE";
		String iv = "A0A1A2A3A4A5A6A7";

		// Generate key and IV bytes from hex strings
		keybytes = new BigInteger(key, 16).toByteArray();
		byte[] ivbytes = hextStringToByteArray(iv);
		System.out.println("Key length: " + keybytes.length);
		System.out.println("IV length:  " + ivbytes.length);

		String message = "Second Test";
		System.out.println("Initial message: " + message);

		myDESCipher dc = new myDESCipher(keybytes, ivbytes);
		cipher = dc.encrypt(message.getBytes());
		System.out.println("Encrypted message (hex): " + bytesToHex(cipher));

		byte[] leadingZero = new BigInteger("C9A476194958FB9716A59B869F5F4351", 16).toByteArray();
		byte[] expected = Arrays.copyOfRange(leadingZero, 1, leadingZero.length);

		assertTrue(Arrays.equals(cipher, expected), "Cipher does not match expected output");

		plain = dc.decrypt(cipher);
		System.out.println("Decrypted Ciphertext (hex): " + bytesToHex(plain));
		System.out.println("Decrypted Ciphertext (ascii): " + new String(plain));

		assertEquals(message, new String(plain), "Decrypted message does not match original");
	}

	// Convert hex string to byte array
	public static byte[] hextStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// Convert byte array to hex string
	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
