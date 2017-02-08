package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class test1 {
	
	public static void main(String[] args)throws Exception{
		
		BigInteger one = new BigInteger("1");
		
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		BigInteger e = new BigInteger("65537");
		BigInteger c = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
		// p = phi/(q-1) +1
		
		BigInteger p = (phi.divide(q.subtract(one))).add(one);
		BigInteger n = p.multiply(q);
		BigInteger d = e.modInverse(phi);
		  /*--USING JCE ----*/
	      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	      RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
	      RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(n, d);
	      RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	      RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
	      
	
	      Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
	      cipher.init(Cipher.DECRYPT_MODE, privKey);
	      byte[] pt = cipher.doFinal(c.toByteArray());
	      System.out.println("PT from JCE:\t" + new String(pt).trim());
	}

}
