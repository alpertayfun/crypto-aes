package com.alpertayfun.android.cryptoaes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
        String encrypted = Crypto.encrypts(message,key,iv);

        Log.d("Crypto",encrypted);

        //decrypted string
        String decrypted = Crypto.decrypts(encrypted,key,iv);

        Log.d("Crypto",decrypted);

    }


}
