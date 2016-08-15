package com.alpertayfun.android.cryptoaes;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Toshiba on 15.08.2016.
 */

public class Crypto {

    private static final String AES_MODE = "AES/CBC/PKCS5Padding";
    private static final String HASH_ALGORITHM = "SHA-256";
    public static boolean DEBUG_LOG_ENABLED = false;
    private static final String TAG = "Crypto";

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    static byte[] EncryptStringToBytes(String plainText, byte[] Key, byte[] IV) {
        if (plainText == null || plainText.length() <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "plain text empty");
            }
        }
        if (Key == null || Key.length <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "key is empty");
            }
        }
        if (IV == null || IV.length <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "IV key empty");
            }
        }
        byte[] encrypted;

        try
        {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            SecretKeySpec myKey = new SecretKeySpec(Key, "AES");
            IvParameterSpec IVKey = new IvParameterSpec(IV);
            cipher.init(Cipher.ENCRYPT_MODE, myKey, IVKey);

            encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            return encrypted;
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    static byte[] DecryptStringToBytes(String plainText, byte[] Key, byte[] IV) {
        if (plainText == null || plainText.length() <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "plain text empty");
            }
        }
        if (Key == null || Key.length <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "key is empty");
            }
        }
        if (IV == null || IV.length <= 0)
        {
            if (DEBUG_LOG_ENABLED) {
                Log.e("error", "IV key empty");
            }
        }
        byte[] decrypted;

        try
        {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            SecretKeySpec myKey = new SecretKeySpec(Key, "AES");
            IvParameterSpec IVKey = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, myKey, IVKey);

            decrypted = cipher.doFinal(Base64.decode(plainText.getBytes("UTF-8"), Base64.NO_WRAP));
            if (DEBUG_LOG_ENABLED) {
                Log.d(TAG,"saa "+ decrypted.toString());
            }
            return decrypted;
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String Encrypt(String input, String passphrase, byte[] iv) {
        if (input.equalsIgnoreCase("") || passphrase.equalsIgnoreCase(""))
            return "";
        else
        {

            byte[] passphrasedata = null;
            try
            {
                passphrasedata = passphrase.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e1)
            {
                e1.printStackTrace();
            }

            byte[] currentHash = new byte[0];

            MessageDigest md = null;
            try
            {
                md = MessageDigest.getInstance(HASH_ALGORITHM);
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            currentHash = md.digest(passphrasedata);
            if (DEBUG_LOG_ENABLED) {
                Log.d(TAG,"hash : " + Base64.encodeToString(currentHash, Base64.NO_WRAP));
            }

            return Base64.encodeToString(EncryptStringToBytes(input, currentHash, iv), Base64.NO_WRAP);
        }
    }

    public static String Decrypt(String input, String passphrase, byte[] iv) {
        if (input.equalsIgnoreCase("") || passphrase.equalsIgnoreCase(""))
            return "";
        else
        {

            byte[] passphrasedata = null;
            try
            {
                passphrasedata = passphrase.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e1)
            {
                e1.printStackTrace();
            }

            byte[] currentHash = new byte[0];

            MessageDigest md = null;
            try
            {
                md = MessageDigest.getInstance(HASH_ALGORITHM);
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            currentHash = md.digest(passphrasedata);
            if (DEBUG_LOG_ENABLED) {
                Log.d(TAG,input);
                Log.d(TAG,Base64.encodeToString(currentHash, Base64.NO_WRAP));
            }


            byte[] decryptedBytes = DecryptStringToBytes(input, currentHash, iv);
            String message = null;
            try {
                message = new String(decryptedBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return message;
        }
    }

}
