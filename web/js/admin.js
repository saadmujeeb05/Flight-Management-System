$(document).ready(function(){
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

    getAllFlights();
    getTentativeFlights();

});


function displayFlights(data)
{
    var table =  $('#table-schedule').DataTable( {
        destroy: true,
        "oLanguage": {
            "sSearch": "<span>Search</span> _INPUT_" //search
        },
        aaData: data.allFlights,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"} ,
            { mData: "Arrival" },
            { mData: "Economy Seats" },
            { mData: "Economy Price" },
            { mData: "Business Seats" },
            { mData: "Business Price" },
            { mData: "First Class Seats" },
            { mData: "First Class Price" }
        ]
    } );
}

function getTentativeFlights() {
    var data = {request : 'getTentativeFlights', flightType: 'TentativeFlights'};
    callAjax(data);
}


function getAllFlights()
{
    var data = {request : 'getAllFlights', flightType: 'Schedule'};
    callAjax(data);
}

var a = [];

function displayTentativeFlights(data)
{
   var table =  $('#table-manage-flights').DataTable( {
       destroy: true,
       "oLanguage": {
           "sSearch": "<span>Search</span> _INPUT_" //search
       },
        aaData: data.allFlights,
        aoColumns: [
            { mData: "Flight Number" },
            { mData: "Departs" },
            { mData: "Departure Date" },
            { mData: "Departure" },
            { mData: "Arrives" },
            { mData: "Arrival Date"} ,
            { mData: "Arrival" },
            { mData: "Economy Seats" },
            { mData: "Economy Price" },
            { mData: "Business Seats" },
            { mData: "Business Price" },
            { mData: "First Class Seats" },
            { mData: "First Class Price" },
            { mData: "Actions",
                "mRender": function() {
                    return '<button type="button" id="update" class="btn btn-link" style="color: #337ab7 !important;">Update</button>' +
                        '<button type="button" id="delete" class="btn btn-link" style="color: #337ab7 !important;">Delete</button>';
                }  }
        ]

    } );

    $('#table-manage-flights tbody').off('click','#update').on('click', '#update', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            a[i++] = $(this).text();
        });
        fillFlightData(a);
        $('#updateFlightModal').modal('show');
    });

    $('#table-manage-flights tbody').off('click','#delete').on('click', '#delete', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            a[i++] = $(this).text();
        });

        var requestFrom = "deleteFlight";
        var flightType = "TentativeFlights";
        var tableData = "flightNumber=" + a[0] + "&departs=" + a[1] + "&departureDate=" + a[2] + "&departure=" + a[3] + "&arrives=" + a[4] +
            "&arrivalDate=" + a[5] + "&arrival=" + a[6] + "&ecoSeats=" + a[7] + "&ecoPrice=" + a[8] +"&bussSeats=" + a[9] + "&bussPrice=" + a[10] +
            "&fclassSeats=" + a[11] + "&fclassPrice=" + a[12] + "&request=" + requestFrom + "&flightType="+flightType;

        callAjax(tableData);
    });
}

function fillFlightData(a) {
    $('#uFlightNumber').val(a[0]);
    $('#uDeparts').val(a[1]);
    $('#uDepartureDate').val(a[2]);
    $('#uDeparture').val(a[3]);
    $('#uArrives').val(a[4]);
    $('#uArrivalDate').val(a[5]);
    $('#uArrival').val(a[6]);
    $('#u-eco-seats').val(a[7]);
    $('#u-eco-price').val(a[8]);
    $('#u-buss-seats').val(a[9]);
    $('#u-buss-price').val(a[10]);
    $('#u-fclass-seats').val(a[11]);
    $('#u-fclass-price').val(a[12]);
}

function updateFlight()
{
    var valid = updateFormValidation();
    if(valid) {
        $("#addFlightForm").submit(function (e) {
            e.preventDefault();
        });

        var oldData = "&flightNumber=" + a[0] + "&departs=" + a[1] + "&departureDate=" + a[2] + "&departure=" + a[3] + "&arrives=" + a[4] +
            "&arrivalDate=" + a[5] + "&arrival=" + a[6] + "&ecoSeats=" + a[7] + "&ecoPrice=" + a[8] + "&bussSeats=" + a[9] + "&bussPrice=" + a[10] +
            "&fclassSeats=" + a[11] + "&fclassPrice=" + a[12];

        var requestFrom = "updateFlight";
        var flightType = "TentativeFlights"
        var data = $('form[name=updateFlightForm]').serialize() + oldData + "&request=" + requestFrom + "&flightType=" + flightType;
        $('.updateFlightModal').modal('toggle');
        callAjax(data);
    }
}

