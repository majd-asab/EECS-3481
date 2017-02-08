package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class C3 {
	public static void main(String[] args) throws Exception{
		  BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	      BigInteger e = new BigInteger("74327");
	      //BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
			BigInteger one = new BigInteger("1");
	      BigInteger c = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");
	      BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");
	      BigInteger q = n.divide(p);
	      System.out.println(q);
	      BigInteger phi = p.subtract(one).multiply(q.subtract(one));
	      BigInteger d = e.modInverse(phi);
	      
	      
	      System.out.println("Manual pt: "+ c.modPow(d, n));
	   
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
