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

public class test_3 {
	
	
		
	
		
		/*****  flip and repeat *****/
		public static String  repeat(String ct1)throws Exception{
			/**RANDOM NUMBER**/
			
			
			
		
				String[] split = ct1.split("");
				
				if(split[34].equals("1"))
					split[34] = "0";
				else
					split[34] ="1";
				
			String changed = "";
			for(int i=0; i<split.length;i++){
				changed +=split[i];
			}
			
	
			return changed;

		}
		
		public static int checkFlipped(String ct1, String ct2){
			int counter = 0;
			String[] ct1BitsSplit = ct1.split("");
			String[] ct2BitsSplit = ct2.split("");
			
			for(int i =0; i < ct1BitsSplit.length; i++){
				if(!(ct1BitsSplit[i].equals(ct2BitsSplit[i]))){
					counter = counter + 1;
				}
			}
		
			return counter;
		}
		
		
		/******AES ENCRYPTION*******/
		public static byte[] encryptAES(byte[] cipherText, byte[] aesKey)throws Exception{
			
			Cipher engine = Cipher.getInstance("AES/ECB/NoPadding");
			Key myKey = new SecretKeySpec(aesKey, "AES");
			engine.init(Cipher.DECRYPT_MODE, myKey);
			byte[] ct = engine.doFinal(cipherText);
			
		return ct;
		}
		
		
		
		
		/******* MAIN METHOD ******/
		public static void main(String[] args)throws Exception{
			byte[] pt1 = "YORK UNIVERSITY!".getBytes();
			
			
			byte[] key = "DO NOT TELL EVE!".getBytes();
			
			byte[] enc_1 = encryptAES(pt1,key);
			
			byte[] ptFlip = "YORK UNIVERSITY!".getBytes();
			String flipped = repeat(CryptoTools.bytesToBin(ptFlip));
			byte[] flippedBytePT = new BigInteger(flipped,2).toByteArray();
			byte[] enc_2 = encryptAES(flippedBytePT, key);

			System.out.println(CryptoTools.bytesToBin(enc_1));
			System.out.println(CryptoTools.bytesToBin(enc_2));
			
			System.out.println(checkFlipped(CryptoTools.bytesToBin(enc_1),CryptoTools.bytesToBin(enc_2)));
			
			
			
			
		

		}

}
