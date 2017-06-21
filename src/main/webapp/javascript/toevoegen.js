var max = 0;
function initPage(){
	$.get("/firstapp/restservices/artikelen", function(data){
		window.sessionStorage.setItem("artikelen", JSON.stringify(data));
	});
}

function findMax() {
	var artikelen = JSON.parse(window.sessionStorage.getItem("artikelen"));
	var nummers = []
	$.each(artikelen, function(i, artikel){
		nummers.push(artikel.artikelnummer);
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
	console.log(max);
	var data = {"artikelnummer": max, "prijs" : $("input[name=prijs]").val(),  "type": $( "#keuze1 option:selected" ).text(), "kleur":$("input[name=kleur]").val(),"afbeelding": $( "#keuze2 option:selected" ).text()};
	var JSONdata = JSON.stringify(data);
	console.log(JSONdata);
	if ($("input[name=prijs]").val()==='' || $("input[name=kleur]").val()==='')
		alert("Vul alle velden in!");
	else {
	$.ajax({
		url: "/firstapp/restservices/artikelen",
		type: "post",
		data: JSONdata,
		succes: function(JSONdata){
			var artikel = data;
			console.log(artikel);
			var artikelen = JSON.parse(window.sessionStorage.getItem("artikelen"));
			artikelen.push(country);
			window.sessionStorage.setItem("artikelen", JSON.stringify(artikelen));
		},
		error: function(response, textStatus, errorThrown) {
		alert("artikel niet toegevoegt!")
		console.log("textStatus: "+textStatus);
		console.log("errorThrown: "+errorThrown);
		console.log("status: "+response.status);
	}
	});
	max += 1;
	besteld();
	}
	location.reload();
});

function besteld() {
	alert("Artikel is toegevoegd!");
	$("input[name=prijs]").val('');
	$("input[name=kleur]").val('');
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
$(document).ready(password());