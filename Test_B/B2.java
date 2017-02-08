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

public class B2 {
	
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
		
		public static String bitwise(String str){
			String[] split = str.split("");
			for(int i = 0 ; i< split.length; i++){
			
			if(split[i].equals("1"))
				split[i] = "0";
			else
				split[i] ="1";
			}
			
			String bitwised = "";
			for(int i=0; i<split.length;i++){
				bitwised +=split[i];
			}		
			return bitwised;
		}
		
		public static byte[] byteRemover(byte[] extraByte){
			byte[] bt = new byte[8];
			for(int i=0; i< 8; i++ ){
				bt[i] = extraByte[i+1];
			}
			return bt;
		}
	
	
		/******* MAIN METHOD ******/
		public static void main(String[] args)throws Exception{
			byte[] pt1 = "facebook".getBytes();
			
			byte[] key = "universe".getBytes();
			
			byte[] ct1 = encrypt(pt1,key);
			
			
			
			String keyBitwise = bitwise(CryptoTools.bytesToBin(key));
			String pt1Bitwise = bitwise(CryptoTools.bytesToBin(pt1));
			byte[] keyBitwiseBytes = byteRemover(new BigInteger(keyBitwise,2).toByteArray());
			byte[] pt1BitwiseBytes = byteRemover(new BigInteger(pt1Bitwise,2).toByteArray());
			
			String binaryString = CryptoTools.bytesToBin(ct1);
			byte[] ct1bitwised = encrypt(pt1BitwiseBytes,keyBitwiseBytes);
			
			//****
			
			System.out.println("P ENC K:    "+binaryString);
			System.out.println("bitwise Ct: "+ bitwise(binaryString));
			System.out.println("~P ENC ~K : "+CryptoTools.bytesToBin(ct1bitwised));
			System.out.println("are they equal: "+bitwise(binaryString).equals(CryptoTools.bytesToBin(ct1bitwised)));
//			/*****part b*******/
//			
//			byte[] pt2 = "facebook".getBytes();
//			
//			byte[] key2 = CryptoTools.hexToBytes("ffffffffffffffff");
//			
//			byte[] ct2 = encrypt(pt1,key2);
//			
//			System.out.println(new String(encrypt(ct2,key2)));


		}

}
