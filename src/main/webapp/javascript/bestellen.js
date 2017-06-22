var max = 0;
function initPage(){
	$.get("/restservices/orders", function(data){
		window.sessionStorage.setItem("orders", JSON.stringify(data));
	});
	var user2 = JSON.parse(window.sessionStorage.getItem("bestelling"));
	console.log(user2);
	$(".bestelling").append("<img src='foto/"+user2.afbeelding +".jpg' alt='artikel' style='width:240px;height:180px;'>");
	$("#prijs").append("<label> "+ user2.prijs +"</label>");
	$("#type").append("<label> "+ user2.type +"</label>");
	$("#kleur").append("<label> "+ user2.kleur +"</label>");
}

function findMax() {
	var orders = JSON.parse(window.sessionStorage.getItem("orders"));
	var nummers = []
	$.each(orders, function(i, order){
		nummers.push(order.ordernummer);
	});
	console.log(nummers);
	var nummer = 0;
	for (i=0; i<=nummer;i++){
	    if (nummers[i]>nummer) {
	        var nummer=nummers[i];
	    }
	}
	max = nummer;
	max++;
	console.log(max);
}

$("#post").click(function(){
	findMax();
	var user2 = JSON.parse(window.sessionStorage.getItem("bestelling"));
	var today = new Date();
	var orders = JSON.parse(window.sessionStorage.getItem("orders"));
	var dd = today.getDate();
	var mm = today.getMonth()+1;

	var yy = today.getFullYear().toString().substr(-2);
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	var today = dd+'/'+mm+'/'+yy;
	var data = {"ordernummer": max, "datum": today, "postcode": $("input[name=postcode]").val(), "huisnummer":$("input[name=huisnummer]").val(),"land": $( "#keuze1 option:selected" ).text(), "artikelnummer": user2.artikelnummer, "email": $("input[name=e-mail]").val(),"betaald": "nee"};
	var JSONdata = JSON.stringify(data);
	console.log(JSONdata);
	console.log(user2.artikelnummer);
	if ($("input[name=voornaam]").val()==='' || $("input[name=achternaam]").val()==='' || $("input[name=postcode]").val()==='' || $("input[name=huisnummer]").val()==='' || $("input[name=e-mail]").val()==='') {
		alert("Vul alle velden correct in!");
	} else {
	$.ajax({
		url: "/restservices/orders",
		type: "post",
		data: JSONdata,
		succes: function(JSONdata){
			var order = data;
			var orders = JSON.parse(window.sessionStorage.getItem("orders"));
			orders.push(country);
			window.sessionStorage.setItem("orders", JSON.stringify(orders));
		},
		error: function(response, textStatus, errorThrown) {
			console.log("textStatus: "+textStatus);
			console.log("errorThrown: "+errorThrown);
			console.log("status: "+response.status);
		}
	});
	$.ajax({
		url: "/restservices/artikelen/"+user2.artikelnummer,
		type: "delete",
		succes: function(response){
			console.log(response);
		}
	});
	besteld();
	}
});

function besteld() {
	var user2 = JSON.parse(window.sessionStorage.getItem("bestelling"));
	alert("Order is verzonden! Zodra u het geld:\n "+ user2.prijs +" overmaakt naar:\n NL99 ABNA 0123 4567 89 \nzal de order verder verwerkt worden! Als er binnen 2 weken nog geen geen geld is overgemaakt zal de order verwijdert worden. Voor meer informatie zie de pagina 'levering'");
	window.location.href = "/index.html";
}

$(document).ready(initPage());
