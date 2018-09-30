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


/*$( "form" ).submit(function() {
  console.log("uup");
  $(".new" ).text(a);
  a++;
});*/


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
		$('form').submit( function (event) {
			if(mode === true)
			{
                event.preventDefault();

                getQuestion();


            }
			else if(a <= 3){	
			var formdata = $(this).serialize();
				$.ajax({
			    type: "POST",
			    url: "https://api.jquery.com/val/",
			    data: formdata
			 });
			return false;
		}else if(a == 4){
			answer = $(this).serialize();
			changeMessage("Ask your question, and wait answers");
			event.preventDefault();
		}else{
			changeMessage("Answers:");
			event.preventDefault();
			$( "form" ).slideUp();
			$("ul").fadeIn(100);
			
			}
	});
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
