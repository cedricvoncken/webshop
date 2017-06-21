function appendorder(order){
	$("#countries").append("<tr id='"+ order.ordernummer +"' class='order'>" +
			"<td>" + order.ordernummer + "</td>" +
			"<td>" + order.datum + "</td>" +
			"<td>" + order.postcode + "</td>" +
			"<td>" + order.huisnummer + "</td>" +
			"<td>" + order.land + "</td>" +
			"<td>" + order.artikelnummer + "</td>" +
			"<td>" + order.email + "</td>" +
			"<td>" + order.betaald + "</td>" +
		   "</tr>");
}

function password(){
	var testV = 1;
	var pass1 = prompt('Vul het wachtwoord in',' ');
	while (testV < 3) {
	if (!pass1) 
	history.go(-1);
	if (pass1.toLowerCase() == "admin") {
	initPage();
	break;
	} 
	testV+=1;
	var pass1 = 
	prompt('Dit wachtwoord klopt niet, probeer het opnieuw.','Password');
	}
	if (pass1.toLowerCase()!="password" & testV ==3) 
	history.go(-1);
	return " ";
}

function initPage() {
	$.get("/firstapp/restservices/orders/Betaald", function(data){
		$.each(data, function(i, order){
			appendorder(order);
		});
	});
	$("#textbutton").click(function(){
		filterOnText();
	});
	$("#numberbutton").click(function(){
		filterOnNumber();
	});
	$("#reset").click(function(){
		reset();
	});
	$("#newLandSubmit").click(function(){
		newOrder();
	});
}
function updateText(val) {
    $("#textInput").text =val; 
  }
$(function() {
    $("#button").click( function()
         {
    		window.location = "http://localhost:4715/firstapp/bestellingen.html";
         }
    );
});

$(document).ready(password());