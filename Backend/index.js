var CryptoJS = require("crypto-js");


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