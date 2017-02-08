package foundation;

import util.CryptoTools;
import java.math.*;
public class A_Exhaustive {
	
	
	 
	/*Decryption a^-1(C-b) mod 26*/
	public static byte[] decrypt(int alpha,int beta, byte[] arr){
		byte[] decryptedArr = new byte[arr.length];
		BigInteger a = BigInteger.valueOf(alpha);
		BigInteger m = new BigInteger("26");
		BigInteger inverse = a.modInverse(m);
		
		for(int i = 0; i< arr.length; i++){
			int bToS = ((inverse.intValue()*(((arr[i]-65)- beta))) % 26)+65;
			if((((arr[i]-65) - beta) % 26) < 0) bToS += 26;
			decryptedArr[i] = (byte)(bToS); 
			//System.out.print((char)decryptedArr[i]);
		}
		//System.out.print("\n");
		return decryptedArr;
	}
	
	public static int[] gcdCheck(int m){
		//check and add all the numbers that are
		//coprimes to m, into a list to be used.
		int[] coprimes = new int[m];
		BigInteger modulo = BigInteger.valueOf(m);
		for(int i=1;i<m; i++){
			if(BigInteger.valueOf(i).gcd(modulo).equals(BigInteger.valueOf(1))){
				coprimes[i] = i;
				//System.out.print(coprimes[i]+" ");
			}
		}
		
		return coprimes;
	}
	
	public static void main(String[] args){
		try{
			byte[] cipherText = CryptoTools.fileToBytes("data/EXAM3.ct");
			int[] coprimes = gcdCheck(26);
			double greatestDotProduct =0;
			int bKey =0;
			int aKey =0;
			for(int alpha = 0; alpha < coprimes.length;alpha++){//alpha must consist of numbers that are coprime with the mod
				for(int beta=0; beta < 26; beta++){
					if(coprimes[alpha] == 0){
						alpha++;
					}else{
						byte[] test = A_Exhaustive.decrypt(coprimes[alpha], beta, cipherText);
						int[] frequency = CryptoTools.getFrequencies(test);
						
						double sumOfDotProduct = 0;
						for(int j = 0; j< frequency.length; j++)
							sumOfDotProduct += frequency[j]*CryptoTools.ENGLISH[j];
						if(sumOfDotProduct > greatestDotProduct){
							greatestDotProduct = sumOfDotProduct;
							aKey = alpha;
							bKey = beta;
						}
							//System.out.print("COPRIME: "+coprimes[alpha] +" beta: "+ beta +"Sum of dot product: "+ sumOfDotProduct+"\n");
						
					}
					
				}
				
				
			}
//			byte[] arr = decrypt(3,20,cipherText);
//			for(int i = 0; i < arr.length; i++){
//					System.out.print((char)arr[i]);
//			}
			
			System.out.println("Greatest dot product: "+greatestDotProduct+", Alpha: " + aKey+", Beta: " +bKey);
		}catch(Exception e){
			System.out.println("Exception thrown "+ e);
		}
	}

}
