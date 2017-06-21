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
			"<td><button id='update"+order.ordernummer+"'>Afronden</button></td>"+
			"<td><button id='delete"+order.ordernummer+"'>Verwijderen</button></td>"+
		   "</tr>");
	$("button#update"+order.ordernummer).click(function(){
		updateOrder(order);
	});
	$("button#delete"+order.ordernummer).click(function(){
		deleteOrder(order);
	});
}

function updateOrder(code){
	console.log(code.ordernummer);
	$.ajax({
		url: "/restservices/orders/"+code.ordernummer,
		type: "put",
		data: code,
		success: function(code) {
			$("#countries").empty();
			$("#countries").append("<tr><th>ordernummer</th> "+
						"<th>datum</th><th>postcode</th><th>huisnummer</th>"+
						"<th>land</th><th>artikelnummer</th><th>email</th><th>betaald</th></tr>")
			console.log('gelukt');
			$.get("/firstapp/restservices/orders", function(data){
				window.sessionStorage.setItem("orders", JSON.stringify(data));
				$.each(data, function(i, order){
					appendorder(order);
				});
			});
		},
		error: function(response, textStatus, errorThrown) {
			console.log("textStatus: "+textStatus);
			console.log("errorThrown: "+errorThrown);
			console.log("status: "+response.status);
		}
	});
	
}
function deleteOrder(code){
	$.ajax({
		url: "/restservices/orders/"+code.ordernummer,
		type: "delete",
	});
	$("#"+code.ordernummer).remove();
}

function password(){
	var testV = 1;
	var pass1 = prompt('Vul het wachtwoord in',' ');
	while (testV < 3) {
	if (!pass1) 
	history.go(-1);
	if (pass1.toLowerCase() == "admin") {
	alert('Toegang verleent');
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
	$.get("/restservices/orders/Open", function(data){
		window.sessionStorage.setItem("orders", JSON.stringify(data));
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
$("#button").click( function()
         {
    		window.location = "/afgerond.html";
         }
);
$("#button2").click( function()
        {
   		window.location = "/toevoegen.html";
        }
);


$(document).ready(password());
