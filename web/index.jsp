<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- custom stylesheet -->
    <link rel="stylesheet" href="css/style.css">

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
                String s = session.getAttribute("id").toString();
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
            <li class="active"><a href="index.jsp">Book a Flight<span class="sr-only">(current)</span></a></li>
            <li><a href="schedule.jsp">Schedule<span class="sr-only">(current)</span></a></li>
          </ul>
        </div>
      </div>
    </div>
  </header>

  <!-- carousel -->
  <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
      <li data-target="#carousel-example-generic" data-slide-to="2"></li>

      <li data-target="#carousel-example-generic" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="img/slide2.jpg" alt="...">
      </div>
      <div class="item">
        <img src="img/slide1.jpg" alt="...">
      </div>
      <div class="item">
        <img src="img/slide4.jpg" alt="...">
      </div>
      <div class="item">
        <img src="img/slide3.jpg" alt="...">
      </div>
    </div>
  </div>
  <!--end carousel -->

  <!-- main -->
  <div class="main">
    <div class="container-fluid">
      <div class="nav-widget">
        <nav class="nav-widget-acc">
          <!-- Nav tabs -->
          <ul class="nav nav-pills nav-justified" role="tablist">
            <li role="presentation" class="active"><a href="#flights" aria-controls="flights" role="tab" data-toggle="tab">Flights</a></li>
            <%--<li role="presentation"><a href="#my-booking" aria-controls="my-booking" role="tab" data-toggle="tab">My Booking</a></li>
          --%></ul>

          <!-- Tab panes -->
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active fade in" id="flights">
              <div class="container form-flights">
                <form name="bookFlightForm" action="BookingServlet" method="POST">
                  <div class="container-fluid form-radios">
                    <div class="row">
                      <div class="col-sm-2 col-sm-offset-1">
                        <input type="radio" class="form-radio" value="1" name="reservation_type" id="res-round" required="" checked="">
                        <label class="option" for="res-round">Return</label>
                      </div>
                      <div class="col-sm-2">
                        <input type="radio" class="form-radio" value="2" name="reservation_type" id="res-one-way" required="">
                        <label class="option" for="res-one-way">One-way</label>
                      </div>
                    </div>
                  </div>
                  <hr>
                  <div class="flight-form">
                    <div class="container-fluid ">
                      <div class="row">
                        <div class="form-group col-sm-5 col-sm-offset-1">
                          <label class="control-label col-sm-3" for="flight-from">From:</label>
                          <div class="col-sm-8 input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-plane"></i></span>
                            <input type="textarea" class="form-control" id="flight-from" name="flight-from" placeholder="From">
                          </div>
                        </div>
                        <div class="form-group col-sm-5">
                          <label class="control-label col-sm-3" for="flight-to">To:</label>
                          <div class="col-sm-8 input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-plane"></i></span>
                            <input type="textarea" class="form-control" id="flight-to" name="flight-to" placeholder="To">
                          </div>
                        </div>
                      </div>
                      <hr>
                      <div class="bootstrap-iso">
                        <div class="row">
                          <div class="col-sm-5 col-sm-offset-1">
                            <div class="form-group ">
                              <label class="control-label col-sm-3" for="date-depart">Departure Date:</label>
                              <div class="col-sm-8 input-group">
                                <div class="input-group-addon">
                                  <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control" id="date-depart" name="date-depart" placeholder="DD/MM/YYYY" type="text"/>
                              </div>
                            </div>
                          </div>
                          <div class="col-sm-5" id="return-date">
                            <div class="form-group ">
                              <label class="control-label col-sm-3" for="date-return">Return Date:</label>
                              <div class="col-sm-8 input-group">
                                <div class="input-group-addon">
                                  <i class="fa fa-calendar"></i>
                                </div>
                                <input class="form-control" id="date-return" name="date-return" placeholder="DD/MM/YYYY" type="text"/>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <hr>
                      <%--<div class="row">
                        <div class="col-sm-8 col-sm-offset-1">
                            <li class="col-sm-2 col-xs-6">
                              <div class="pax_type">
                                <label for="num_seats">Seats</label>
                                <select class="form-control" id="num_seats" name="num_seats">
                                  <option value="1" selected="selected">1</option>
                                  <option value="2">2</option>
                                  <option value="3">3</option>
                                  <option value="4">4</option>
                                  <option value="5">5</option>
                                </select>
                              </div>
                            </li>
                        </div>
                      </div>--%>
                    </div>
                    <div class="row">
                      <div class="col-sm-offset-5 col-xs-offset-3">
                        <button type="button" onclick="startBooking()" class="btn btn-success btn-lg btn-proceed">Proceed</button>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
