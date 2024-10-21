package org.com3009.lab3.css1ss;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;

import org.com3009.lab3.css1ss.myAESCipher;
import org.junit.Test;

public class myAESCipherTest {

		
	@Test
	public void AESConstructorTest() {
		System.out.println("============= AESConstructorTest =============");
		byte[] cipher;
		byte[] plain;
		byte[] keyBytes;
		byte[] ivBytes;
		
		keyBytes = "This is 16 bytes".getBytes();
		ivBytes = "16 bytes as well".getBytes();
		String message = new String("Second Test");

		myAESCipher dc = new myAESCipher(keyBytes,ivBytes);
		cipher = dc.encrypt(message.getBytes());
		byte[] leadingZero = new BigInteger("FF02E2AD0327E982E2458DB0D1E5D848",16).toByteArray(); //returns leading zero for bit sign
		byte[] ans = Arrays.copyOfRange(leadingZero, 1, leadingZero.length);
		
		System.out.println("Cipher " + cipher.length);
		System.out.println("Ans    " + ans);
		
	    assertTrue(Arrays.equals(cipher,ans));
	  
		plain = dc.decrypt(cipher);
		
		assertEquals(message, new String(plain));
	 }

	@Test
	public void paddingQueryTest(){
		System.out.println("============= paddingQueryTest =============");
		byte[] keybytes = {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2};
		byte[] ivBytes = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		byte[] cipher;
		byte[] plain;	
		
		myAESCipher dc = new myAESCipher(keybytes,ivBytes);
		plain = "my message".getBytes();
		cipher = dc.encrypt(plain);
		assertTrue(dc.paddingQuery(cipher));
		cipher[15] = 2;
		assertFalse(dc.paddingQuery(cipher));
	}
	
	@Test
	public void paddingQueryTest2(){
		System.out.println("============= paddingQueryTest2 =============");
		byte[] keybytes = {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2};
		byte[] cipher;
		byte[] plain;	
		
		myAESCipher dc = new myAESCipher(keybytes);
		plain = "my message".getBytes();
		cipher = dc.encrypt(plain);
		assertTrue(dc.paddingQuery(cipher));
		cipher[15] = 2;
		assertFalse(dc.paddingQuery(cipher));
	}
	
	@Test
	public void printableTest() {
		System.out.println("============= printableTest =============");
		byte[] test1 = {63,63,63,63};
		byte[] test2 = "my test!".getBytes();
		byte[] test3 = {63,12,12,63};
		byte[] test4 = {27,1,99,20,1,5,7,12,5,65,69,47,45,32,78,90};
		assertTrue(myAESCipher.printable(test1));
		assertTrue(myAESCipher.printable(test2));	
		assertFalse(myAESCipher.printable(test3));	
		assertFalse(myAESCipher.printable(test4));	
	}
	
	
	@Test
	public void BruteForce1Test() {
		System.out.println("============= BruteForce1Test =============");
		byte[] keybytes = {0,0,0,0,0,0,0,0,1,1,1,1,1,3,1,0};
		byte[] cipher = hextStringToByteArray("F65A5F5EB99C352A5B1F94AE68E1E222B516980328E62C917705E4A8678F7C03");
		
		myAESCipher.bruteForce1(keybytes, cipher);
	}
	
	@Test
	public void BruteForce2Test() {
		System.out.println("============= BruteForce2Test =============");
		byte[] keybytes = {3,6,9,12,90,85,24,36,6,18,30,48,3,67,0,0};
		byte[] cipher = new BigInteger("363C84B102F9BA6B9EB223C073001F07B76EC0796F3EE2F0202DC59F3B24200D",16).toByteArray();
		
		myAESCipher.bruteForce2(keybytes, cipher);
	}
	
	@Test
	public void BruteForce3Test() {
		System.out.println("============= BruteForce3Test =============");
		byte[] keybytes = {4,8,12,16,50,54,70,25,3,11,21,42,13,0,0,0};
		byte[] cipher = hextStringToByteArray("E8EA8B7065CF486F4AF16C269B46A2DF41483A709F5E7636B3DAEE3B5849DC1203739E7FDFB095815EB3DF2E9A006265");
		
		myAESCipher.bruteForce3(keybytes, cipher);
	}
	
	public static byte[] hextStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len/2];
		for(int i=0;i<len;i+=2) {
			data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
}