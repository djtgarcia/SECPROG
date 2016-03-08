<%-- 
    Document   : adminIndex
    Created on : 02 22, 16, 8:12:34 PM
    Author     : Arveen
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="Beans.User"%>
<%@page import="java.sql.Timestamp"%>
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
            String trClass = null;

            Date currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
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
                    <li class="active"><a href="adminIndex.jsp">Dashboard</a></li>
                    <li><a href="adminCreate.jsp">Create</a></li>
                    <li><a href="adminUnlock.jsp">Unlock</a></li>
                    <li><a href="adminRemove.jsp">Remove</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown active">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"><%= username%><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="active"><a href="Logout"><i class="fa fa-fw fa-sign-out"></i>Log out</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>
