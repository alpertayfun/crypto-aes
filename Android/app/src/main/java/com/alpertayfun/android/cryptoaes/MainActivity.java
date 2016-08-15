package com.alpertayfun.android.cryptoaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //debug mode on
        Crypto.DEBUG_LOG_ENABLED = true;

        //key string must be max 32 char
        String key = "0k8j7h6g5f4d3s2ak8j7h6g5f4d3s2a";

        //iv string must be max 16 char
        String iv = "0k8j7h6g5f4d3s2a";

        //message string must be utf-8
        String message = "hello world!!!";

        //ecnrypted string
        String encrypted = encrypts(message,key,iv);

        Log.d("Crypto",encrypted);

        //decrypted string
        String decrypted = decrypts(encrypted,key,iv);

        Log.d("Crypto",decrypted);

    }

    public static String encrypts(String message, String key, String ivs){
        try {
            return Crypto.Encrypt(message,key,Crypto.hexStringToByteArray(Crypto.bytesToHex(ivs.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypts(String message, String key, String ivs){
        try {
            return Crypto.Decrypt(message,key,Crypto.hexStringToByteArray(Crypto.bytesToHex(ivs.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
