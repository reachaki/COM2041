package org.com3009.lab3.css1ss;

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
	Cipher ecipher;  // DES encryption cipher
	Cipher dcipher;  // DES decryption cipher 	
	
	myDESCipher(byte[] keyBytes, byte[] ivBytes){
		SecretKey key = new SecretKeySpec(keyBytes, "DES");
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		try{
			ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE,key,iv);
			dcipher.init(Cipher.DECRYPT_MODE,key,iv);
		}
		catch (InvalidKeyException e){
		}
		catch (NoSuchAlgorithmException e){
		}
		catch (NoSuchPaddingException e){
		} 
		catch (InvalidAlgorithmParameterException e){			
		}
	}
	
	myDESCipher(byte[] keybytes){
		SecretKey key = new SecretKeySpec(keybytes, "DES");
		try{
			ecipher = Cipher.getInstance("DES/ECB/NoPadding");
			dcipher = Cipher.getInstance("DES/ECB/NoPadding");
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
		}
		return dec;
	}
}
