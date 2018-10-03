var placeForRegisterForm = $("#place_for_registration_form");
var singUpBtn = $("#sing_up_btn");
var singInBtn = $("#sing_in_btn");
var slidingDelay = 350;

function start()
{
}

function sendRegisterData()
{

    var username = $("#username_register_text").val();
    var password = $("#password_register_text").val();
    var conPassword = $("#con_password_register_text").val();
    var errorMessageSpan = $("#register_from_error_message");

    if(
        (password == conPassword)&&
        (username.length < 20 && username.length > 6)&&
        (password.length > 8 && password.length < 32)
    )
    {
        console.log("Data!!!");

        var registrationData = {

            username : username,
            password : password

        };

        var postResult = $.ajax({

            url : '/addNewUser',
            type : 'POST',
            async : false,
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            data : JSON.stringify(registrationData),

        });
        if(postResult)
        {

            console.log(postResult);

        }

        hideRegisterForm();
    }
    else
    {
        errorMessageSpan.empty();
        if(password != conPassword)
        {
            errorMessageSpan.append("<font style='color:red'>Fields password and confirm password isn't equals!</font><br/>");
        }
        if(username.length > 20 || username.length < 6)
        {
            errorMessageSpan.append("<font style='color:red'>Wrong username size!</font><br/>");
        }
        if(password.length < 8 || password.length > 32)
        {
            errorMessageSpan.append("<font style='color:red'>Wrong password size!</font><br/>");
        }
    }
}

function hideRegisterForm()
{
    placeForRegisterForm.slideUp(slidingDelay,function(){placeForRegisterForm.empty()});
    singUpBtn.prop("disabled", false);

}

function hideAuthForm()
{
    placeForRegisterForm.slideUp(slidingDelay,function(){placeForRegisterForm.empty()});
    singInBtn.prop("disabled", false);

}

function getRegisterForm()
{

    placeForRegisterForm.load("getRegisterForm #register_form", function (){

        singUpBtn.prop("disabled", true);
        placeForRegisterForm.hide();
        placeForRegisterForm.slideDown(slidingDelay);

    });

}

function getAuthForm()
{

    placeForRegisterForm.load("getAuthForm #auth_form", function (){

        singInBtn.prop("disabled", true);
        placeForRegisterForm.hide();
        placeForRegisterForm.slideDown(slidingDelay);

    });

}