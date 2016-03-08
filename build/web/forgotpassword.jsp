<%-- 
    Document   : forgotpassword
    Created on : 02 22, 16, 9:32:42 PM
    Author     : Arveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
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
        </style>
    </head>
    <body>
        <%
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            String output;
            String type;
            if (session.getAttribute("type") != null) {
                type = (String) session.getAttribute("type");
                if ("Customer".equals(type)) {
                    response.sendRedirect("account.jsp");
                } else if ("Administrator".equals(type)) {
                    response.sendRedirect("adminIndex.jsp");
                } else if ("Book Manager".equals(type) || "Magazine Manager".equals(type) || "CD Manager".equals(type) || "DVD Manager".equals(type)) {
                    response.sendRedirect("productIndex.jsp");
                } else if ("Accounting Manager".equals(type)) {
                    response.sendRedirect("accountingIndex.jsp");
                }
            }
        %>
        <nav class="navbar navbar-default" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp"> Foobar Bookshop</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
        <div class="col-sm-4 col-md-4 col-lg-4 col-md-offset-4">
            <%
                if (cookies != null) {
                    for (javax.servlet.http.Cookie cookie : cookies) {
                        if (cookie.getName().equals("output")) {
                            output = cookie.getValue();
                            cookie.setMaxAge(1);
                            out.print("<div class=\"alert alert-danger alert-dismissable\" id=\"alert-danger\" style=\"display: block;text-align: center\">");
                            out.print(output);
                            out.print("</div>");
                        }
                    }
                }
            %>
            <form action="ForgotPassword" method="POST" role="form">
                <legend class="text-center">Changing your password</legend>

                <div class="form-group">
                    <div class="col-sm-1">
                        <label for=""><i class="fa fa-fw fa-user fa-2x"></i></label>
                    </div>
                    <div class="col-sm-11">
                        <input type="text" name="username" class="form-control" id="" placeholder="Username">
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-sm-1">
                        <label for=""><i class="fa fa-fw fa-user fa-2x"></i></label>
                    </div>
                    <div class="col-sm-11">
                        <input type="text" name="email" class="form-control" id="" placeholder="Email Address" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-1">
                        <label for=""><i class="fa fa-fw fa-lock fa-2x"></i></label>
                    </div>
                    <div class="col-sm-11">
                        <input type="password" name="npass" class="form-control" id="" placeholder="New Password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required>
                    </div>
                </div>
                
                                <div class="form-group">
                    <div class="col-sm-1">
                        <label for=""><i class="fa fa-fw fa-lock fa-2x"></i></label>
                    </div>
                    <div class="col-sm-11">
                        <input type="password" name="vpass" class="form-control" id="" placeholder="Verify Password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required>
                    </div>
                </div>
                
                <div class="form-group text-center">
                    <div class="col-md-12">
                        <br>
                        <button type="submit" class="btn btn-info">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
