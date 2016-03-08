/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.User;
import Security.Authenticator;
//import Security.Authenticator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Arveen
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Authenticator authenticator = new Authenticator();
            HttpSession session = request.getSession();

            if (authenticator.createUser(request, response)) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                String username = loggedInUser.getUsername();

                Cookie alert = new Cookie("alert", "success");
                Cookie message = new Cookie("message", "Successfully created " + request.getParameter("type") + " Account!");
                alert.setMaxAge(2);
                message.setMaxAge(2);
                alert.setPath("/");
                message.setPath("/");
                response.addCookie(alert);
                response.addCookie(message);
//                org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateAccount.class);
//                PropertyConfigurator.configure("location ng lo4j.properties);
//                MDC.put("username", username);
 //               log.info("Account Created");
                response.sendRedirect("adminCreate.jsp");
           } else {
                Cookie alert = new Cookie("alert", "failed");
               Cookie message = new Cookie("message", "Failed to create " + request.getParameter("type") + " Account!");
               alert.setMaxAge(2);
                message.setMaxAge(2);
                alert.setPath("/");
                message.setPath("/");
                response.addCookie(alert); 
                response.addCookie(message);
                response.sendRedirect("adminCreate.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error at createaccount.java");
//            org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateAccount.class);
//            PropertyConfigurator.configure("location ng lo4j.properties");
//            log.error("Error at CreateAccount.java");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
