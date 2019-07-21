package com.logic;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Decrypt 
{
	private static String passWord1="";
	private  static SecretKeyFactory keyFactory;
	private  static byte[] passByte;
    Cipher desCipher;
    SecretKey myDesKey ;
	 public  String  decrypt(String cipher,String passWord1) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IOException
     {
 	   String dec="";
        this.passWord1=passWord1;
             try
             {
             manageKeystrengthMethod();
             keyFactory = SecretKeyFactory.getInstance("DES");
             passByte=this.passWord1.getBytes();
			 DESKeySpec dspec= new DESKeySpec(passByte);
			 SecretKey myDesKey = keyFactory.generateSecret(dspec);
		     Cipher desCipher;

		    // Create the cipher
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

		    //sensitive information
		    byte[] textEncrypted = cipher.getBytes();

		   // System.out.println("Text [Byte Format] : " + text);
		    //System.out.println("Cipher to be decrypted : " + new String(textEncrypted));

		    // Decrypt the text
            Base64 bs=new Base64();
		    byte[] textDecrypted = desCipher.doFinal(bs.decode(cipher));

		    //System.out.println("Text Decryted : " + new String(textDecrypted));
		    dec=new String(textDecrypted);

		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
		}
		return dec;
     }
	 private void manageKeystrengthMethod()
		{
			if(passWord1.length()<8)
			{
				int counter=passWord1.length();
				while(counter<8)
				{
					passWord1+='@';
					counter++;
				}
			}
		}
}
