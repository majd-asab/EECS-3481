package hash;

import java.security.MessageDigest;

import util.CryptoTools;

public class D1 {
	 /*
	 
	  1 For each of the following use cases, determine the needed cryptographic hash function property (or properties):
		1 Password Storage: pre image resistance + weak collision resistance so that you cannot compute the password from the hash
		2 Blob Storage:  strong collision resistance so that different files do not hash to the same value.
		3 Bidding: zero knowledge needs weak collision resistance so that you cannot computer a similar hash from different input (such as lower input in bidding) pre image ( so rivals cant see what being bid), weak collision resistance. 
	
	 */
	
	 /*
	 
	 2 Prove that:
		1 One-way-ness (pre-image resistance) does not imply strong collision resistance(look in pdf) you could possibly have a hash function for which finding a preimage for a *given* output is difficult, but for which you can easily find two inputs which hash to the same value nonetheless (i.e. find a collision)
		2 Strong collision resistance does not imply one-way-ness (pre-image resistance)(look in pdf) hx = 0x and 1g(x) . but x's are all unique 
		3 Weak collision resistance does not imply strong but that the opposite is true ( in weak ,you can't find 1 that matches your hash value, but in strong, you can't find 2!)
	 
	 */
	
	
	/***
	 * 3 Examine each of the three cryptographic properties of the following hash functions and write a brief critique of each:
		1)h(x) = a^x (mod p), where p is a prime and GCD(a,p)=1. its periodic with p-1 where a^x mod p => a^(p-1) = 1 mod p  | a^(x+p-1) = a^x mod p  and any multiple of p will eventually be moded to 1 and can find many collisions. 1)not strong collision resistant  2)weak collision resistant 
		2)h(x) = x^2 (mod n), where n=pq and p,q are two distinct large primes. | not one way at all if x is small  can be easily factored
		3)Divide x into blocks B1, B2, ... Bs of size n bits each and then define h(x) = B1 xor B2 ... xor Bs.

	 ***/
	
	/***
	 * 4 With x people in a room, compute the probability S of at least two of them have the same birthday as follows (ignore leap years):
		1 Do the calculation exactly by subtracting 1 from the probability of all birthdays being different. 
		2 Use the approximation: 1 - x = e-x (valid for small x) to express S as 1 - e-x2/(2N), where N=365 is the number of days in the year.
		3 Show that S becomes >= 50% when x >= 1.177*sqrt(N)
	 ***/
	
	
	public static void main(String[] args){
		/**question 5**/
		
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  byte[] digest = md.digest(psw.getBytes());
		  System.out.println(CryptoTools.bytesToHex(digest));
		  
		  // produced 5ae9b7f211e23aac3df5f2b8f3b8eada
		  
		  //1) why is it impossible to retrieve the password
		  	/**
		  	 * If the md5 hash function conforms to the just any 2 of the hash func properties then A) it would be impossible to find the input that lead to this hash and B) even though there are infinit collision due to the same fixed out the H(x) produced, yet it is extremely hard to find one collision (brute force might need 2^(size of hash output) to find a collision so impossible in your lifetime.
		  	 * How ever, due to the fact it is only hard to find another  plain text the hashes to the same value and there are infinit of them, then we know for certain that there other possible plain texts (not similar to the original, but also hashes to the same value).
		  	 * stimate the time needed to find such a pre-image. normal comp  on a 256 bit size hash function output  10^24 year on a supercomp 10^18
		  	 */
	}

}
