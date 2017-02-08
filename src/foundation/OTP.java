package foundation;


import util.CryptoTools; 

public class OTP 
{
	public static void main(String[] args) throws Exception
	{
//		String key = "Go Blue Jays Go!";
//		String msg = "Meet me at 10:00";
//		String ct = xor(msg, key);
//		//System.out.println("CT=" + CryptoTools.bytesToHex(ct.getBytes()));
//		// CT=0A0A45364C1800002B155942107D5F11
//		
		
//		String alt = "Don't come today";
//		String altKey = xor(alt, ct);
//		String altpt = xor(ct, altKey); //xoring the ct with pad key gives you back the plain text
//		//System.out.println("Alt PT = " + altpt);
//		String key = "Go Blue Jays Go!";
		String pKey= "bottle          ";
		String CT1 = "3D48044D420E5446411541054204131C";
		String CT2 = "3D54024D531442454C09411754041557";
		
		
		String PCT = "0E0C3075723A6002752771407730272A";
//		String key = xor(CryptoTools.bytesToHex(pKey.getBytes()),CT1);
//		
//		System.out.print(CryptoTools.bytesToHex(key.getBytes()));
//		
		String xPCT = xor(PCT,CT2);
		System.out.println(CryptoTools.bytesToHex(xPCT.getBytes()));
		
		String xx = "0E10377963267601793A702360362162";
		System.out.print(xor(xx,CT1));
	}
	
	private static String xor(String x, String y) throws Exception
	{
		if (x.length() != y.length()) throw new Exception("Lengths Mismatch!");
		byte[] xb = x.getBytes();// CryptoTools.hexToBytes(y);//
		byte[] yb = y.getBytes();//CryptoTools.hexToBytes(y);//
		byte[] result = new byte[xb.length];
		
		for (int i = 0; i < xb.length; i++)
		{
			result[i] = (byte) (xb[i] ^ yb[i]);
		}
		return new String(result); 
	}
}