function refreshTable(data)
{
    if(data.success)
    {
        getTentativeFlights();
        if(data.request == "deleteFlight")
            alert("deleted");
        else if(data.request == "updateFlight")
            alert("updated");
    }
    else
    {
        alert("error");
    }
}

function formValidation() {
    var numRegex = /^\d+$/;
    var alphaRegex = /^[a-zA-Z ]{2,30}$/;
    var flightNumber = $('#flightNumber').val();
    var departureDate = $('#departureDate').val();
    var arrivalDate = $('#arrivalDate').val();
    var departs = $('#departs').val();
    var departure = $('#departure').val();
    var arrives = $('#arrives').val();
    var arrival = $('#arrival').val();
    var ecoPrice = $('#eco-price').val();
    var bussPrice = $('#buss-price').val();
    var fclassPrice = $('#fclass-price').val();
    var ecoSeats = $('#eco-seats').val();
    var bussSeats = $('#buss-seats').val();
    var fclassSeats = $('#fclass-seats').val();
    var count = 0;

    $('#flightNumber').removeClass('error');
    $('#departureDate').removeClass('error');
    $('#arrivalDate').removeClass('error');
    $('#departs').removeClass('error');
    $('#departure').removeClass('error');
    $('#arrives').removeClass('error');
    $('#arrival').removeClass('error');
    $('#eco-price').removeClass('error');
    $('#buss-price').removeClass('error');
    $('#fclass-price').removeClass('error');
    $('#eco-seats').removeClass('error');
    $('#buss-seats').removeClass('error');
    $('#fclass-seats').removeClass('error');

    if (flightNumber == "") {
        $('#flightNumber').addClass('error');
        count++;
    }
    if (departureDate == "") {
        $('#departureDate').addClass('error');
        count++;
    }
    if(arrivalDate == "") {
        $('#arrivalDate').addClass('error');
        count++;
    }
    if(!alphaRegex.test(departs) || departs == ""){
        $('#departs').addClass('error');
        count++;
    }
    if(departure == "") {
        $('#departure').addClass('error');
        count++;
    }
    if(!alphaRegex.test(arrives) || arrives == "") {
        $('#arrives').addClass('error');
        count++;
    }
    if(arrival == "") {
        $('#arrival').addClass('error');
        count++;
    }
    if(!numRegex.test(ecoPrice) || ecoPrice == "") {
        $('#ecoPrice').addClass('error');
        count++;
    }
    if(!numRegex.test(bussPrice) || bussPrice == "") {
        $('#buss-price').addClass('error');
        count++;
    }
    if(!numRegex.test(fclassPrice) || fclassPrice == "") {
        $('#fclass-price').addClass('error');
        count++;
    }
    if(!numRegex.test(ecoSeats) || ecoSeats == "") {
        $('#eco-seats').addClass('error');
        count++;
    }
    if(!numRegex.test(bussSeats) || bussSeats == "") {
        $('#buss-seats').addClass('error');
        count++;
    }
    if(!numRegex.test(fclassSeats) || fclassSeats == "") {
        $('#fclass-seats').addClass('error');
        count++;
    }
    if(count == 0)
        return true
    else return false;
}

