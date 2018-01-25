$(document).ready(function(){
    setup();
    getDepartingFlights();
    getReturningFlights();

});
var valid = false;
function setup() {
    var date_input=$('input[name="departureDate"]'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input.datepicker({
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    });
    var date_input2=$('input[name="arrivalDate"]'); //our date input has the name "date"
    var container2=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input2.datepicker({
        format: 'dd/mm/yyyy',
        container: container2,
        todayHighlight: true,
        autoclose: true,
    });


    /!*disable non active tabs*!/
    $('.nav li').not('.active').addClass('disabled');
    /!*to actually disable clicking the bootstrap tab, as noticed in comments by user3067524*!/
    $('.nav li').not('.active').find('a').removeAttr("data-toggle");

    $('button').click(function(){
        if(valid) {
            /!*enable next tab*!/
            $('.nav li.active').next('li').removeClass('disabled');
            $('.nav li.active').next('li').find('a').attr("data-toggle", "tab");
            var $tabs = $('.nav-widget-acc li');
            $tabs.filter('.active').next('li').find('a[data-toggle="tab"]').tab('show');
        }
    });

    $('#flight-tab').click(function () {
        $('.nav li').not('.active').next('li').addClass('disabled');
        /!*to actually disable clicking the bootstrap tab, as noticed in comments by user3067524*!/
        $('.nav li').not('.active').next('li').find('a').removeAttr("data-toggle");

    });

    $('#itinerary-tab').click(function () {
        $('.nav li').next('li').next('li').addClass('disabled');
        /!*to actually disable clicking the bootstrap tab, as noticed in comments by user3067524*!/
        $('.nav li').next('li').next('li').find('a').removeAttr("data-toggle");

    });
}
var departing = [];
var returning = [];

function displayDepartingFlights(data)
{
    var table =  $('#table-departing-flights').DataTable( {
        destroy: true,
        "oLanguage": {
            "sSearch": "<span>Search</span> _INPUT_" //search
        },
        aaData: data.departingFlights,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"},
            { mData: "Arrival" },
            { mData: "Economy Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="1" name="reservation_depart" id="res-eco-dep" required="">'+
                           '<label class="option" for="res-eco-dep">' + full["Economy Price"] +'</label>';
                    }

                    return data;
                }  },
            { mData: "Business Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="2" name="reservation_depart" id="res-buss-dep" required="">'+
                            '<label class="option" for="res-buss-dep">' + full["Business Price"] +'</label>';
                    }

                    return data;
                }  },
            { mData: "First Class Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="3" name="reservation_depart" id="res-fclass-dep" required="">'+
                            '<label class="option" for="res-fclass-dep">' + full["First Class Price"] +'</label>';
                    }

                    return data;
                }  },
        ]
    } );

    $('#table-departing-flights tbody').on('change', '#res-eco-dep', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            departing[i++] = $(this).text();
        });
        departing[i++] = "Economy";
    });

    $('#table-departing-flights tbody').on('change', '#res-buss-dep', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            departing[i++] = $(this).text();
        });
        departing[i++] = "Business";
    });

    $('#table-departing-flights tbody').on('change', '#res-fclass-dep', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            departing[i++] = $(this).text();
        });
        departing[i++] = "First Class";
    });
}

function getDepartingFlights()
{
    var data = {request : 'getDepartingFlights'};
    callAjax(data);
}


function displayReturningFlights(data)
{
    var table =  $('#table-returning-flights').DataTable( {
        destroy: true,
        "oLanguage": {
            "sSearch": "<span>Search</span> _INPUT_" //search
        },
        aaData: data.returningFlights,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"},
            { mData: "Arrival" },
            { mData: "Economy Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="1" name="return" id="res-eco-ret" required="">'+
                            '<label class="option" for="res-eco-ret">' + full["Economy Price"] +'</label>';
                    }

                    return data;
                }  },
            { mData: "Business Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="2" name="return" id="res-buss-ret" required="">'+
                            '<label class="option" for="res-buss-ret">' + full["Business Price"] +'</label>';
                    }

                    return data;
                }  },
            { mData: "First Class Price",
                "mRender": function(data, type, full, meta){
                    if(type === 'display'){
                        data = '<input type="radio" class="form-radio" value="3" name="return" id="res-fclass-ret" required="">'+
                            '<label class="option" for="res-fclass-ret">' + full["First Class Price"] +'</label>';
                    }

                    return data;
                }  },
        ]
    } );

    $('#table-returning-flights tbody').on('change', '#res-eco-ret', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            returning[i++] = $(this).text();
        });
        returning[i++] = "Economy";
    });

    $('#table-returning-flights tbody').on('change', '#res-buss-ret', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            returning[i++] = $(this).text();
        });
        returning[i++] = "Business";
    });

    $('#table-returning-flights tbody').on('change', '#res-fclass-ret', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            returning[i++] = $(this).text();
        });
        returning[i++] = "First Class";
    });
}

