<%-- 
    Document   : adminCreate
    Created on : 02 22, 16, 8:12:20 PM
    Author     : Arveen
--%>

<%@page import="Beans.User"%>
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
            String type = null;
            String username = null;
            User loggedInUser = null;
            if (session.getAttribute("loggedInUser") == null) {
                response.sendRedirect("managerlogin.jsp");
            } else {
                loggedInUser = (User) session.getAttribute("loggedInUser");
                type = loggedInUser.getType();
                if ("Administrator".equals(type)) {
                    username = loggedInUser.getUsername();
                } else {
                    response.sendRedirect("accessdenied.jsp");
                }
            }

            Cookie[] cookies = request.getCookies();
            String alert = null;
            String message = null;
            System.out.println(cookies.length);
            for (Cookie cookie : cookies) {
                System.out.println("cookie: " + cookie.getName());
                if (cookie.getName().equals("message")) {
                    message = cookie.getValue();
                    //System.out.println("success");
                    System.out.println("message:" + message);
                    cookie.setMaxAge(2);
                    response.addCookie(cookie);

                }
                if (cookie.getName().equals("alert")) {
                    alert = cookie.getValue();
                    //System.out.println("success");
                    System.out.println("alert:" + alert);
                    cookie.setMaxAge(2);
                    response.addCookie(cookie);
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
                <a class="navbar-brand" href="#">Foobar Bookshop</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="adminIndex.jsp">Dashboard</a></li>
                    <li class="active"><a href="adminCreate.jsp">Create</a></li>
                    <li><a href="adminUnlock.jsp">Unlock</a></li>
                    <li><a href="adminRemove.jsp">Remove</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-user"></i><%= username%> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <!-- <li><a href="#"><i class="fa fa-fw fa-user"></i>My Account</a></li> -->
                            <li><a href="Logout"><i class="fa fa-fw fa-sign-out"></i>Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
        <div class="col-sm-4 col-md-4 col-lg-4 col-md-offset-4">
            <%
                if (alert != null && message != null) {
                    if (alert.equals("success")) {
                        out.print("<div class=\"alert alert-success alert-dismissable\" id=\"alert-succes\" style=\"display: block;text-align: center\">");
                        out.print("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
                        out.print(message);
                        out.print("</div>");
                    } else if (alert.equals("failed")) {
                        out.print("<div class=\"alert alert-danger alert-dismissable\" id=\"alert-danger\" style=\"display: block;text-align: center\">");
                        out.print("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
                        out.print(message);
                        out.print("</div>");
                    }
                }
            %>
            <h3 class="text-center"><i class="fa fa-group fa-5x"></i></h3>
            <form action="CreateAccount" method="POST" role="form">
                <legend class="text-center">Create an Account</legend>

                <div class="form-group">
                    <label for="">Username</label>
                    <input type="text" name="username" class="form-control" id="" placeholder="Username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" title="Alphanumeric; 8-20 characters only" required autofocus>
                </div>

                <div class="form-group">
                    <label for="">Temporary Password</label>
                    <input type="password" name="password" class="form-control" id="" placeholder="Password" pattern="(?=^.{8,45}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required autofocus>
                </div>
                <div class="col-md-6">
                    <div class="radio">
                        <label>
                            <input type="radio" name="type" id="input" value="Book Manager"required>
                            Book Manager
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="type" id="input" value="Magazine Manager" required>
                            Magazine Manager
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="type" id="input" value="CD Manager" required>
                            Audio CD Manager
                        </label>
                    </div>

                    <div class="radio">
                        <label>
                            <input type="radio" name="type" id="input" value="DVD Manager" required>
                            DVD Manager
                        </label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="radio">
                        <label>
                            <input type="radio" name="type" id="input" value="Accounting Manager" required>
                            Accounting Manager
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Submit</button>
            </form>
        </div>
        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>
