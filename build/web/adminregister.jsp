<%-- 
    Document   : adminregister
    Created on : 02 22, 16, 8:13:35 PM
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
        <br><br><br>
        <div class="col-sm-4 col-md-4 col-lg-4 col-sm-offset-4 col-md-offset-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Admin Register</h3>
                </div>
                <div class="panel-body">
                    <form action="AdminRegister" method="POST" role="form">
                        <div class="form-group">
                            <label for="">Username</label>
                            <input type="text" name="username" class="form-control" id="" placeholder="Username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" title="Alphanumeric; 8-20 characters only" required autofocus>
                        </div>

                        <div class="form-group">
                            <label for="">Password</label>
                            <input type="password" name="password" class="form-control" id="" placeholder="Password" pattern="(?=^.{8,45}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required autofocus>
                        </div>


                        <button type="submit" class="btn btn-info">Submit</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
