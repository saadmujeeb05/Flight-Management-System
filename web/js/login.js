function formValidation()
{
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
        $("#loginForm").submit(function (e) {
            e.preventDefault();
        });
        callAjax();
    }
}

function customerLogin(data) {
    if(data.success)
    {
        window.location = "../index.jsp";
    }
    else if (!data.success)
    {
        alert("Incorrect Email or Password");
    }
}

function callAjax() {

    $.ajax({
        type: "POST",
        url: "/CustomerServlet",
        data: $('form[name=loginForm]').serialize() + "&request=login",
        dataType: "json",
        success: customerLogin
    });

}