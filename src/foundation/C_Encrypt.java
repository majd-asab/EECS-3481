package foundation;

import util.CryptoTools;

public class C_Encrypt {
	
	/* Encryption formula E = (x + n) mod 26 */
	public static byte[] encrypt(int key, byte[] arr){
		
		byte[] encryptedArr = new byte[arr.length];
		for(int i = 0; i < arr.length; i++){
			
			int shift = (((arr[i]-65) + key) % 26)+65;
			if((((arr[i]-65) + key) % 26) < 0) shift += 26;
			encryptedArr[i] = (byte)(shift);
			
		}
		
		
		return encryptedArr;
		
	}
	
	public static void main(String[] args){
		try{
			
			byte[] arr = CryptoTools.fileToBytes("data/MSG1.pt");
			byte[] cleanArray = CryptoTools.clean(arr);
			CryptoTools.bytesToFile(cleanArray, "data/MSG1.clean");
			
			
			byte[] encryptedArray = C_Encrypt.encrypt(19, CryptoTools.fileToBytes("data/MSG1.clean"));
			CryptoTools.bytesToFile(encryptedArray, "data/MSG1.ct");
			System.out.println("md5Sum: "+CryptoTools.getMD5(encryptedArray));
			System.out.print("IdxCoi: "+CryptoTools.getIC(CryptoTools.fileToBytes("data/MSG1.clean")));
		}catch(Exception e){
			System.out.println("exception thrown: "+ e);
		}
	}

}
