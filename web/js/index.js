$(document).ready(function(){
    var date_input=$('input[name="date-depart"]'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input.datepicker({
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    });
    var date_input1=$('input[name="date-return"]'); //our date input has the name "date"
    var container1=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input1.datepicker({
        format: 'dd/mm/yyyy',
        container: container1,
        todayHighlight: true,
        autoclose: true,
    });

    $("#btn-view-booking").click(function () {
        $("#form-my-booking").show(1500);
        $("#form-search-booking").hide(1500);
    });

    $("#btn-search-booking").click(function () {
        $("#form-search-booking").show(1500);
        $("#form-my-booking").hide(1500);
    });
});

$("input[type='radio']").click(function(){
    var radioValue = $("input[name='reservation_type']:checked").val();
    if(radioValue == 1){
        $("#return-date").show(1000);
    }
    else if(radioValue == 2){
        $("#return-date").hide(1000);
    }


});

function formValidation() {
    var alphaRegex = /^[a-zA-Z ]{2,30}$/;
    var count = 0;
    var from = $('#flight-from').val();
    var to = $('#flight-to').val();
    var ddate = $('#date-depart').val();
    if (document.getElementById('res-round').checked)
        var rdate = $('#date-return').val();

    $('#flight-from').removeClass('error');
    $('#flight-to').removeClass('error');
    $('#date-depart').removeClass('error');
    $('#date-return').removeClass('error');

    if (!alphaRegex.test(from) || from == "") {
        $('#flight-from').addClass('error');
        count++;
    }
    if (!alphaRegex.test(to) || to == "") {
        $('#flight-to').addClass('error');
        count++;
    }
    if (ddate == "") {
        $('#date-depart').addClass('error');
        count++;
    }

    if (rdate == "") {
        $('#date-return').addClass('error');
        count++;
    }
    if (count == 0)
        return true;
    else return false;
}

function startBooking() {
    var valid = formValidation();
    if(valid) {
        $("#bookFlightForm").submit(function (e) {
            e.preventDefault();
        });

        var requestFrom = "startBooking";
        var data = $('form[name=bookFlightForm]').serialize() + "&request=" + requestFrom;
        callAjax(data);
    }
}

function startedBooking(data) {
    if(data.success)
    {
        window.location = "../flightTrip.html";
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
        window.location = "../index.jsp";
}

function callAjax(data) {

    $.ajax({
        type: "POST",
        url: "/BookingServlet",
        data: data,
        dataType: "json",
        success: function (data) {
            if (data.request == "startBooking")
                startedBooking(data);
            else if(data.request == "logout")
                loggedout(data);
        }
    });
}