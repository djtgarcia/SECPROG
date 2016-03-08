<%-- 
    Document   : managerlogout
    Created on : 02 22, 16, 8:44:33 PM
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
        <br><br><br><br>
        <div class="col-sm-4 col-md-4 col-lg-4 col-md-offset-4 text-center">
            <h2><i class="fa fa-check fa-5x" style="color: green"></i></h2>
            <legend>Logout Successful</legend>
            <p>You have successfully logged out of Foobar Manager!</p>
            <a class="btn btn-default" href="managerlogin.jsp">Login to Foobar Manager</a> <a class="btn btn-success" href="index.jsp">Foobar Home</a>
        </div>

        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
