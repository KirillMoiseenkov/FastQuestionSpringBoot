alert(1)
/*var formJson = [];
$("form" ).submit(function (event){

	var data = JSON.stringify( $( this ).serializeArray());
    var t = $( this ).serializeArray();

	for (var i = 0; i < t.length; i++){
	if(t[i].value != 0)
	formJson.push({id:i, count:t[i].value, product:t[i].name});
}

console.log(JSON.stringify(formJson));

event.preventDefault();

	$.ajax({
    url: '/addOrderToSession',
    type: 'POST',
    data: JSON.stringify(formJson),
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    async: false,
    success: function(msg) {

    }
});

})
*/