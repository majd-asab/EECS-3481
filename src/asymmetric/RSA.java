package asymmetric;

import java.math.BigInteger;

public class RSA {

	public static void main(String[] args){
		
		final int CERTAINTY = 20;
		BigInteger one = new BigInteger("1");
		
		BigInteger p = new BigInteger("11"); //we know its prime
		BigInteger q = new BigInteger("13"); //we know its prime
		
		assert p.isProbablePrime(CERTAINTY); // fail probability is 1/2^20
		assert q.isProbablePrime(CERTAINTY); // fail probability is 1/2^20
		
		BigInteger n = p.multiply(q);
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		
		//pick a public exponenet
		BigInteger e = new BigInteger("7");// turn it into a loop that keeps finding them
		assert e.gcd(phi).equals(one);
		
		//find d
		BigInteger d = e.modInverse(phi);
		
		
		String msg = "H";
		BigInteger m = new BigInteger(msg.getBytes());
		assert m.compareTo(n) == -1; // assert m is less than n
				
		BigInteger ct = m.modPow(e,n);
		System.out.println("ct: "+ct);
		
		BigInteger pt = ct.modPow(d,n);
		System.out.println("pt: "+pt);
	}
}
