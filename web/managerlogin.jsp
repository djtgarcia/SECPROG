<%-- 
    Document   : managerlogin
    Created on : 02 22, 16, 8:44:40 PM
    Author     : Arveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="js/jquery-1.12.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/npm.js"></script>

        <style type="text/css">
            body {
                background: #fafafa;
            }
            * {
                font-family: "Open Sans", sans-serif;
            }
            .jumbotron {
                border: none;
                box-shadow: none;
            }
        </style>
    </head>
    <body>
        <%
            String type;
            if(session.getAttribute("type")!=null){
                type = (String) session.getAttribute("type");
                if ("Customer".equals(type)) {
                    response.sendRedirect("account.jsp");
                }
                else if ("Administrator".equals(type)) {
                    response.sendRedirect("adminIndex.jsp");
                }
                else if ("Book Manager".equals(type) || "Magazine Manager".equals(type) || "CD Manager".equals(type) || "DVD Manager".equals(type)) {
                    response.sendRedirect("productIndex.jsp");
                }
                else if ("Accounting Manager".equals(type)) {
                    response.sendRedirect("accountingIndex.jsp");
                }
            }
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            String output; 
        %>
        <br><br>
        <div class="col-sm-4 col-md-4 col-lg-4 col-md-offset-4">
            <%
                if (cookies != null) {
                    for (javax.servlet.http.Cookie cookie : cookies) {
                        if (cookie.getName().equals("output")) {
                            output = cookie.getValue();
                            cookie.setMaxAge(0);
                            out.print("<div class=\"alert alert-danger alert-dismissable\" id=\"alert-danger\" style=\"display: block;text-align: center\">");
                            out.print(output);
                            out.print("</div>");
                        }
                    }
                }
            %>
            <form action="ManagerLogin" method="POST" role="form">
                <legend>Login to Foobar Manager</legend>

                <div class="form-group">
                    <label for="">Username</label>
                    <input type="text" name="username" class="form-control" id="" placeholder="Username" required>
                </div>

                <div class="form-group">
                    <label for="">Password</label>
                    <input type="password" name="password" class="form-control" id="" placeholder="Password"required>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
