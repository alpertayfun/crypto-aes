var CryptoJS = require("crypto-js");
var Crypto = require("./crypto.js");

var message = "hello world!!!";
var iv = "0k8j7h6g5f4d3s2a";
var key = "0k8j7h6g5f4d3s2ak8j7h6g5f4d3s2a";

Crypto;

var r = Crypto.encrypts(message,key,iv);
console.log(r);
var r = Crypto.decrypts(r,key,iv);
console.log(r);