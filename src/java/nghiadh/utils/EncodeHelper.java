/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author haseo
 */
public class EncodeHelper {
    private static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    public static String toHexString(String input) throws NoSuchAlgorithmException{
        BigInteger bi = new BigInteger(1,getSHA(input));
        StringBuilder hexString = new StringBuilder(bi.toString(16));
        while(hexString.length()<32){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
