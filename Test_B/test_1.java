package Test_B;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class test_1 {
	
	/***** DECRYPTION DES *****/
	public static byte[] decrypt(byte[] cipherText, byte[] key) throws Exception{
		/***********/ //"DES/OFB32/PKCS5Padding"
		Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey = new SecretKeySpec(key,"DES");
		engine.init(Cipher.DECRYPT_MODE, myKey);
		/***********/
		byte[] decrypted = engine.doFinal(cipherText);
		return decrypted;
		
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
		byte[] ct1 = CryptoTools.hexToBytes("0F73399CA2C11EEC");
		
		byte[] key = "FACEBOOK".getBytes();
		
		//bitwise the key
		String keyBitwise = bitwise(CryptoTools.bytesToBin(key));
		byte[] keyBitwiseBytes = byteRemover(new BigInteger(keyBitwise,2).toByteArray());
		
		byte[] decryptedM = decrypt(ct1,keyBitwiseBytes);
		System.out.println(new String(decrypt(decryptedM,key)));
		
		
//		String keyBitwise = bitwise(CryptoTools.bytesToBin(key));
//		String pt1Bitwise = bitwise(CryptoTools.bytesToBin(pt1));
//		byte[] keyBitwiseBytes = byteRemover(new BigInteger(keyBitwise,2).toByteArray());
//		byte[] pt1BitwiseBytes = byteRemover(new BigInteger(pt1Bitwise,2).toByteArray());
//		
//		String binaryString = CryptoTools.bytesToBin(ct1);
//		byte[] ct1bitwised = encrypt(pt1BitwiseBytes,keyBitwiseBytes);
//		
		//****


	}


}
