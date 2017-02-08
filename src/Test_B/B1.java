package Test_B;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import util.CryptoTools;

import java.math.BigInteger;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Random;

public class B1 {
	
		/***** ENCRYPTION DES *****/
		public static byte[] encrypt(byte[] plainText, byte[] key) throws Exception{
			/***********/ //"DES/OFB32/PKCS5Padding"
			Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
			Key myKey = new SecretKeySpec(key,"DES");
			engine.init(Cipher.ENCRYPT_MODE, myKey);
			/***********/
			byte[] cipherText = engine.doFinal(plainText);
			return cipherText;
		}
		
		
		/***** DECRYPTION DES *****/
		public static String decrypt(byte[] cipherText, byte[] key) throws Exception{
			/***********/ //"DES/OFB32/PKCS5Padding"
			Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
			Key myKey = new SecretKeySpec(key,"DES");
			engine.init(Cipher.DECRYPT_MODE, myKey);
			/***********/
			byte[] decrypted = engine.doFinal(cipherText);
			return new String(decrypted);
			
		}
	
		
		/*****  flip and repeat *****/
		public static int  repeat(String pt1,byte[] ct1, byte[] key)throws Exception{
			/**RANDOM NUMBER**/
			Random rn = new Random();
			int n = rn.nextInt(64);
			
			String binString = pt1;
		
				String[] split = binString.split("");
				if(split[n].equals("1"))
					split[n] = "0";
				else
					split[n] ="1";
				
			String changed = "";
			for(int i=0; i<split.length;i++){
				changed +=split[i];
			}
	
			//convert binary string to binary in base 2 then to byte array
			byte[] flipped = new BigInteger(changed,2).toByteArray(); // parse to bytes 
			
			String flippedStr = new String(flipped);
			
			byte[] ct2 = encrypt(flippedStr.getBytes(),key);
			
			String ct1Bits = CryptoTools.bytesToBin(ct1);
			String ct2Bits = CryptoTools.bytesToBin(ct2);
			
			System.out.println("\ncipherText1: " + ct1Bits);
			System.out.println("cipherText2: "+ ct2Bits);
			

			/***compute the number of bits in the ciphertext that have flipped as a result**/
			int counter = 0;
			String[] ct1BitsSplit = CryptoTools.bytesToBin(ct1).split("");
			String[] ct2BitsSplit = CryptoTools.bytesToBin(ct2).split("");
			
			for(int i =0 ; i < ct1Bits.length(); i++){
				if(!(ct1BitsSplit[i].equals(ct2BitsSplit[i]))){
					counter = counter + 1;
				}
			}
			System.out.println("Number of bits flipped: "+ counter +" out of "+ct1Bits.length());
			return counter;
		}
		
		
		/******AES DECRYPTION*******/
		public static String decryptAES(byte[] iv,byte[] cipherText, byte[] aesKey)throws Exception{
			AlgorithmParameterSpec aps = new IvParameterSpec(iv);
			Cipher engine = Cipher.getInstance("AES/CBC/PKCS5Padding");
			Key myKey = new SecretKeySpec(aesKey, "AES");
			engine.init(Cipher.DECRYPT_MODE, myKey, aps);
			byte[] pt = engine.doFinal(cipherText);
			
			return new String(pt);
		}
		
		
		
		
		/******* MAIN METHOD ******/
		public static void main(String[] args)throws Exception{
//			byte[] pt1 = "Facebook".getBytes();
//			
//			byte[] key = "universe".getBytes();
//			
//			byte[] ct1 = encrypt(pt1,key);
//			
//			
//			
//			String binString = CryptoTools.bytesToBin(pt1);
//			System.out.println("plainText1:  "+ binString);
//			
//			int flippedNumberOfBits = 0;
//			for(int i = 0; i < 10; i++){
//				 flippedNumberOfBits += repeat(binString, ct1, key);
//			}
//			
//			
//			System.out.println("average: "+flippedNumberOfBits/10);
			//-------- DES  ends here ----------------------------//
			
			//-------- AES starts here ----------------------------//
//			//PART 2
//			byte[] aesKey = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
//			byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
//			byte[] aesCt = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
//			
//			String aesPt = decryptAES(iv,aesCt,aesKey);
//			System.out.println("PlainText: " + aesPt);
//			//---PART 2 ENDS HERE --------------------
//			
//			//PART3
//			byte[] aesKey3 = "DO NOT TELL EVE!".getBytes();
//			byte[] iv3 = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
//			byte[] aesCt3 = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
//			
//			String aesPt3 = decryptAES(iv3,aesCt3,aesKey3);
//			System.out.println("PlainText: " + aesPt3);
//			
//			
//			
		}

}
