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
            .panel-body {
                background: #fcfcfc;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp"></i> Foobar Bookshop</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.jsp">Login</a></li>
                    <li class="active"><a href="register.jsp">Register</a></li>
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
        <div class="col-sm-8 col-md-8 col-lg-8 col-md-offset-2">
            <%
         //       if (alert != null && message != null) {
         //           if (alert.equals("failed")) {
         //               out.print("<div class=\"alert alert-danger alert-dismissable\" id=\"alert-danger\" style=\"display: block;text-align: center\">");
         //               out.print("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
         //               out.print(message);
         //               out.print("</div>");
         //           }
         //       }
            %>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Register to Foobar Bookshop</h3>
                </div>
                <div class="panel-body">
                    <form action="Register" method="POST" role="form">
                        <legend>Basic Information</legend>
                        <p>*All fields are required.</p>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">First Name</label>
                                <input type="text" name="fname" class="form-control" id="" placeholder="First Name" pattern="[a-zA-Z0-9\s]+" title="Alphanumeric w/ or w/o spaces" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Last Name</label>
                                <input type="text" name="lname" class="form-control" id="" placeholder="Last Name" pattern="[a-zA-Z0-9\s]+" title="Alphanumeric w/ or w/o spaces" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Username</label>
                                <input type="text" name="username" class="form-control" id="" placeholder="Username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{7,45}$" title="Alphanumeric, 8-45 characters only" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Email Address</label>
                                <input type="email" name="email" class="form-control" id="" placeholder="Email Address" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Password</label>
                                <input type="password" name="password" class="form-control" id="" placeholder="Password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Verify Password</label>
                                <input type="password" name="vpassword" class="form-control" id="" placeholder="Verify Password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Uppercase, Lowercase, Number/SpecialChar, min 8 chars" required>
                            </div>
                        </div>
                        <legend>Billing Address</legend>
                        <p>*All fields are required.</p>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">House Number</label>
                                <input type="text" name="bhouse" class="form-control" id="" data-validate="number" placeholder="House Number" pattern="[0-9]{3}" title="Numeric" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Street</label>
                                <input type="text" name="bstreet" class="form-control" id="" placeholder="Street" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric w/ or w/o spaces" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Subdivision/Village</label>
                                <input type="text" name="bsubdiv" class="form-control" id="" placeholder="Subdivision/Village" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric w/ or w/o spaces" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">City</label>
                                <input type="text" name="bcity" class="form-control" id="" placeholder="City" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric w/ or w/o spaces" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Postal Code</label>
                                <input type="text" name="bpcode" class="form-control" data-validate="number" id="" pattern="[0-9]{4}" title="PH Postal Code (4 digits)" placeholder="Postal Code" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Country</label>
                                <input type="text" name="bcountry" class="form-control" id="" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric w/ or w/o spaces" placeholder="Country" required>
                            </div>
                        </div>
                        <legend>Delivery Address</legend>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="" name="bcheckbox" onclick="sameAddress(this.form)">
                                Same as Billing Address
                            </label>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">House Number</label>
                                <input type="text" name="dhouse" class="form-control" id="" placeholder="House Number" pattern="[0-9]{3}" title="Numeric" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Street</label>
                                <input type="text" name="dstreet" class="form-control" id="" placeholder="Street" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Subdivision/Village</label>
                                <input type="text" name="dsubdiv" class="form-control" id="" placeholder="Subdivision/Village" pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">City</label>
                                <input type="text" name="dcity" class="form-control" id=""  pattern="[a-zA-Z0-9\s]+" title="AlphaNumeric" placeholder="City" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Postal Code</label>
                                <input type="text" name="dpcode" class="form-control" id=""  pattern="[0-9]{4}" title="PH Postal Code" placeholder="Postal Code" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Country</label>
                                <input type="text" name="dcountry" class="form-control" id="" pattern="[a-zA-Z0-9\s]+" title="Alphanumeric" placeholder="Country" required>
                            </div>
                        </div>
                        <div class="form-group text-center"><br><br><br>
                            <button type="submit" class="btn btn-primary btn-block">Sign Me Up!</button>
                        </div>
                        </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="jquery-1.11.1.min.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
                                    function sameAddress(f) {
                                        if (f.bcheckbox.checked == true) {
                                            f.dhouse.value = f.bhouse.value;
                                            f.dstreet.value = f.bstreet.value;
                                            f.dsubdiv.value = f.bsubdiv.value;
                                            f.dcity.value = f.bcity.value;
                                            f.dpcode.value = f.bpcode.value;
                                            f.dcountry.value = f.bcountry.value;

                                            f.dhouse.readOnly = true;
                                            f.dstreet.readOnly = true;
                                            f.dsubdiv.readOnly = true;
                                            f.dcity.readOnly = true;
                                            f.dpcode.readOnly = true;
                                            f.dcountry.readOnly = true;

                                        }
                                        else {
                                            f.dhouse.value = null;
                                            f.dstreet.value = null;
                                            f.dsubdiv.value = null;
                                            f.dcity.value = null;
                                            f.dpcode.value = null;
                                            f.dcountry.value = null;

                                            f.dhouse.readOnly = false;
                                            f.dstreet.readOnly = false;
                                            f.dsubdiv.readOnly = false;
                                            f.dcity.readOnly = false;
                                            f.dpcode.readOnly = false;
                                            f.dcountry.readOnly = false;
                                        }
                                    }
        </script>
    </body>
</html>