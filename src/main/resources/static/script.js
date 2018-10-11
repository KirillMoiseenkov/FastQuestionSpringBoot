/*
$(".new" ).text("questions");
$("ul" ).hide();


var a = 1;
var mode = false;
var question;



var timerId = setInterval(function() {
	  if(a>10){
		  getMessage();
		  if(a>14)
		  clearInterval(timerId)
		  a++;
		  }
	}, 700);


$( ".randomQuestion" ).click(function() {

    a = 0;
	mode = true;
	$("ul").slideUp();
	$( "form" ).fadeIn(100);

	getQuestion();
    deleteAnswers();
});

$( ".askQuestion" ).click(function() {
	a = 0;
	mode = false;
	deleteAnswers();
	changeMessage("Answer on the three question and after it you can ask your our question");
	$("ul").slideUp();
	$( "form" ).fadeIn(100);
});


function changeMessage(Text){
  $(".header-text").text(Text);
 }

function addNewAnswer(){
	  $( "<li class=\"list-group-item autocomplete\">Vestibulum at eros</li>" ).appendTo( ".answers" );
}



$(document).ready( function () {

        getQuestion();


});


$('form').submit( function (event) {

        var data = JSON.stringify( $( this ).serializeArray());

        var secondFormJson = [];

        secondFormJson.push({id:1, message:JSON.parse(data)[0].value, question_id:question});



            $(".new" ).text("wait");

			if(mode === true)
			{
			    a=1;
                postAnswer(secondFormJson);
                event.preventDefault();
                getQuestion();
            }

			else if(a <= 2){
		        postAnswer(secondFormJson);
                getQuestion();
			    a++;
                event.preventDefault();
		}else if(a == 3){

            changeMessage("Ask your question, and wait answers");
            $(".new" ).text("enter your answer, SICH");
			event.preventDefault();
			a++;
        }else if(a == 4){
		     var thirdFormJson = [];

             thirdFormJson.push({id:1, question:JSON.parse(data)[0].value, language_id:question.language_id});

             postQuestion(thirdFormJson);
             event.preventDefault();
             a++;
        }else{
			changeMessage("Answers:");
			event.preventDefault();
			$( "form" ).slideUp();
			$("ul").fadeIn(100);

			}
});

function deleteAnswers(){
	$("li.autocomplete").remove();
}
	

function postQuestion(formdata){
            	$.ajax({
                    url: '/addQuestion',
                    type: 'POST',
                    data: JSON.stringify(formdata),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    async: false,
                    success: function(msg) {

                    }
                });
}

function postAnswer(formdata){
            	$.ajax({
                    url: '/addMessage',
                    type: 'POST',
                    data: JSON.stringify(formdata),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    async: false,
                    success: function(msg) {

                    }
                });
}


function getQuestion(){

$.get( "/getRandomQuestionByLanguage", function( data ) {
             question =  data;
             $(".new" ).text(question.question);
     });
}

function getMessage(){
	
	$( "<li class=\"list-group-item autocomplete\">" + a + "</li>" ).appendTo( ".answers" ).hide().show('slow');
}
*/
///Oleg code
var slidingDelay = 350;

var placeForAnswerAndQuestion = $("#place_for_answer_and_question");

