var encrypt={
    ENCRYPT_KEY:"WD9787DhdxDSKLDds",
	DES:function(plaintext){
		var keyHex = CryptoJS.enc.Utf8.parse(encrypt.ENCRYPT_KEY);
		var encrypted = CryptoJS.DES.encrypt(plaintext, keyHex, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();  
	}
}