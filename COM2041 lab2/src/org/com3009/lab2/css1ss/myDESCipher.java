package org.com3009.lab2.css1ss;

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
			/* TODO:  set ecipher to be a DES encryption cipher in CBC mode with PKCS5 padding and ivBytes as initialisation vector */
			/* TODO:  set dcipher to be a DES encryption cipher in CBC mode with PKCS5 padding and ivBytes as initialisation vector */
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
			/* TODO set ecipher to be a DES encryption cipher in ECB mode with no padding	*/
			/* TODO set dcipher to be a DES decryption cipher in ECB mode with no padding	*/
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
			/* TODO encrypt databytes with ecipher */
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
			/* TODO decrypt databytes with ecipher */
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
		}
		return dec;
	}
	
}
