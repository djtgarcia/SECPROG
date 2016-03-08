/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class Hasher {
    protected MessageDigest hashGenerator;
    
    public Hasher() throws NoSuchAlgorithmException {
        hashGenerator = MessageDigest.getInstance("SHA-256");
    }
    
    public void resetHash() {
        hashGenerator.reset();
    }
    public void updateHash(String value, String charset) throws UnsupportedEncodingException {
        hashGenerator.update(value.getBytes(charset));
    }
    public void updateHash(byte[] value) {
        hashGenerator.update(value);
    }
    public byte[] getHashBytes() {
        return hashGenerator.digest();
    }
    public String getHashBASE64() {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(hashGenerator.digest());
    }
}
