$(document).ready(function(){
    getAllFlights();
    getTentativeFlights();
});

function getAllFlights()
{
    var data = {request : 'getAllFlights', flightType: "Schedule"};
    callAjax(data);
}

var a = [];

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


function displayTentativeFlights(data)
{
    var table =  $('#table-tentative-flights').DataTable( {
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
            { mData: "Approve Flight",
                "mRender": function() {
                    return '<button type="button" id="yes" class="btn btn-link" style="color: #337ab7 !important;">Yes</button>' +
                        '<button type="button" id="no" class="btn btn-link" style="color: #337ab7 !important;">No</button>';
                }   }
        ]
    } );



    $('#table-tentative-flights tbody').off('click','#yes').on('click', '#yes', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            a[i++] = $(this).text();
        });

        var requestFrom = "approveFlight";
        var flightType = "Schedule"
        var tableData = "flightNumber=" + a[0] + "&departs=" + a[1] + "&departureDate=" + a[2] + "&departure=" + a[3] + "&arrives=" + a[4] +
            "&arrivalDate=" + a[5] + "&arrival=" + a[6] + "&ecoSeats=" + a[7] + "&ecoPrice=" + a[8] +"&bussSeats=" + a[9] + "&bussPrice=" + a[10] +
            "&fclassSeats=" + a[11] + "&fclassPrice=" + a[12] + "&request=" + requestFrom + "&flightType="+flightType;

        callAjax(tableData);
    });

    $('#table-tentative-flights tbody').off('click','#no').on('click', '#no', function () {
        var $row = $(this).closest("tr"),        // Finds the closest row <tr>
            $tds = $row.find("td"); // Finds the 2nd <td> element
        var i = 0;
        $.each($tds, function () {                // Visits every single <td> element
            a[i++] = $(this).text();
        });

        var requestFrom = "disapproveFlight";
        var flightType = "Schedule"
        var tableData = "flightNumber=" + a[0] + "&departs=" + a[1] + "&departureDate=" + a[2] + "&departure=" + a[3] + "&arrives=" + a[4] +
            "&arrivalDate=" + a[5] + "&arrival=" + a[6] + "&ecoSeats=" + a[7] + "&ecoPrice=" + a[8] +"&bussSeats=" + a[9] + "&bussPrice=" + a[10] +
            "&fclassSeats=" + a[11] + "&fclassPrice=" + a[12] + "&request=" + requestFrom + "&flightType="+flightType;

        callAjax(tableData);
    });
}


function refreshTable(data)
{
    if(data.success)
    {
        if(data.request == "approveFlight")
            getAllFlights();

        getTentativeFlights();
    }
    else
    {
        alert("error");
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
            if(data.request == "getAllFlights")
                displayFlights(data);
            else if(data.request == "getTentativeFlights")
                displayTentativeFlights(data);
            else if(data.request == "approveFlight")
                refreshTable(data);
            else if(data.request == "disapproveFlight")
                refreshTable(data);
            else if(data.request == "logout")
                loggedout(data);
        }
    });

}