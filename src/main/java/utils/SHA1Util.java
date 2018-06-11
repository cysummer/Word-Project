package utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.NoSuchAlgorithmException;

/**
 * SHA1Util
 * Create by cy on 2018/5/19
 **/
public class SHA1Util {

    public static String encodeHmacSHA1(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);
        //转为十六进制的字符串输出
    }

    //生成秘钥
    public static byte[] initHmacSHA1Key() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA1");  //假设使用到的是HmacSHA1方法
        SecretKey secretKey = generator.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }


    //byte[]转十六进制String
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    //十六进制String转byte[]
    public static byte[] hexStrToByteArray(String str)
    {
        byte[] byteArray = new byte[0];
        try {
            if (str == null) {
                return null;
            }
            if (str.length() == 0) {
                return new byte[0];
            }
            byteArray = new byte[str.length() / 2];
            for (int i = 0; i < byteArray.length; i++){
                String subStr = str.substring(2 * i, 2 * i + 2);
                byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static void main(String[] args) {
        try {
            String ss = "123456";
            String key = "CF433A4430EC5780AE8C02E4A5C0B66037768BBD3050458E23F1791955DB94ADF6913EA80693700B4AFE0CAE54BFE8476F09B2EE8BE0C365169FCFD85F7EE13C";
//            byte[] key = initHmacSHA1Key();
            String string = encodeHmacSHA1(ss.getBytes(), hexStrToByteArray(key));
            System.out.println(string);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
