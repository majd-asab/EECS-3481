package Test_B;

import java.math.BigInteger;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class B3 {
	
	/******DES DECRYPTION*******/
	public static String decryptDES(byte[] iv,byte[] cipherText, byte[] desKey)throws Exception{
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		Cipher engine = Cipher.getInstance("DES/CBC/NoPadding");
		Key myKey = new SecretKeySpec(desKey, "DES");
		engine.init(Cipher.DECRYPT_MODE, myKey, aps);
		byte[] pt = engine.doFinal(cipherText);
		
		return new String(pt);
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
	
	public static void main(String[] args)throws Exception{
		byte[] iv = CryptoTools.hexToBytes("0123456701234567");
		
		byte[] key = "CSE@YORK".getBytes();
		
		byte[] retriv = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
		byte[] nonRetrivIV = CryptoTools.hexToBytes("4E51297B424F90D8");
		
		System.out.println("Part1:" + decryptDES(nonRetrivIV, retriv, key));
		/***part1**/
		
		System.out.println();
		
		/**part2**/
		byte[] iv2  = CryptoTools.hexToBytes("6976466F724D4F50");
		byte[] key2 = CryptoTools.hexToBytes("6B79466F724D4F50");
		
		byte[] ct2_a = CryptoTools.hexToBytes("437DBAB5607137A5");
		byte[] ct2_b = CryptoTools.hexToBytes("CFC1031114634087");

		byte[] negatedIV_1 = new BigInteger(bitwise(CryptoTools.bytesToBin(iv2)),2).toByteArray();
		byte[] decrypted_1 = decryptDES(byteRemover(negatedIV_1),ct2_a,key2).getBytes();
		
	    byte[] negatedIV_2 = new BigInteger(bitwise(CryptoTools.bytesToBin(ct2_a)),2).toByteArray();
		byte[] decrypted_2 = decryptDES(byteRemover(negatedIV_2),ct2_b,key2).getBytes();
		
		System.out.print(new String(decrypted_1));
		System.out.println(new String(decrypted_2));
		
	}

}
