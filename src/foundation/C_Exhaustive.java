package foundation;

import util.CryptoTools; 

public class C_Exhaustive {
	
	public static byte[] decrypt(int key, byte[] arr){
		byte[] decryptedArray = new byte[arr.length];
		for(int i = 0; i < arr.length; i++){
			int shift = ((((arr[i] - 65) - key) % 26) + 65);
			if((((arr[i] - 65) - key) % 26) < 0) // avoid mixing up other with other characters by checking if the value(after mod) is less than 0
				shift += 26;
			decryptedArray[i] = (byte)shift;
			System.out.print((char)decryptedArray[i]);
		}
		
		return decryptedArray;
	}
	
	public static void main(String[] args){
		try{
			
			byte[] cipherText = CryptoTools.clean(CryptoTools.fileToBytes("data/EXAM4.ct"));
			for(int i = 0; i < 26; i++){
				
				C_Exhaustive.decrypt(i, cipherText); //using keys to decrypt
				int[] frequency = CryptoTools.getFrequencies(C_Exhaustive.decrypt(i, cipherText));// getting the frequency of letters
				
				//calculate the dot product and the greatest value will be the most accurate to english
				double sumOfDotProduct = 0;
				for(int j = 0; j< frequency.length; j++)
					sumOfDotProduct += frequency[j]*CryptoTools.ENGLISH[j];
				System.out.print("\nShift: "+ i +", Sum of dot product: "+ sumOfDotProduct+" IC: "+CryptoTools.getIC(C_Exhaustive.decrypt(i, cipherText) )+"\n\n");
				
					
			}
			
		}catch(Exception e){
			System.out.println("Exception thrown: "+ e);
		}
		
	}

}
