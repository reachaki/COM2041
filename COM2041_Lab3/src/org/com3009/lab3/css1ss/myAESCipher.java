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
	Cipher ecipher;  // AES encryption cipher
	Cipher dcipher;  // AES decryption cipher 	
	
	myAESCipher(byte[] keyBytes, byte[] ivBytes){
		SecretKey key = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		try{
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE,key,iv);
			dcipher.init(Cipher.DECRYPT_MODE,key,iv);
		}
		catch (InvalidKeyException e){
			e.printStackTrace();		
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e){
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e){			
			e.printStackTrace();
		}
	}
	
	myAESCipher(byte[] keybytes){
		SecretKey key = new SecretKeySpec(keybytes, "AES");
		try{
			ecipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE,key);
			dcipher.init(Cipher.DECRYPT_MODE,key);	

		}
		catch (InvalidKeyException e){
		}
		catch (NoSuchAlgorithmException e){
		}
		catch (NoSuchPaddingException e){
		} 
	 }


	public byte[] encrypt(byte[] databytes){
		byte[] enc = null;
		try{
		enc = ecipher.doFinal(databytes);
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
		}
		return enc;
	}

	public byte[] decrypt(byte[] databytes){
		byte[] dec = null;
		try{
			dec = dcipher.doFinal(databytes);
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
//			e.printStackTrace();
		}
		return dec;
	}
	
	public boolean paddingQuery(byte[] databytes){
		try{
		dcipher.doFinal(databytes);
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
			return false;
		}
		return true;
	}
	
	public static boolean printable(byte[] databytes){
		int currentByte;
		int len = databytes.length;
		for(int i = 0; i < len-1; i++){
			currentByte = (int) databytes[i];
			if (currentByte<32||currentByte>127){return false;}
		}
		return true;
	}

	
	public static void bruteForce1(
			byte[] keybytes, // the last byte may have been changed
			byte[] ciphertext // the ciphertext to be brute forced
				) {

		boolean answer;
		byte[] possiblePlain;
		
		int count = 0;
				for(int k = 0 ; k < 256 ; k++){
					keybytes[15] = (byte) k;
					myAESCipher dc = new myAESCipher(keybytes);
					answer = dc.paddingQuery(ciphertext);
					if (answer){
						System.out.println("here");
						count++;
						possiblePlain = dc.decrypt(ciphertext);
						if (myAESCipher.printable(possiblePlain)){
//							System.out.println("Possible decryption: "+DatatypeConverter.printHexBinary(possiblePlain));
							String text = new String(possiblePlain);
							System.out.print("Possible decryption: "+text);
//							System.out.println("    Key = "+ DatatypeConverter.printHexBinary(keybytes));
							System.out.println("    Key = "+ new BigInteger(1, keybytes).toString(16).toUpperCase());
							}
						}
					
//			System.out.println("count ="+count);
		}
	 }

	public static void bruteForce2(
			byte[] keybytes, // the last 2 bytes may have been changed
			byte[] ciphertext // the ciphertext to be brute forced
				) {

		boolean answer;
		byte[] possiblePlain;
		
		int count = 0;
			for(int j = 0 ; j < 256 ; j++){
				keybytes[14] = (byte) j;
				for(int k = 0 ; k < 256 ; k++){
					keybytes[15] = (byte) k;
					myAESCipher dc = new myAESCipher(keybytes);
					answer = dc.paddingQuery(ciphertext);
					if (answer){
						count++;
						possiblePlain = dc.decrypt(ciphertext);
						if (myAESCipher.printable(possiblePlain)){
//							System.out.println("Possible decryption: "+DatatypeConverter.printHexBinary(possiblePlain));
							String text = new String(possiblePlain);
							System.out.print("Possible decryption: "+text);
//							System.out.println("    Key = "+ DatatypeConverter.printHexBinary(keybytes));
							System.out.println("    Key = "+ new BigInteger(1, keybytes).toString(16).toUpperCase());
							}
						}
					
				}
			}
//			System.out.println("count ="+count);
	 }
	
	
	
	public static void bruteForce3(
			byte[] keybytes, // the last 3 bytes may have been changed
			byte[] ciphertext // the ciphertext to be brute forced
				) {

		boolean answer;
		byte[] possiblePlain;
		
		int count = 0;
		for(int i=0 ; i<256 ; i++){
		    keybytes[13] = (byte) i;	
			for(int j = 0 ; j < 256 ; j++){
				keybytes[14] = (byte) j;
				for(int k = 0 ; k < 256 ; k++){
					keybytes[15] = (byte) k;
					myAESCipher dc = new myAESCipher(keybytes);
					answer = dc.paddingQuery(ciphertext);
					if (answer){
						count++;
						possiblePlain = dc.decrypt(ciphertext);
						if (myAESCipher.printable(possiblePlain)){
//							System.out.println("Possible decryption: "+DatatypeConverter.printHexBinary(possiblePlain));
							String text = new String(possiblePlain);
							System.out.print("Possible decryption: "+text);
//							System.out.println("    Key = "+ DatatypeConverter.printHexBinary(keybytes));
							System.out.println("    Key = "+ new BigInteger(1, keybytes).toString(16).toUpperCase());
							}
						}
					
				}
			}
//			System.out.println("count ="+count);
		}
	 }

	
}
