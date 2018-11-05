package cn.zjy.dayong.demo.utils;

import io.jsonwebtoken.impl.Base64Codec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/10/24
 * Time:15:31
 */
public class HS256 {

    public static String returnSign(String message){
        String HmacSHA256 = "HmacSHA256";
        String has = "";
        String secret = "mystar";
        try {
            Mac sha256_HMAC = Mac.getInstance(HmacSHA256);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),HmacSHA256);
            sha256_HMAC.init(secret_key);
            has = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(message + "-----" + has);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return has;
    }

    public static void main(String[] args) {
        String message = "123456";
        String HmacSHA256 = "HmacSHA256";
        String has = "";
        String secret = "mystar";
        try {
            Mac sha256_HMAC = Mac.getInstance(HmacSHA256);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),HmacSHA256);
            sha256_HMAC.init(secret_key);
            has = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(message + "-----" + has);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
