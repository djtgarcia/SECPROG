/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Beans.Product;
import DB.DBConnectionFactory;
import Servlet.CreateAccount;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.AuthenticationException;
import org.owasp.esapi.errors.AuthenticationHostException;
import org.owasp.esapi.errors.EncryptionException;
//import org.apache.log4j.MDC;
//import org.apache.log4j.PropertyConfigurator;

public class Authenticator implements org.owasp.esapi.Authenticator {

    private DBConnectionFactory dBConnectionFactory;
    private Connection connection;
    private ResultSet result;
    private ResultSet result2;
    private ResultSet result3;

    Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

    public boolean userExists(String username) throws NoSuchAlgorithmException {
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();

            String query = "Select username from accounts where username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement.toString());
            result = preparedStatement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Beans.User getCustomerAccount(String username) {
        Beans.User user = null;
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();
            user = new Beans.User();
            String query = "Select * from accounts where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement.toString());
            result = preparedStatement.executeQuery();
            if (result.next()) {
                user.setUserID(result.getInt("userID"));
                user.setUsername(username);
                String type = result.getString("type");
                user.setType(type);
                user.setIsLoggedIn(true);
                if ("Customer".equals(type)) {
                    user.setAddProductRule(false);
                    user.setEditProductRule(false);
                    user.setDeleteProductRule(false);
                    user.setCreateAccountRule(false);
                    user.setUnlockAccountRule(false);
                    user.setRemoveAccountRule(false);
                    user.setViewRecordRule(false);
                    user.setBuyProductRule(true);
                    user.setPostReviewRule(true);

                    String query2 = "select * from customers where cUsername = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                    preparedStatement2.setString(1, username);
                    System.out.println(preparedStatement2.toString());
                    result2 = preparedStatement2.executeQuery();

                    if (result2.next()) {
                        user.setFname(result2.getString("fname"));
                        user.setLname(result2.getString("lname"));
                        user.setEmail(result2.getString("email"));
                        user.setBaddress(result2.getString("baddress"));
                        user.setDaddress(result2.getString("daddress"));
                    }
                }
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean adminRegister(HttpServletRequest req, HttpServletResponse res) throws NoSuchAlgorithmException {
        try {
            PasswordHasher passwordHasher = new PasswordHasher();
            String securePassword = passwordHasher.getSecurePassword(req.getParameter("password"));

            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();

            String query = "insert into accounts (username, pass, type, active) values (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, req.getParameter("username"));
            preparedStatement.setString(2, securePassword); //edit hash password
            preparedStatement.setString(3, "Administrator");
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR at Authenticator.java adminRegister");
        }
        return false;
    }

    public boolean register(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, NoSuchAlgorithmException, NoSuchProviderException {
        try {
            if (req.getParameter("password") == null ? req.getParameter("vpassword") != null : !req.getParameter("password").equals(req.getParameter("vpassword"))) {
                return false;
            } else {
                PasswordHasher passwordHasher = new PasswordHasher();
                String securePassword = passwordHasher.getSecurePassword(req.getParameter("vpassword"));

                dBConnectionFactory = DBConnectionFactory.getInstance();
                connection = dBConnectionFactory.getConnection();

                String baddress = req.getParameter("bhouse") + " " + req.getParameter("bstreet") + " " + req.getParameter("bsubdiv") + " " + req.getParameter("bcity") + " " + req.getParameter("bpcode") + " " + req.getParameter("bcountry");
                String daddress = req.getParameter("dhouse") + " " + req.getParameter("dstreet") + " " + req.getParameter("dsubdiv") + " " + req.getParameter("dcity") + " " + req.getParameter("dpcode") + " " + req.getParameter("dcountry");
                String query = "insert into accounts (username, pass, type, active) values (?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, req.getParameter("username"));
                preparedStatement.setString(2, securePassword); //edit hash password
                preparedStatement.setString(3, "Customer");
                preparedStatement.setInt(4, 1);
                preparedStatement.executeUpdate();

                System.out.println(preparedStatement.toString());
                String query2 = "insert into customers (cUsername, fname, lname, email, baddress, daddress) values (?,?,?,?,?,?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, req.getParameter("username"));
                preparedStatement2.setString(2, req.getParameter("fname"));
                preparedStatement2.setString(3, req.getParameter("lname"));
                preparedStatement2.setString(4, req.getParameter("email"));
                preparedStatement2.setString(5, baddress);
                preparedStatement2.setString(6, daddress);
                preparedStatement2.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: Cannot register user account!");
        }
        return false;
    }

    @Override
    public Beans.User login(HttpServletRequest hsr, HttpServletResponse hsr1) throws AuthenticationException {
        Beans.User user = null;
        String username = null;
        String securePassword = null;
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();
            user = new Beans.User();
            PasswordHasher passwordHasher = new PasswordHasher();
            username = hsr.getParameter("username");
            securePassword = passwordHasher.getSecurePassword(hsr.getParameter("password"));
            String query = "Select * from accounts where username = ? && pass = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, securePassword);
            System.out.println(preparedStatement.toString());
            result = preparedStatement.executeQuery();
            if (result.next()) {
                user.setUserID(result.getInt("userID"));
                user.setUsername(username);
                String type = result.getString("type");
                user.setType(type);
                user.setIsLoggedIn(true);
                if ("Customer".equals(type)) {
                    user.setAddProductRule(false);
                    user.setEditProductRule(false);
                    user.setDeleteProductRule(false);
                    user.setCreateAccountRule(false);
                    user.setUnlockAccountRule(false);
                    user.setRemoveAccountRule(false);
                    user.setViewRecordRule(false);
                    user.setBuyProductRule(true);
                    user.setPostReviewRule(true);

                    String query2 = "select * from customers where cUsername = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                    preparedStatement2.setString(1, username);
                    System.out.println(preparedStatement2.toString());
                    result2 = preparedStatement2.executeQuery();

                    if (result2.next()) {
                        user.setFname(result2.getString("fname"));
                        user.setLname(result2.getString("lname"));
                        user.setEmail(result2.getString("email"));
                        user.setBaddress(result2.getString("baddress"));
                        user.setDaddress(result2.getString("daddress"));
                    }
                } else if ("Administrator".equals(type)) {
                    user.setAddProductRule(false);
                    user.setEditProductRule(false);
                    user.setDeleteProductRule(false);
                    user.setCreateAccountRule(true);
                    user.setUnlockAccountRule(true);
                    user.setRemoveAccountRule(true);
                    user.setViewRecordRule(false);
                    user.setBuyProductRule(false);
                    user.setPostReviewRule(false);
                } else if ("Accounting Manager".equals(type)) {
                    user.setAddProductRule(false);
                    user.setEditProductRule(false);
                    user.setDeleteProductRule(false);
                    user.setCreateAccountRule(false);
                    user.setUnlockAccountRule(false);
                    user.setRemoveAccountRule(false);
                    user.setViewRecordRule(true);
                    user.setBuyProductRule(false);
                    user.setPostReviewRule(false);
                } else if ("Book Manager".equals(type) || "Magazine Manager".equals(type) || "CD Manager".equals(type) || "DVD Manager".equals(type)) {
                    user.setAddProductRule(true);
                    user.setEditProductRule(true);
                    user.setDeleteProductRule(true);
                    user.setCreateAccountRule(false);
                    user.setUnlockAccountRule(false);
                    user.setRemoveAccountRule(false);
                    user.setViewRecordRule(false);
                    user.setBuyProductRule(false);
                    user.setPostReviewRule(false);
                }
                String query3 = "select * from accounts where username = ? and locked_date is not null";
                PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
                preparedStatement3.setString(1, username);
                System.out.println(preparedStatement3.toString());
                result3 = preparedStatement3.executeQuery();
                if (result3.next()) {
                    user.setIsLocked(true);
                }
                else user.setIsLocked(false);
                return user;
            } else {
                return null;
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean createUser(HttpServletRequest req, HttpServletResponse res) throws NoSuchAlgorithmException {
        try {
            PasswordHasher passwordHasher = new PasswordHasher();
            String securePassword = passwordHasher.getSecurePassword(req.getParameter("password"));

            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();

            if (userExists(req.getParameter("username"))) {
                return false;
            } else {
                String query = "insert into accounts (username, pass, type, active) values (?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, req.getParameter("username"));
                preparedStatement.setString(2, securePassword); //edit hash password
                preparedStatement.setString(3, req.getParameter("type"));
                preparedStatement.setInt(4, 0);
                int rowInserted = preparedStatement.executeUpdate();
                System.out.println(query);
                return rowInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in authenticator.java createUser");
        }
        return false;
    }

    public boolean addProduct(HttpServletRequest req, HttpServletResponse res) {
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();

            String query = "insert into products (title, author, description, quantity, price, pType) values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, req.getParameter("title"));
            preparedStatement.setString(2, req.getParameter("author"));
            preparedStatement.setString(3, req.getParameter("description"));
            preparedStatement.setInt(4, Integer.parseInt(req.getParameter("quantity")));
            preparedStatement.setFloat(5, Float.parseFloat(req.getParameter("price")));
            preparedStatement.setString(6, req.getParameter("type"));
            preparedStatement.executeUpdate();
            System.out.println(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in authenticator.java addProduct");
        }
        return false;
    }

    public boolean changePassword(String username, String oldPass, String newPass, String vPass) throws NoSuchAlgorithmException {
        try {
            PasswordHasher passwordHasher = new PasswordHasher();
            String securePassword = passwordHasher.getSecurePassword(oldPass);
            System.out.println(securePassword);
            PasswordHasher passwordHasher2 = new PasswordHasher();
            String securePassword2 = passwordHasher.getSecurePassword(newPass);
            System.out.println(securePassword2);
            PasswordHasher passwordHasher3 = new PasswordHasher();
            String securePassword3 = passwordHasher.getSecurePassword(vPass);
            System.out.println(securePassword3);

            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();

            if (securePassword2 == null ? securePassword3 != null : !securePassword2.equals(securePassword3)) {
                return false;
            } else if (securePassword == null ? securePassword2 == null : securePassword.equals(securePassword2)) {
                return false;
            } else {
                String query = "update accounts set pass = ?, active = ? where pass = ? and username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, securePassword3);
                preparedStatement.setInt(2, 1);
                preparedStatement.setString(3, securePassword);
                preparedStatement.setString(4, username);

                int rowUpdated = preparedStatement.executeUpdate();
                System.out.println(preparedStatement.toString());
                System.out.println(rowUpdated);

                return rowUpdated > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean forgotPassword(String username, String email, String newPass, String vPass) throws NoSuchAlgorithmException {
        try {
            PasswordHasher passwordHasher = new PasswordHasher();
            String securePassword2 = passwordHasher.getSecurePassword(newPass);
            System.out.println(securePassword2);
            String securePassword3 = passwordHasher.getSecurePassword(vPass);
            System.out.println(securePassword3);

            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();
            
            String query1 = "select * from customers where cUsername = ? and email = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, email);
            ResultSet rs;
            rs = preparedStatement1.executeQuery();
            if(rs.next()){  
                
            if (!securePassword2.equals(securePassword3)) {
                return false;
            } 
                else {
                String query = "update accounts set pass = ?, active = ? where username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, securePassword3);
                preparedStatement.setInt(2, 1);
                preparedStatement.setString(3, username);

                int rowUpdated = preparedStatement.executeUpdate();
                System.out.println(preparedStatement.toString());
                System.out.println(rowUpdated);

                return rowUpdated > 0;
            }
        }
            System.out.println("Account does not exist");
        } catch (SQLException ex) {
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean toLock(String username) throws SQLException {
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();
            Date register_date;
            String query = "select * from accounts where username = ? and active = ? and locked_date is null";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, 0);
            result = preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());

            if (result.next()) {
                register_date = Timestamp.valueOf(result.getTimestamp("register_date").toString());
                System.out.println("Register date: " + register_date);
                long diff = currentTimestamp.getDate() - register_date.getDate();
                System.out.println("Date diff: " + diff);
                return diff >= 1;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("error at authenticator.java toLock");
        }
        return false;
    }

    public boolean lockAccount(Beans.User user) {
        try {
            dBConnectionFactory = DBConnectionFactory.getInstance();
            connection = dBConnectionFactory.getConnection();
            Timestamp locked_date = currentTimestamp;
            Timestamp register_date = null;
            int rowUpdated;
            String dateQuery = "select register_date from accounts where userID = ?";
            PreparedStatement ps = connection.prepareStatement(dateQuery);
            ps.setInt(1, user.getUserID());
            result = ps.executeQuery();

            if (result.next()) {
                register_date = result.getTimestamp("register_date");
            }

            String query = "update accounts set register_date = ?, locked_date = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, register_date);
            preparedStatement.setTimestamp(2, locked_date);
            preparedStatement.setString(3, user.getUsername());
            rowUpdated = preparedStatement.executeUpdate();
            System.out.println(preparedStatement.toString());

            if (rowUpdated > 0) {
                user.setIsLocked(true);
            }
            return rowUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("error at authenticator.java lockaccount");
        }
        return false;
    }

    @Override
    public void clearCurrent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login() throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyPassword(User user, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User createUser(String string, String string1, String string2) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateStrongPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateStrongPassword(User user, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changePassword(User user, String string, String string1, String string2) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set getUserNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrentUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String hashPassword(String string, String string1) throws EncryptionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(String string) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verifyAccountNameStrength(String string) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verifyPasswordStrength(String string, String string1, User user) throws AuthenticationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