function updateFormValidation() {
    var numRegex = /^\d+$/;
    var alphaRegex = /^[a-zA-Z ]{2,30}$/;
    var flightNumber = $('#uFlightNumber').val();
    var departureDate = $('#uDepartureDate').val();
    var arrivalDate = $('#uArrivalDate').val();
    var departs = $('#uDeparts').val();
    var departure = $('#uDeparture').val();
    var arrives = $('#uArrives').val();
    var arrival = $('#uArrival').val();
    var ecoPrice = $('#u-eco-price').val();
    var bussPrice = $('#u-buss-price').val();
    var fclassPrice = $('#u-fclass-price').val();
    var ecoSeats = $('#u-eco-seats').val();
    var bussSeats = $('#u-buss-seats').val();
    var fclassSeats = $('#u-fclass-seats').val();
    var count = 0;

    $('#uFlightNumber').removeClass('error');
    $('#uDepartureDate').removeClass('error');
    $('#uArrivalDate').removeClass('error');
    $('#uDeparts').removeClass('error');
    $('#uDeparture').removeClass('error');
    $('#uArrives').removeClass('error');
    $('#uArrival').removeClass('error');
    $('#u-eco-price').removeClass('error');
    $('#u-buss-price').removeClass('error');
    $('#u-fclass-price').removeClass('error');
    $('#u-eco-seats').removeClass('error');
    $('#u-buss-seats').removeClass('error');
    $('#u-fclass-seats').removeClass('error');

    if (flightNumber == "") {
        $('#uFlightNumber').addClass('error');
        count++;
    }
    if (departureDate == "") {
        $('#uDepartureDate').addClass('error');
        count++;
    }
    if(arrivalDate == "") {
        $('#uArrivalDate').addClass('error');
        count++;
    }
    if(!alphaRegex.test(departs) || departs == ""){
        $('#uDeparts').addClass('error');
        count++;
    }
    if(departure == "") {
        $('#uDeparture').addClass('error');
        count++;
    }
    if(!alphaRegex.test(arrives) || arrives == "") {
        $('#uArrives').addClass('error');
        count++;
    }
    if(arrival == "") {
        $('#uArrival').addClass('error');
        count++;
    }
    if(!numRegex.test(ecoPrice) || ecoPrice == "") {
        $('#u-ecoPrice').addClass('error');
        count++;
    }
    if(!numRegex.test(bussPrice) || bussPrice == "") {
        $('#u-buss-price').addClass('error');
        count++;
    }
    if(!numRegex.test(fclassPrice) || fclassPrice == "") {
        $('#u-fclass-price').addClass('error');
        count++;
    }
    if(!numRegex.test(ecoSeats) || ecoSeats == "") {
        $('#u-eco-seats').addClass('error');
        count++;
    }
    if(!numRegex.test(bussSeats) || bussSeats == "") {
        $('#u-buss-seats').addClass('error');
        count++;
    }
    if(!numRegex.test(fclassSeats) || fclassSeats == "") {
        $('#u-fclass-seats').addClass('error');
        count++;
    }
    if(count == 0)
        return true
    else return false;
}

function addFlight() {
    var valid = formValidation();
    if (valid) {
        $("#addFlightForm").submit(function (e) {
            e.preventDefault();
        });

        var requestFrom = "addFlight";
        var flightType = "TentativeFlights"
        var data = $('form[name=addFlightForm]').serialize() + "&request=" + requestFrom + "&flightType=" + flightType;
        callAjax(data);
    }
}

function flightInserted(data) {
    if(data.success == true)
    {
        alert("added")
        window.location = "../admin.html";
    }
    else if (data.success == false)
    {
        //show error
        alert("exists");
    }
}

function login() {
    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    var email = $('#email').val();
    var pwd = $('#pwd').val();
    var count = 0;

    $('#email').removeClass('error');
    $('#pwd').removeClass('error');

    if (!emailRegex.test(email) || email == "") {
        $('#email').addClass('error');
        count++;
    }
    if (pwd == "") {
        $('#pwd').addClass('error');
        count++;
    }
    if(count == 0){
        $('#email').removeClass('error');

        if(pwd == "admin")
            location.href = "../admin.html";
        else if(pwd == "manager")
            location.href = "../manager.html";
    }
}

function logout() {
    var data = {request : 'logout'};
    callAjax(data)
}

function loggedout(data) {
    if(data.success)
        window.location = "../adminLogin.html";
}

function callAjax(data) {

    $.ajax({
        type: "POST",
        url: "/AdminServlet",
        data: data,
        dataType: "json",
        success: function (data) {
            if(data.request == "addFlight")
                flightInserted(data);
            else if(data.request == "getAllFlights")
                displayFlights(data);
            else if(data.request == "getTentativeFlights")
                displayTentativeFlights(data);
            else if(data.request == "updateFlight" || data.request == "deleteFlight")
                refreshTable(data);
            else if(data.request == "logout")
                loggedout(data);
        }
    });

}