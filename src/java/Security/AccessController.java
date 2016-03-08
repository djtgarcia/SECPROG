/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Security;

import Beans.User;
import org.owasp.esapi.errors.AccessControlException;

public class AccessController implements org.owasp.esapi.AccessController {
    
    public static final String CREATE_ACCOUNT = "0";
    public static final String UNLOCK_ACCOUNT = "1";
    public static final String REMOVE_ACCOUNT = "2";
    public static final String ADD_PRODUCT = "3";
    public static final String EDIT_PRODUCT = "4";
    public static final String DELETE_PRODUCT = "5";
    public static final String VIEW_RECORD = "6";
    public static final String BUY_PRODUCT = "7";
    public static final String POST_REVIEW = "8";
    
    public User loggedInUser;
    
    public AccessController (User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    
    @Override
    public boolean isAuthorized(Object o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorized(Object o, Object o1) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthorizedForURL(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthorizedForFunction(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthorizedForData(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthorizedForFile(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthorizedForService(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorizedForURL(String string) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorizedForFunction(String string) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorizedForData(String string, Object o) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorizedForFile(String string) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void assertAuthorizedForService(String string) throws AccessControlException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
