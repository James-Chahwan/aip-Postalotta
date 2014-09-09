/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main.au;

/**
 *
 * @author Ben probably but this is probs from somewhere else aswell idk. <3
 */

import java.security.*;

public class Sha {
  public static String hash256(String data) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(data.getBytes());
    return bytesToHex(md.digest());
  }
  
  public static String bytesToHex(byte[] bytes) {
    StringBuffer result = new StringBuffer();
    for (byte byt : bytes) {
      result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
    }
    return result.toString();
  }
}