package com.esewashopping.shared;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
public class ApiSecurityExample {
    public String Signature() {
        try {
            String secret = "secret";
            String message = "Message";
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        } catch (Exception e) {
            return "Sorry cannot generate Signature";
        }
    }
}

