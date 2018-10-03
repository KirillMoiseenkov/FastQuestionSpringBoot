var placeForRegisterForm = $("#place_for_registration_form");
var singInBtn = $("#sing_in_btn");
var placeForMessages = $("#place_for_messages");
var slidingDelay = 350;

function authValidation(username, password)
{
    var userAuthData = {

        username : username,
        password : password

    };

    var isSuccess = false;

    $.ajax({

        url : '/validateAuthForm',
        type : 'POST',
        async : false,
        data : JSON.stringify(userAuthData),
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        success : function (data)
        {

            isSuccess = data;

        },
        error : function (error)
        {
            console.log(error);
        }

    });

    return isSuccess;
}

function sendAuthData()
{

    var username = $("#username_auth_text").val();
    var password = $("#password_auth_text").val();
    var authErrorMessageSpan = $("#auth_error_message_span");

    var authData = {

        username : username,
        password : password

    };

    var valid = authValidation(username, password);

    if(valid == true)
    {
        var messages = $.ajax({

                    url : '/getMyMessages',
                    type : 'POST',
                    async : false,
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    data : JSON.stringify(authData),
                    success : function (data)
                    {
                        console.log(data);
                        for(var i = 0;i < data.length;i ++)
                        {
                            placeForMessages.append("<p>" + data[i].question_id.question + " " + data[i].message + "</p><br />");
                        }
                    }
                });

        hideAuthForm();
        singInBtn.prop("disabled", true);

    }
    else
    {
        authErrorMessageSpan.html("<font style='color:red'>Wrong username or password!</font><br/>");
    }

}

function hideAuthForm()
{
    placeForRegisterForm.slideUp(slidingDelay,function(){placeForRegisterForm.empty()});
    singInBtn.prop("disabled", false);

}

function getAuthForm()
{

    placeForRegisterForm.load("getAuthForm #auth_form", function (){

        singInBtn.prop("disabled", true);
        placeForRegisterForm.hide();
        placeForRegisterForm.slideDown(slidingDelay);

    });

}