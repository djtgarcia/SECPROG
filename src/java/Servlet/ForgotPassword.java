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
@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {

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
            Authenticator authenticator = new Authenticator();
            String newPass = request.getParameter("npass");
            String vPass = request.getParameter("vpass");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            HttpSession session = request.getSession();
            User loggedInUser = null;
             if (loggedInUser == null) {
                if (authenticator.forgotPassword(username, email, newPass, vPass)) {
                    Cookie alert = new Cookie("output", "Password successfully changed");
                    Cookie message = new Cookie("message", "Successfully changed password!");
                    alert.setMaxAge(2);
                    message.setMaxAge(2);
                    alert.setPath("/");
                    message.setPath("/");
                    response.addCookie(alert);
                    response.addCookie(message);
                    response.sendRedirect("forgotpassword.jsp");
                    } else {
                    Cookie alert = new Cookie("output", "Password change failed. Please try again.");
                    Cookie message = new Cookie("message", "Failed to change password");
                    alert.setMaxAge(2);
                    message.setMaxAge(2);
                    alert.setPath("/");
                    message.setPath("/");
                    response.addCookie(alert);
                    response.addCookie(message);
                    response.sendRedirect("forgotpassword.jsp");
                }
            } else {
                response.sendRedirect("accessdenied.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error at changepassword.java");
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
