package foundation;

import util.CryptoTools;
import java.math.*;
public class A_Crypta {
	
	public static void main(String[] args){
		try{
			byte[] cipherText = CryptoTools.fileToBytes("data/MSG3.ct");
			int[] frequency = CryptoTools.getFrequencies(cipherText);
			int textSize = cipherText.length;
			for(int i = 0; i < frequency.length; i++){
				System.out.print((char)(i+65)+": "+(double)frequency[i]/textSize+", ");
			}

		}catch(Exception e){
			System.out.println("Exception thrown "+ e);
		}
	}

}
