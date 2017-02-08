package foundation;

import java.util.ArrayList;

import util.CryptoTools;

public class V_Crypta {
	
	public static void findKey(char[] arr){
		System.out.print("Key decrypted: ");
		for(int i = 0; i < arr.length; i++){
			int shiftNumber = ((arr[i] - 'A')-('E'-'A'));
			if(shiftNumber  < 0) shiftNumber += 26;
			System.out.print((char)(shiftNumber+'A'));
		}
	}
	
	
	/*****crypt-analytical method******/
	public static char lettersFrequency(byte[] array){
		int[] freqOfLetters = CryptoTools.getFrequencies(array);
//		String[] freqWithLetters = new String[freqOfLetters.length];
//		for(int i =0; i< 26; i++){
//			freqWithLetters[i] = (char)(i+65) +":"+ (double)freqOfLetters[i]/array.length+" ";
//			System.out.print(freqWithLetters[i]);
//		}
		char letter = 0;
		double greatestFreq = 0;
		for(int i =0; i< 26; i++){
			if(greatestFreq < (double)freqOfLetters[i]/array.length)
			{
			greatestFreq = (double)freqOfLetters[i]/array.length;
			letter = (char)(i+65);
			}
		}
		return letter;
	}
	
	
	/**This method returns a sequence of chars,shifted by a K keys apart.
	 * Edit i to change the sequence of the array 
	 **/
	public static byte[] keyShift(byte[] cipherText, int key, int sequence){
		
		ArrayList<Byte> tempArray = new ArrayList<Byte>();
		int i = sequence;
		do{
			tempArray.add(cipherText[i]);
			i = i + key;// increment cipherText key;
		}while(i < cipherText.length);
		
		//conver to byte[]
		byte[] shiftedArray = new byte[tempArray.size()];
		for(int k = 0; k < shiftedArray.length; k++){
			shiftedArray[k] = tempArray.get(k);
		}
		
		
		return shiftedArray;
	}
	
	
	
	
	
	
	/**********************MAIN METHOD*****************/
	public static void main(String[] args){
		
		try{
			byte[] cipherText = CryptoTools.clean(CryptoTools.fileToBytes("data/EXAM2.ct"));
			
			int x = 1;
			double greatestIC = 0;
			int greatestICshift = 1;
			
			while(x < 41){
				byte[] shiftedArray = keyShift(cipherText,x,0);
				
				System.out.println("shift #"+x+" "+CryptoTools.getIC(shiftedArray));
				if(greatestIC < CryptoTools.getIC(shiftedArray)){
					greatestIC = CryptoTools.getIC(shiftedArray);
					greatestICshift = x;
				}
				x++;
			}
			System.out.println("--------------------------\nGreatest IC: "+ greatestIC +" shift: "+ greatestICshift);
			/*
			 * Edit the line under here when you have an IC close to English.(Aprx 0.7)
			 */
				char[] secretKey = new char[8];//this is equal to the length of the key 
				System.out.print("Scrambelled: ");
				for(int i =0; i<8;i++){
				// i gives different sequences (starts from index 0 then 1 then 2 )
				// middle parameter makes sure the shift is by # every time within a sequence(key length);
				 secretKey[i] = lettersFrequency(keyShift(cipherText,8,i));
				 System.out.print(secretKey[i]);
				 /*Decryption of key, 1) take the letter  found hiding E 
					 * 					 2)find the shift difference.
					 * 					 3)add from 0( subtract of c.t letter if its the message);  */
				}
				System.out.println();
				findKey(secretKey);
		}catch(Exception e){
			System.out.println("Exception thrown " + e);
		}
	}
}

















