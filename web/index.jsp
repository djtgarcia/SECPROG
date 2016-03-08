<%@page import="Beans.User"%>
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
            if (session.getAttribute("loggedInUser")!=null) {
                loggedInUser = (User) session.getAttribute("loggedInUser");
                type = loggedInUser.getType();
                if (loggedInUser.getIsLocked()) {
                    response.sendRedirect("accountlocked.jsp");
                }
                if ("Customer".equals(type)) {
                    username = loggedInUser.getUsername();
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
                    <% if (username == null) {%>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="register.jsp">Register</a></li>
                    <%} else {%>
                        <li><a href="search.jsp">Search Products</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-user"></i><%= username %><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="account.jsp"><i class="fa fa-fw fa-user"></i>My Account</a></li>
                                <li><a href="Logout"><i class="fa fa-fw fa-sign-out"></i>Logout</a></li>
                            </ul>
                        </li>
                    <%}%>
                    <!-- <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li><a href="#">Separated link</a></li>
                            </ul>
                    </li> -->
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
        <div class="jumbotron text-center">
            <div class="container">
                <h1>Foobar Bookshop</h1>
                <p>Online Bookstore</p>
                <p>
                    <% if (username == null) {%>
                    <a class="btn btn-success btn-lg" href="login.jsp">Login</a> <a class="btn btn-primary btn-lg" href="register.jsp">Register</a>
                    <%} else {%>
                    <a class="btn btn-success btn-lg" href="search.jsp">Search for Products</a>
                    <%}%>
                </p>
            </div>
        </div>
        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>