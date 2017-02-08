package foundation;

import util.CryptoTools;

public class S_Encrypt {
	
	public static String encrypt(byte[] arr,int key)throws Exception{
		byte[] encryptedArray = new byte[arr.length];
		if(arr.length % key != 0) throw new Exception("key and pt length are not dividing correctly");
		else{
			//int numberOfRows = arr.length/key;
			
			int j = 0;
			int i = 0;
			int k = 0;
			while(i < arr.length){
				do{
					encryptedArray[i] = arr[j];
					
					
					j = j + key;
					i++;
				}while(j < arr.length);
				j = k + 1;
				k++;
				
			}
			
		}
		return new String(encryptedArray);
		
	}
	
	public static void main(String[] args){
		try{
		byte[] pt = CryptoTools.clean(CryptoTools.fileToBytes("data/SCTL.pt"));
		System.out.print(encrypt(pt, 3));
		
		
		}catch(Exception e){
			System.out.println("Exception thrown " + e);
		}
	} 

}
