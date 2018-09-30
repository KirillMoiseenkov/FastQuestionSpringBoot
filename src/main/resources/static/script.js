$(".new" ).text("questions");
$("ul" ).hide();


var a = 1;
var mode = false;
var question;



var timerId = setInterval(function() {
	  if(a>4){
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

var answer;

$(document).ready( function () {

        getQuestion();


});


$('form').submit( function (event) {

        var formJson = [];

        var secondFormJson = [];

        formJson.push({id:1, message:'123'});

        secondFormJson.push({id:1, message:'123', question_id:question});

        var data = JSON.stringify( $( this ).serializeArray());


            	$.ajax({
                url: '/addMessage',
                type: 'POST',
                data: JSON.stringify(secondFormJson),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function(msg) {

                }
            });

		    $(".new" ).text("wait");

			if(mode === true)
			{


                event.preventDefault();
                getQuestion();
        }
			else if(a <= 3){
			var formdata = $(this).serialize();
            getQuestion();
			a++;

			event.preventDefault();
		}else if(a == 4){
			answer = $(this).serialize();
			changeMessage("Ask your question, and wait answers");
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
	
	
function postAnswer(formdata){
    $.post( "ajax/test.html", function( data ) {
      $( ".result" ).html( data );
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
