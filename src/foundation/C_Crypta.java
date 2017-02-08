package foundation;

import util.CryptoTools;
/*
 * In this class, the Frequency of every letter in the C.T is divided by the length of the Cipher text
 * The closest result to (1), is the alphabet that is ciphering the letter (E)
 * calculate the difference (shift) between the alphabets and you will have your shift
 * ex if A has the greatest value, then the A is hiding E and from A to E, it 5. 26-5 = 22*/
public class C_Crypta {	
	public static void main(String[] args){
		try{
			
			byte[] cipherText = CryptoTools.clean(CryptoTools.fileToBytes("data/EXAM4.ct"));
		
				int[] frequency = CryptoTools.getFrequencies(cipherText);// getting the frequency of letters
				int letterCount = cipherText.length;
				
				for(int i= 0; i < frequency.length; i++)
					System.out.print((char)(i+65)+":" + (double)frequency[i]/letterCount+" ");
				
		}catch(Exception e){
			System.out.println("Exception thrown: "+ e);
		}
		
	}

}
