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
        </style>
    </head>
     <body>
        <%
            String type = null;
            String username = null;
            String fullName = null;
            String email = null;
            String baddress = null;
            String daddress = null;
            User loggedInUser = null;
           // ArrayList<Transaction> transactions = null;
            if (session.getAttribute("loggedInUser") == null) {
                response.sendRedirect("login.jsp");
            } else {
                loggedInUser = (User) session.getAttribute("loggedInUser");
                type = loggedInUser.getType();
                if (loggedInUser.getIsLocked()) {
                    response.sendRedirect("accountlocked.jsp");
                }
                if ("Customer".equals(type)) {
                    username = loggedInUser.getUsername();
                    fullName = loggedInUser.getFname() + " " + loggedInUser.getLname();
                    email = loggedInUser.getEmail();
                    baddress = loggedInUser.getBaddress();
                    daddress = loggedInUser.getDaddress();
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
                <a class="navbar-brand" href="index.jsp"> Foobar Bookshop</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="search.jsp"><i class="fa fa-fw fa-search"></i>Search Products</a></li>
                    <li><a href="cart.jsp"><i class="fa fa-fw fa-shopping-cart"></i>My Cart</a></li>
                    <li class="dropdown active">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"><%= username%><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="active"><a href="account.jsp"><i class="fa fa-fw fa-user"></i>My Account</a></li>
                            <li class="active"><a href="Logout"><i class="fa fa-fw fa-sign-out"></i>Log out</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
                        
                        
        <div class="col-md-4">
            <div class="panel panel-info" id="account_info">
                <div class="panel-heading">
                    <h3 class="panel-title">Account Information</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="input">Username:</label>
                        <input type="text" name="" id="input" class="form-control" value="<%= username%>" readonly pattern="" title="">

                    </div>
                    <div class="form-group">
                        <label for="input">Full Name:</label>
                        <input type="text" name="" id="input" class="form-control" value="<%= fullName%>" readonly pattern="" title="">
                    </div>
                    <div class="form-group">
                        <label for="input">Email Address:</label>
                        <input type="text" name="" id="input" class="form-control" value="<%= email%>" readonly pattern="" title="">
                    </div>
                    <div class="form-group">
                        <label for="textarea">Billing Address:</label>
                        <textarea name="" id="input" class="form-control" value="" readonly pattern="" title=""><%= baddress%></textarea> 
                    </div>
                    <div class="form-group">
                        <label for="textarea">Delivery Address:</label>
                        <textarea name="" id="input" class="form-control" value="" readonly pattern="" title=""><%= daddress%></textarea> 
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">My Transactions</h3>
                </div>
                <div class="panel-body">
                    Nothing to show. 
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>
