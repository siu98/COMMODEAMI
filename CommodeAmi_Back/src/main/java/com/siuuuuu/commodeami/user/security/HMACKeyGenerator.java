//package com.siuuuuu.commodeami.user.security;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.util.Base64;
//
//@Slf4j
//public class HMACKeyGenerator {
//    public static void main(String[] args) {
//
//        try {
//
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
//            keyGen.init(512);
//
//            SecretKey secretKey = keyGen.generateKey();
//
//            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//
//            log.info(encodedKey);
//        } catch (Exception e) {
//                e.printStackTrace();
//        }
//
//    }
//}
