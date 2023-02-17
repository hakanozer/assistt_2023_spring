package com.works.services;

import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TinkEncDec {

    @Value("${tink.key128Bit}")
    private String key128Bit;

    @Value("${tink.associatedData}")
    private String associatedData;

    public String encrypt( String plainText ) {
        String cipherText = "";
        try {
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] byteEncrypt = aesGcmJce.encrypt(plainText.getBytes(), associatedData.getBytes());
            cipherText = new String(byteEncrypt, "ISO-8859-1");
        } catch (Exception e) {
            System.err.println("Encrypt Error :" + e);
        }
        cipherText = Base64.encode(cipherText.getBytes());
        return cipherText;
    }

    public String decrypt( String cipherText ) {
        String planText = "";
        try {
            cipherText = new String( Base64.decode(cipherText) );
            byte[] convertEncode = cipherText.getBytes("ISO-8859-1");
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] descBytes = aesGcmJce.decrypt(convertEncode, associatedData.getBytes());
            planText = new String(descBytes);
        } catch (Exception e) {
            System.err.println("decrypt Error : " + e);
        }
        return planText;
    }


}
