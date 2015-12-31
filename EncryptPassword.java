import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.PrintStream;
import java.util.Scanner;
import java.security.MessageDigest;

public class EncryptPassword{
     static String str1,str2;
	public static void main(String args[]){
           if(args.length<1){
		System.out.println("[ERROR] Important param miss!");
 		return;            
		}else 
              str1=args[0];
		
           try{
           str2 = encrypt(md5("7%ChIna3#@Net*%"), str1);
           System.out.println(str2);
       }catch(Exception e){
           System.out.println("Internal Error:2");
       }
	}
	  private static void appendHex(StringBuffer paramStringBuffer, byte paramByte)
	  {
	    paramStringBuffer.append("0123456789ABCDEF".charAt(0xF & paramByte >> 4)).append("0123456789ABCDEF".charAt(paramByte & 0xF));
	  }
	  private static byte[] md5(String paramString)
	    throws Exception{
	    MessageDigest localMessageDigest = MessageDigest.getInstance("md5");
	    localMessageDigest.update(paramString.getBytes("UTF-8"));
	    return localMessageDigest.digest();
	  }
	public static String encrypt(byte[] paramArrayOfByte, String paramString)
	    throws Exception{
	    byte[] arrayOfByte = encrypt(paramArrayOfByte, paramString.getBytes());
	      return toHex(arrayOfByte);
	  }
	private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
	    throws Exception
	  {
	    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
	    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    byte[] arrayOfByte = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	    localCipher.init(1, localSecretKeySpec, new IvParameterSpec(arrayOfByte));
	    return localCipher.doFinal(paramArrayOfByte2);
	  }

	  public static String toHex(String paramString)
	  {
	    return toHex(paramString.getBytes());
	  }
	  public static String toHex(byte[] paramArrayOfByte)
	  {
	    if (paramArrayOfByte == null) {
	      return "";
	    }
	    StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
	    for (int i = 0; i < paramArrayOfByte.length; i++) {
	      appendHex(localStringBuffer, paramArrayOfByte[i]);
	    }
	    return localStringBuffer.toString();
  }
}
