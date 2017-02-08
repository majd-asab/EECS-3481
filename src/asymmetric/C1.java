package asymmetric;

import java.math.BigInteger;

public class C1 {
	
	public static void main(String[] args){
		//1. Implement RSA with modulus 55 and public exponent 3 by finding p, q, and d. Given a plaintext m with GCD(m,55)=1,
		//prove that m = cd (mod 55), where c is me (mod 55). 
		
		final int CERTAINTY = 20;
		BigInteger one = new BigInteger("1");
		
		BigInteger p = new BigInteger("11"); //we know its prime
		BigInteger q = new BigInteger("5"); //we know its prime
		
		
		BigInteger n = p.multiply(q); //find n
		BigInteger phi = p.subtract(one).multiply(q.subtract(one));
		System.out.println("phi: "+phi);
		//pick a public exponenet
		BigInteger e = new BigInteger("3");// turn it into a loop that keeps finding them
		assert e.gcd(phi).equals(one);
		System.out.println("e: "+e);
		//find d
		BigInteger d = e.modInverse(phi);
		System.out.println("d: "+d);
		
		String msg = "H";
		BigInteger m = new BigInteger(msg.getBytes());
		assert m.compareTo(n) == -1; // assert m is less than n
				
		BigInteger ct = m.modPow(e,n);
		BigInteger pt =(ct).modPow(d, n); 
		
		System.out.println("ct: "+ct);
		System.out.println("pt: "+pt);
		/////PART 1 ENDS//////////////////////////////////////////////////////////////////////////////////////////////
		
			//We can implement 2RSA by choosing a modulus n and two public exponents e1 and e2. In this case,
			//encryption is done using: c1 = me1 (mod n) followed by c2 = c1e2 (mod n). Is 2RSA stronger than 1RSA? Juystify your answer. 
			// Answer: no difference since (m^e1)^e2 == M^(e1*e2)
		
		////PART 3 ENDS///////////////////////////////////////////////////////////////////////////////////////////////
			//The exponents e = 1 and e = 2 should not be used in RSA. Why?
				//If we use e = 1 in RSA and compute c = m1
				//(mod n), then c is the
				//plaintext!
				//In the case of e = 2, We know that φ(n) = (p-1)(q-1)
				// then GCD(e,φ(n))!= 1) since 2 is not prime
		
		///PART 4/////////////////////////////////////////////////////////////////////////////////////////////////////
		//If they are not equal and not relatively prime they can be attacked, and if they are not prime factoring them would be easier
		// as there exists multiple products that give the same result.
	
	}

}