<%--
            <div role="tabpanel" class="tab-pane fade" id="my-booking">
              <div class="container-fluid form-search-booking fade in" id="form-search-booking">
                <div class="row">
                  <div class="form-group col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-3">
                    <label class="control-label col-sm-3 col-xs-3" for="PNR">Email:</label>
                    <div class="col-sm-8 col-xs-6 input-group">
                      <input type="text" class="form-control" id="PNR" placeholder="Enter PNR">
                    </div>
                  </div>
                </div>
                <div class="row pnr">
                  <li class="col-sm-8 col-xs-8 col-sm-offset-2 col-xs-offset-2"> <br>
                    What is <strong>PNR</strong>?<br>
                    A Passenger Name Record(PNR) is a travel record, used by airlines and travel agency databases. The 6 letters PNR is located at the top of your ticket under the Booking Reference heading. </li>
                </div>
                <div class="row">
                  <div class="col-sm-offset-5 col-xs-offset-3">
                    <button type="button" class="btn btn-success btn-lg btn-view-booking" id="btn-view-booking">View Booking</button>
                  </div>
                </div>

              </div>
                <div class="container-fluid form-my-booking fade in" id="form-my-booking">
                    <div class="panel panel-primary itin-panel">
                      <div class="panel-heading">
                        <span style="margin:15px"><i class="glyphicon glyphicon-plane"></i></span>
                        Departing Flight
                      </div>
                      <div class="panel-body">
                        <div class="table-responsive">
                          <table class="table table-bordered">
                            <th>Date</th>
                            <th>Flight Number</th>
                            <th>Class Type</th>
                            <th>Departs</th>
                            <th>Departure</th>
                            <th>Arrives</th>
                            <th>Arrival</th>
                            <tr>
                              <td>10-9-2017</td>
                              <td>PK 707</td>
                              <td>Economy</td>
                              <td>6:00 pm</td>
                              <td>Lahore</td>
                              <td>8:00 pm</td>
                              <td>Karachi</td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="panel panel-primary itin-panel">
                      <div class="panel-heading">
                        <span style="margin:15px"><i class="glyphicon glyphicon-plane"></i></span>
                        Returning Flight
                      </div>
                      <div class="panel-body">
                        <div class="table-responsive">
                          <table class="table table-bordered">
                            <th>Date</th>
                            <th>Flight Number</th>
                            <th>Class Type</th>
                            <th>Departs</th>
                            <th>Departure</th>
                            <th>Arrives</th>
                            <th>Arrival</th>
                            <tr>
                              <td>15-9-2017</td>
                              <td>PK 609</td>
                              <td>Economy</td>
                              <td>3:45 pm</td>
                              <td>Karachi</td>
                              <td>5:40 pm</td>
                              <td>Lahore</td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="panel panel-primary itin-panel">
                      <div class="panel-heading">
                        <span style="margin:15px"><i class="glyphicon glyphicon-user"></i></span>
                        Details
                      </div>
                      <div class="panel-body">
                        <div class="table-responsive">
                          <table class="table table-bordered">
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Contact Number</th>
                            <tr>
                              <td>Saad</td>
                              <td>Mujeeb</td>
                              <td>saad@hotmail.com</td>
                              <td>03454320123</td>
                            </tr>
                            <tr>
                              <td>Ahmed</td>
                              <td>Shabbir</td>
                              <td>ahmed@hotmail.com</td>
                              <td>03455684268</td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="form-group col-sm-12">
                        <div class="col-sm-offset-5 col-sm-3 col-xs-offset-4">
                          <button type="button" class="btn btn-success btn-lg btn-search-booking" id="btn-search-booking">Search</button>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
         --%>
          </div>
        </nav>
      </div>
    </div>
  </div>
  <!-- end main -->

  <!--offers-->
  <div class="container-fluid">
    <div class="offers">
      <div class="row">
        <h3>Latest Offers</h3>
        <hr>
      </div>
      <div class="row">
        <ul class="priceList">
          <li class="col-md-6">
            <a class="iframeclass" id="2386703">
              <img class="t19_img img-responsive" src="https://media.etihad.com/cms/webimage/Original/documents/destinations/cities/Bangkok_iHero.jpg.jpg" alt="">
              <!-- <img width="50" src="/Documents/Destinations/cities/Bangkok_iHero.jpg" alt="" /> -->
              <div class="details">
                <div class="offerDetails">
                  <div class="offerName">Bangkok</div>
                  <div>
                    <div class="offerClass">Economy</div>
                    <div class="offerExpires">
                      <div class="dateLabelExpire">
                        Book by
                      </div>
                      <div class="datePromotionExpire">12 Sep 2017</div>
                    </div>
                  </div>
                </div>
                <div class="priceDetails">
                  <div class="offerCurrency">from PKR</div>
                  <div class="offerPrice">43159<sup class="returnTripStar">*</sup></div>
                </div>
                <div class="departurePeriod">
                  <div class="dateLabelDeparture">Travel between </div>
                  <div class="datePromotionDeparture">5 Sep 2017 - 31 Mar 2018</div>
                </div>
              </div>
            </a>
          </li>
          <li class="col-md-6">
            <a class="iframeclass" id="2386704"><img class="t19_img" src="https://media.etihad.com/cms/webimage/Original/documents/destinations/cities/New-York_iHero.jpg.jpg" alt="">
              <!-- <img width="50" src="/Documents/Destinations/cities/Bangkok_iHero.jpg" alt="" /> -->
              <div class="details">
                <div class="offerDetails">
                  <div class="offerName">New York</div>
                  <div>
                    <div class="offerClass">Economy</div>
                    <div class="offerExpires">
                      <div class="dateLabelExpire">
                        Book by
                      </div>
                      <div class="datePromotionExpire">12 Sep 2017</div>
                    </div>
                  </div>
                </div>
                <div class="priceDetails">
                  <div class="offerCurrency">from PKR</div>
                  <div class="offerPrice">112642<sup class="returnTripStar">*</sup></div>
                </div>
                <div class="departurePeriod">
                  <div class="dateLabelDeparture">Travel between </div>
                  <div class="datePromotionDeparture">5 Sep 2017 - 31 Mar 2018</div>
                </div>
              </div>
            </a>
          </li>

          <li  class="col-md-6">
            <a class="iframeclass" id="2386705"><img class="t19_img" src="https://media.etihad.com/cms/webimage/Original/documents/destinations/cities/Medina_iHero.jpg.jpg" alt="">
              <!-- <img width="50" src="/Documents/Destinations/cities/Bangkok_iHero.jpg" alt="" /> -->
              <div class="details">
                <div class="offerDetails">
                  <div class="offerName">Medina</div>
                  <div>
                    <div class="offerClass">Economy</div>
                    <div class="offerExpires">
                      <div class="dateLabelExpire">
                        Book by
                      </div>
                      <div class="datePromotionExpire">12 Sep 2017</div>
                    </div>
                  </div>
                </div>
                <div class="priceDetails">
                  <div class="offerCurrency">from PKR</div>
                  <div class="offerPrice">145512<sup class="returnTripStar">*</sup></div>
                </div>
                <div class="departurePeriod">
                  <div class="dateLabelDeparture">Travel between </div>
                  <div class="datePromotionDeparture">5 Sep 2017 - 31 Mar 2018</div>
                </div>
              </div>
            </a>
          </li>
          <li  class="col-md-6">
            <a class="iframeclass" id="2386706"><img class="t19_img" src="https://media.etihad.com/cms/webimage/Original/documents/destinations/cities/Manchester_iHero.jpg.jpg" alt="">
              <!-- <img width="50" src="/Documents/Destinations/cities/Bangkok_iHero.jpg" alt="" /> -->
              <div class="details">
                <div class="offerDetails">
                  <div class="offerName">Manchester</div>
                  <div>
                    <div class="offerClass">Economy</div>
                    <div class="offerExpires">
                      <div class="dateLabelExpire">
                        Book by
                      </div>
                      <div class="datePromotionExpire">12 Sep 2017</div>
                    </div>
                  </div>
                </div>
                <div class="priceDetails">
                  <div class="offerCurrency">from PKR</div>
                  <div class="offerPrice">285474<sup class="returnTripStar">*</sup></div>
                </div>
                <div class="departurePeriod">
                  <div class="dateLabelDeparture">Travel between </div>
                  <div class="datePromotionDeparture">5 Sep 2017 - 31 Mar 2018</div>
                </div>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>


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


  <script type="text/javascript" src="js/index.js"></script>

  </body>
</html>