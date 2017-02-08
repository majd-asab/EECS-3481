package Test_B;

import java.math.BigInteger;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class test_2 {
	
	/******DES DECRYPTION*******/
	public static String decryptDES(byte[] iv,byte[] cipherText, byte[] desKey)throws Exception{
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		Cipher engine = Cipher.getInstance("DES/CBC/NoPadding");
		Key myKey = new SecretKeySpec(desKey, "DES");
		engine.init(Cipher.DECRYPT_MODE, myKey, aps);
		byte[] pt = engine.doFinal(cipherText);
		
		return new String(pt);
	}

	
	public static void main(String[] args)throws Exception{
		
		/***part1**/
		byte[] iv =   "InVector".getBytes();
		byte[] key1 = "HelpThem".getBytes();
		byte[] key2 = "OurRight".getBytes();
		byte[] ct1_a = CryptoTools.hexToBytes("E52D5E84466CE90B");
		byte[] ct1_b = CryptoTools.hexToBytes("C6154B487EC26E29");
		byte[] ct1_c = CryptoTools.hexToBytes("52A5CA8FA66D9F95");

		String a = decryptDES(iv,ct1_a,key1);
		
		String b = decryptDES(ct1_a,ct1_b,key2);
		
		String c = decryptDES(ct1_b,ct1_c,key1);
	
		System.out.println(a+b+c);

		
	}

}
