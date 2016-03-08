/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.User;
import Security.Authenticator;
import Security.Hasher;
import Security.Randomizer;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.errors.AuthenticationException;

/**
 *
 * @author Arveen
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, AuthenticationException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String address = null;
            String salt = null;
            String token = null;
            int loginAttempt = 0;
            User loggedInUser = null;
            HttpSession session = null;
            Hasher hashGenerator = new Hasher();
            Randomizer randomGenerator = new Randomizer();
            Authenticator authenticator = new Authenticator();

            if (authenticator.userExists(request.getParameter("username"))) {
                loggedInUser = (User) authenticator.login(request, response);

                if (loggedInUser != null && loggedInUser.getIsLoggedIn()) {
                    if (loggedInUser.getIsLocked()) {
                        response.sendRedirect("accountlocked.jsp");
                    } else {
                        request.getSession().invalidate();
                        session = request.getSession(true);

                        address = request.getRemoteAddr();
                        salt = Long.toString(randomGenerator.getRandomLong());
                        hashGenerator.updateHash(address, "UTF-8");
                        hashGenerator.updateHash(salt, "UTF-8");
                        token = hashGenerator.getHashBASE64();

                        session.setAttribute("address", address);
                        session.setAttribute("salt", salt);
                        session.setAttribute("token", token);
                        session.setAttribute("username", loggedInUser.getUsername());
                        session.setAttribute("type", loggedInUser.getType());
                        session.setAttribute("loggedInUser", loggedInUser);
                        session.removeAttribute("loginAttempt");
                        session.setMaxInactiveInterval(60 * 15);

                        if ("Customer".equals(loggedInUser.getType())) {
                            response.sendRedirect("account.jsp");
                        } else {
                            Cookie alert = new Cookie("output", "Invalid Username or Password");
                            alert.setMaxAge(1);
                            alert.setPath("/");
                            response.addCookie(alert);
                            System.out.println("ERROR at Login.java");
                            response.sendRedirect("login.jsp");
                        }
                    }
                } else {
                    session = request.getSession();
                    if (session.getAttribute("loginAttempt") == null) {
                        session.setAttribute("loginAttempt", loginAttempt + 1);
                        loginAttempt = (Integer) session.getAttribute("loginAttempt");
                        loggedInUser = authenticator.getCustomerAccount(request.getParameter("username"));
                        loggedInUser.setFailedLoginCount(loginAttempt);

                        System.out.println("Login attempts: " + loggedInUser.getFailedLoginCount());
                        if (loggedInUser.getFailedLoginCount() == 5) {
                            authenticator.lockAccount(loggedInUser);
                            loggedInUser.setFailedLoginCount(0);
                            response.sendRedirect("accountlocked.jsp");
                        } else {
                            session.setAttribute("loginAttempt", loginAttempt + 1);
                            Cookie alert = new Cookie("output", "Invalid Username or Password");
                            alert.setMaxAge(1);
                            alert.setPath("/");
                            response.addCookie(alert);
                            response.sendRedirect("login.jsp");
                        }
                    } else {
                        loginAttempt = (Integer) session.getAttribute("loginAttempt");
                        loggedInUser = authenticator.getCustomerAccount(request.getParameter("username"));
                        loggedInUser.setFailedLoginCount(loginAttempt);

                        System.out.println("Login attempts: " + loggedInUser.getFailedLoginCount());
                        if (loggedInUser.getFailedLoginCount() == 5) {
                            authenticator.lockAccount(loggedInUser);
                            loggedInUser.setFailedLoginCount(0);
                            response.sendRedirect("accountlocked.jsp");
                        } else {
                            session.setAttribute("loginAttempt", loginAttempt + 1);
                            Cookie alert = new Cookie("output", "Invalid Username or Password");
                            alert.setMaxAge(1);
                            alert.setPath("/");
                            response.addCookie(alert);
                            response.sendRedirect("login.jsp");
                        }
                    }
                }
            } else {
                Cookie alert = new Cookie("output", "Account does not exist. Please register a new account.");
                alert.setMaxAge(1);
                alert.setPath("/");
                response.addCookie(alert);
                System.out.println("ERROR at Login.java");
                response.sendRedirect("login.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
