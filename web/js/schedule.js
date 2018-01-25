$(document).ready(function(){
    getAllFlights();
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

function logout() {
    var data = {request : 'logout'};
    callAjax(data)
}

function loggedout(data) {
    if(data.success)
        window.location = "../index.jsp";
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
            else if(data.request == "logout")
                loggedout(data);
        }
    });

}