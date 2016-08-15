# ﻿Crypto-Aes
--------


### Cross Platform / Encryption - Decryption


Project contains the implementation SHA-256 bit AES encryption which works on stated platforms only. Easy use and implementation.


Platforms:

- iOS ( not tested yet)

- Android

- Windows (C#). ( not tested yet )

- Node.js

- Web

## Android Side

Just added Crypto.java file into your project and then add these functions.

### Functions

```
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
```

### Usage

```
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
```


## Web Side

Must be included cryptojs.js file into your project.

### Functions

```
function getBytes(e){
  var bytes = [];
  for (var i = 0; i < e.length; ++i) {
    bytes.push(e.charCodeAt(i));
  }
  return bytes;
}

function getHex(e){
  return e.map(function(byte) {
    return ('0' + (byte & 0xFF).toString(16)).slice(-2);
  }).join('');
}

function hash(e){
  return CryptoJS.SHA256(e);
}
```

### Usage

```
var message = "hello world!!!";
var iv = "0k8j7h6g5f4d3s2a";
var key = "0k8j7h6g5f4d3s2ak8j7h6g5f4d3s2a";


var iv1 = CryptoJS.enc.Hex.parse(getHex(getBytes(iv)));

console.log(iv1);

var cipher = CryptoJS.AES.encrypt(message, hash(key), {
    iv: iv1,
    mode: CryptoJS.mode.CBC,
    keySize: 256 / 32,
    padding: CryptoJS.pad.Pkcs7
});


var encrypted64 = cipher.ciphertext.toString(CryptoJS.enc.Base64);
console.log(encrypted64);

//decrypt
var bytes  = CryptoJS.AES.decrypt(encrypted64.toString(), hash(key), {
      iv: iv1,
      mode: CryptoJS.mode.CBC,
      keySize: 256 / 32,
      padding: CryptoJS.pad.Pkcs7
  });

var plaintext = bytes.toString(CryptoJS.enc.Utf8);

console.log(plaintext);
```