function getReturningFlights()
{
    var data = {request : 'getReturningFlights'};
    callAjax(data);
}

function bookFlight() {

   if($('input[type=radio]:checked').length == 2)
       valid = true;
   else
       alert("Please select a flight");

    if (valid) {

        $("#chooseFlightForm").submit(function (e) {
            e.preventDefault();
        });

        var requestFrom = "bookFlight";

        var departingdata = "dflightNumber=" + departing[0] + "&ddeparts=" + departing[1] + "&ddepartureDate=" + departing[2] + "&ddeparture=" +
            departing[3] + "&darrives=" + departing[4] + "&darrivalDate=" + departing[5] + "&darrival=" + departing[6] + "&decoPrice=" + departing[7] +
            "&dbussPrice=" + departing[8] + "&dfclassPrice=" + departing[9] + "&dseatType=" + departing[10];

        var returningData = "&rflightNumber=" + returning[0] + "&rdeparts=" + returning[1] + "&rdepartureDate=" + returning[2] + "&rdeparture=" +
            returning[3] + "&rarrives=" + returning[4] + "&rarrivalDate=" + returning[5] + "&rarrival=" + returning[6] + "&recoPrice=" + returning[7] +
            "&rbussPrice=" + returning[8] + "&rfclassPrice=" + returning[9] + "&rseatType=" + returning[10];


        var data = departingdata + returningData + "&request=" + requestFrom;
        callAjax(data);
    }
}



function displayItinDepartingFlights(data)
{
    var table =  $('#table-itin-departing').DataTable( {
        destroy: true,
        paginate: false,
        searching: false,
        aaData: data.departingFlight,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"},
            { mData: "Arrival" },
            { mData: "Class Type" },
            { mData: "Price" }
        ]
    } );
}


function displayItinReturningFlights(data)
{
    var table =  $('#table-itin-returning').DataTable( {
        destroy: true,
        paginate: false,
        searching: false,
        aaData: data.returningFlight,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"},
            { mData: "Arrival" },
            { mData: "Class Type" },
            { mData: "Price" }
        ]
    } );
}

function displayCustomerDetails(data) {
    var table =  $('#table-itin-customer-details').DataTable( {
        destroy: true,
        paginate: false,
        searching: false,
        aaData: data.customerDetails,
        aoColumns: [
            { mData: "Name" },
            { mData: "Email" },
            { mData: "Contact Number" }
        ]
    } );
}

function flightBooked(data) {
    if (data.success)
        alert("booking in session");

    if (data.reservationType == "return")
        displayItinReturningFlights(data);
    else if (data.reservationType == "one-way")
        $('#itin-returning').hide();

    displayItinDepartingFlights(data);
    displayCustomerDetails(data);
}

function getPaymentDetails() {

    var data = {request: 'getPaymentDetails'};
    callAjax(data);
}

function showPaymentTable(data) {
    var table =  $('#table-payment').DataTable( {
        destroy: true,
        paginate: false,
        searching: false,
        aaData: data.paymentDetails,
        aoColumns: [
            { mData: "Fare" },
            { mData: "Taxes" },
            { mData: "Total" }
        ]
    } );
}

function makePayment() {
    var data = {request : 'makePayment'};
    callAjax(data);
}

function bookingAdded(data) {
    if (data.success) {
       sendMail();
    }
    else
        alert("error");
}

function sendMail() {
    var data = {request : 'sendMail'};
    callAjax(data);
}

function mailSent(data) {
    if(data.success) {
        alert("flight booked");
        window.location = "../index.jsp";
    }
    else alert("error");
}

function callAjax(data) {

    $.ajax({
        type: "POST",
        url: "/BookingServlet",
        data: data,
        dataType: "json",
        success: function (data) {
            if(data.request == "getDepartingFlights")
                displayDepartingFlights(data);
            else if(data.request == "getReturningFlights")
                displayReturningFlights(data);
            else if(data.request == "bookFlight")
                flightBooked(data);
            else if(data.request == "getPaymentDetails")
                showPaymentTable(data);
            else if(data.request == "makePayment")
                bookingAdded(data);
            else if(data.request == "sendMail")
                mailSent(data);

        }
    });

}