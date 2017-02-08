package symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

import java.security.Key;
public class DES {
	
		public static void main(String[] args)throws Exception{
			byte[] pt1 = "12345678".getBytes();
			byte[] pt2 = "82345678".getBytes();
			
			byte[] key = "helloYou".getBytes();
			
			/***********/ //"DES/OFB32/PKCS5Padding"
			Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
			Key myKey = new SecretKeySpec(key,"DES");
			engine.init(Cipher.ENCRYPT_MODE, myKey);
			/***********/
			
			byte[] ct1 = engine.doFinal(pt1);
			byte[] ct2 = engine.doFinal(pt2);
			
			System.out.println("cipherText 1: "+CryptoTools.bytesToHex(ct1));
			System.out.println("cipherText 2: "+CryptoTools.bytesToHex(ct2));
			
			
			/*Decryption*/
			engine.init(Cipher.DECRYPT_MODE, myKey);
			byte[] decrypted = engine.doFinal(ct1);
			System.out.println("decrypted ct1: "+ new String(decrypted));
					
		}

}
