function formValidation() {
    var alphaRegex = /^[a-zA-Z ]{2,30}$/;
    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var numRegex = /^\d+$/;

    var count = 0;

    var name = $('#name').val();
    var email = $('#email').val();
    var number = $('#number').val();
    var country = $('#country').val();
    var pwd = $('#pwd').val();
    var conPwd = $('#conPwd').val();

    $('#name').removeClass('error');
    $('#email').removeClass('error');
    $('#number').removeClass('error');
    $('#country').removeClass('error');
    $('#pwd').removeClass('error');
    $('#conPwd').removeClass('error');

    if (!alphaRegex.test(name) || name == "") {
        $('#name').addClass('error');
        count++;
    }
    if (!emailRegex.test(email) || email == "") {
        $('#email').addClass('error');
        count++;
    }
    if (!alphaRegex.test(country) || country == "") {
        $('#country').addClass('error');
        count++;
    }
    if (!numRegex.test(number) || number == "") {
        $('#number').addClass('error');
        count++;
    }
    if (pwd == "") {
        $('#pwd').addClass('error');
        count++;
    }
    if (conPwd == "") {
        $('#conPwd').addClass('error');
        count++;
    }
    if(pwd != conPwd) {
        $('#conPwd').addClass('error');
        count++;
    }

    if(count == 0) {
        $("#signupForm").submit(function (e) {
            e.preventDefault();
        });
        callAjax();
    }
}

function customerInserted(data) {
    if(data.success)
    {
        window.location = "../index.jsp";
    }
    else if (!data.success)
    {
        alert("Customer already exists");
        $('#email').addClass('error');
    }
}

function callAjax() {

    $.ajax({
        type: "POST",
        url: "/CustomerServlet",
        data: $('form[name=signupForm]').serialize() + "&request=signup",
        dataType: "json",
        success: customerInserted
    });

}