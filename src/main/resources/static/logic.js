var selectLanguage = $("#language_select");
var questionText = $("#question_text");
var answersList = $("#my_messages_place");
var answerOnQuestionGetBtn = $("#answer_on_question_get_btn");
var questionId = 0;
var currentQuestion;

function onBtnSkipQuestion()
{
    generateQuestion();
}

function onBtnAnswerSubmit()
{
    var question = currentQuestion;
    var answerText = $("#answer_text");

    answerOnQuestion(question, answerText.val());

    var answersCount = 0;

    var getAnswer = $.ajax({

            url : '/getQuestionStatus',
            type : 'GET',
            async : false,
            contentType : 'application/json; charset=utf-8',
            success : function (data)
            {
                answersCount = data;
            }

    });
    console.log(answersCount);
    $("#answers_count").html("Your answers count = "+answersCount+".");

    if(answersCount >= 3)
    {
        showSendMyQuestionForm();
    }
    else
    {
        generateQuestion();
    }

}

function showSendMyQuestionForm()
{
        placeForAnswerAndQuestion.empty();
        placeForAnswerAndQuestion.load("getQuestionForm #my_question_form", function (){

                placeForAnswerAndQuestion.hide();
                placeForAnswerAndQuestion.slideDown(slidingDelay);

            });
}

function sendMyQuestion()
{

    var myQuestionText = $("#my_question_text").val();
    var languageId = 0;

    if(selectLanguage.val() == "russian")
    {
        languageId = 4;
    }
    else
    {
        languageId = 5;
    }

    var language = {

        id : languageId,
        language_name : selectLanguage.val()

    };

    var myQuestion = {

        question : myQuestionText,
        language_id : language

    };

    var postAnswer = $.ajax({

            url : '/addNewQuestion',
            type : 'POST',
            data : JSON.stringify(myQuestion),
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            async : false,
            success : function (data)
            {
                return data;
            }

        });

        getAnswerForm();
}

function answerOnQuestion(question, messageText)
{
    var myMessage = {

        question_id : question,
        message : messageText

    };

    //console.log(myMessage);

    var postAnswer = $.ajax({

        url : '/addMessage',
        type : 'POST',
        data : JSON.stringify(myMessage),
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        async : false,
        success : function (data)
        {
            return data;
        }

    });
}

function getAnswerForm()
{
    placeForAnswerAndQuestion.empty();
    placeForAnswerAndQuestion.load("getAnswerForm #user_answer_on_question_form", function (){

            generateQuestion();
            answerOnQuestionGetBtn.prop("disabled", true);
            placeForAnswerAndQuestion.hide();
            placeForAnswerAndQuestion.slideDown(slidingDelay);


        });
}

function generateQuestion()
{
    var placeForQuestion = $("#place_for_question");
    placeForQuestion.html(createQuestionHtml());
}

function createQuestionHtml()
{

    var lang = selectLanguage.val();

    var qData = getQuestionData(lang);
    currentQuestion = qData;

    var questionId = qData.id;
    var questionText = qData.question;

    var htmlString = questionId + "<br/>" + questionText + "<br/>";

    return htmlString;

}

function getQuestionData(lang)
{

    var url = "getRandomQuestionByLanguage?lang="+lang;

    var questionData;

    var question = $.ajax({

                        url : url,
                        type : 'GET',
                        async : false,
                        contentType : 'application/json; charset=utf-8',
                        success : function (data)
                        {
                            questionData = data;
                        }

                    });
    return questionData;
}
