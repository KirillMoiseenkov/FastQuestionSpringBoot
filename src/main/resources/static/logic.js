var selectLanguage = $("#language_select");
var questionText = $("#question_text");
var answersList = $("#my_messages_place");
var questionId = 0;
var currentQuestion;

function onBtnAnswerSubmit()
{

    var question = currentQuestion;
    var answerText = $("#answer_text");

    answerOnQuestion(question, answerText.val());

    answersList.append(question);

}

function answerOnQuestion(question, messageText)
{
    var myMessage = {

        question_id : question,
        message : messageText

    };

    console.log(myMessage);

    var postAnswer = $.ajax({

        url: '/addMessage',
        type: 'POST',
        data: JSON.stringify(myMessage),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false

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