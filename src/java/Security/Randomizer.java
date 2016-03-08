/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.owasp.esapi.errors.EncryptionException;

public class Randomizer implements org.owasp.esapi.Randomizer {

    protected SecureRandom randomGenerator;
    
    public Randomizer() throws NoSuchAlgorithmException {
            randomGenerator = SecureRandom.getInstance("SHA1PRNG");
            randomGenerator.setSeed(10);
    }
    @Override
    public String getRandomString(int i, char[] chars) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getRandomBoolean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRandomInteger(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getRandomLong() {
        return randomGenerator.nextLong();
    }

    @Override
    public String getRandomFilename(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getRandomReal(float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRandomGUID() throws EncryptionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getRandomBytes(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
