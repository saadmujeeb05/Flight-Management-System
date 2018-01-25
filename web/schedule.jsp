<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>My Airline</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->
    <!--     <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />-->


    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- custom stylesheet -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/signup.css">

</head>

<body>
<header>
    <div class="navbar navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" ><img src="img/logo.jpg"></a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <%
                if(session.getAttribute("id") != null) {
                %>
                <button type="button" onclick="logout()" class="btn btn-link navbar-btn navbar-right">Logout</button>
                <span class="btn btn-link navbar-btn navbar-right">${name}</span>

                <%
                }
                else{
                %>

                <button type="button" onclick="location.reload();location.href='login.html'" class="btn btn-link navbar-btn navbar-right">Log In</button>
                <button type="button" onclick="location.reload();location.href='signup.html'" class="btn btn-link navbar-btn navbar-right">Sign Up</button>
                <%
                }
                %>
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp">Book a Flight<span class="sr-only">(current)</span></a></li>
                    <li class="active"><a href="schedule.jsp">Schedule<span class="sr-only">(current)</span></a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="main">
    <div class="container schedule-container"  style="margin-top: 70px; margin-bottom: 50px">

        <div class="panel panel-primary itin-panel">
            <div class="panel-heading">
                <span style="margin:15px"><i class="glyphicon glyphicon-plane"></i></span>
                Flight Schedule
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="table-schedule">
                        <thead>
                        <th>Flight Number</th>
                        <th>Departs</th>
                        <th>Departure Date</th>
                        <th>Departure</th>
                        <th>Arrives</th>
                        <th>Arrival Date</th>
                        <th>Arrival</th>
                        <th>Economy Seats</th>
                        <th>Economy Price</th>
                        <th>Business Seats</th>
                        <th>Business Price</th>
                        <th>First Class Seats</th>
                        <th>First Class Price</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
       <!-- <div class="panel panel-primary itin-panel">
            <div class="panel-heading">
                <span style="margin:15px"><i class="glyphicon glyphicon-plane"></i></span>
                Schedule
            </div>
            <div class="panel-body">
                <div class="bootstrap-iso">
                    <div class="row">
                        <div class="form-group col-sm-5 col-sm-offset-1">
                            <label class="control-label col-sm-3" style="color: #000 !important;" for="flight-from">From:</label>
                            <div class="col-sm-8 input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-plane"></i></span>
                                <input type="textarea" class="form-control" id="flight-from" placeholder="From">
                            </div>
                        </div>
                        <div class="col-sm-5" id="sch-date">
                            <div class="form-group ">
                                <label class="control-label col-sm-3" style="color: #000 !important;" for="date-sch">Date:</label>
                                <div class="col-sm-8 input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <input class="form-control" id="date-sch" name="date" placeholder="DD/MM/YYYY" type="text"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <th>Date</th>
                        <th>Flight Number</th>
                        <th>Departs</th>
                        <th>Departure</th>
                        <th>Arrives</th>
                        <th>Arrival</th>
                    </table>
                </div>
            </div>
        </div>-->
    </div>
</div>
<!-- end main -->

<div id="footer" class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4">
                <h4 class="wow fadeInUp">Contact Us</h4>
                <p><i class="fa fa-home" aria-hidden="true"></i> 111 Main Street, Washington DC, 22222</p>
                <p><i class="fa fa-envelope" aria-hidden="true"></i> info@media.com</p>
                <p><i class="fa fa-phone" aria-hidden="true"></i> +1 222 222 2222</p>
                <p><i class="fa fa-globe" aria-hidden="true"></i> www.media.com</p>
            </div>
            <div class="col-lg-4 col-md-4">
                <h4>About</h4>
                <p><i class="fa fa-square-o" aria-hidden="true"></i> About Us</p>
                <p><i class="fa fa-square-o" aria-hidden="true"></i> Privacy</p>
                <p><i class="fa fa-square-o" aria-hidden="true"></i> Term & Condition</p>
            </div>
            <div class="col-lg-4 col-md-4">
                <h4>Stay in touch</h4>
                <i class="social fa fa-facebook" aria-hidden="true"></i>
                <i class="social fa fa-twitter" aria-hidden="true"></i>
                <i class="social fa fa-linkedin" aria-hidden="true"></i>
                <i class="social fa fa-pinterest" aria-hidden="true"></i>
                <i class="social fa fa-youtube" aria-hidden="true"></i>
                <i class="social fa fa-github" aria-hidden="true"></i><br>
                <input type="email" placeholder="Subscribe For Updates"><button class="btn btn-success">Subscribe</button>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/schedule.js"></script>
<script>
    $(document).ready(function() {
        var date_input = $('input[name="date"]'); //our date input has the name "date"
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'dd/mm/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    });
</script>
</body>
</html>