function appendCountry(country){
	$("#countries").append("<div class='item'><span id='"+ country.artikelnummer +"' class='country'>" +
			"<img src='foto/"+country.afbeelding +".jpg' alt='artikel' style='width:240px;height:180px;'>"+
			"<p>prijs: &euro; " + country.prijs + "</p>" +
			"<p>type: " + country.type + "</p>" +
			"<p>kleur: " + country.kleur + "</p>" +
			"<p><button class='button' id='verzend"+country.artikelnummer+"'>bestellen</button></p>"+
		   "</span></div>");
	$("button#verzend"+country.artikelnummer).click(function(){
		verzend(country);
	});
}

function initPage(){
	$.get("/restservices/artikelen/Schoenen", function(data2){
		window.sessionStorage.setItem("data2", JSON.stringify(data2));
		$.each(data2, function(i, country){
			appendCountry(country);
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
		newCountry();
	});
}

$(function(){
	  $('input[type="radio"]').click(function(){
	    if ($(this).is(':checked')){
	    	var kleur =$(this).val();
	    $(".country").remove();
		$(".countryForm").remove();
		$(".item").remove();
		var data2 = JSON.parse(window.sessionStorage.getItem("data2"));
		$.each(data2, function(i, country){
			if(country.kleur.includes(kleur)){
				appendCountry(country);
			}
			});
	    }
	  });
	});

function filterOnKleur(){
	var option = $(":selected").val();
	$(".country").remove();
	$(".countryForm").remove();
	$(".item").remove();
	var data2 = JSON.parse(window.sessionStorage.getItem("data2"));
	if(option==="groter"){
		$.each(data2, function(i, country){
			if(country.prijs > number){
				console.log(country.prijs);
				appendCountry(country);
			}
		});
	} else if(option==="kleiner"){
		$.each(data2, function(i, country){
			if(country.prijs < number){
				console.log(country.prijs);
				appendCountry(country);
			}
		});
	}
}

function filterOnNumber(){
	var number = $("#getal").val();
	var option = $(":selected").val();
	$(".country").remove();
	$(".countryForm").remove();
	$(".item").remove();
	var data2 = JSON.parse(window.sessionStorage.getItem("data2"));
	if(option==="groter"){
		$.each(data2, function(i, country){
			if(country.prijs > number){
				console.log(country.prijs);
				appendCountry(country);
			}
		});
	} else if(option==="kleiner"){
		$.each(data2, function(i, country){
			if(country.prijs < number){
				console.log(country.prijs);
				appendCountry(country);
			}
		});
	}
}

function updateText(val) {
    $("#textInput").text =val; 
  }

function reset(){
	$(".country").remove();
	$(".countryForm").remove();
	$(".item").remove();
	var data2 = JSON.parse(window.sessionStorage.getItem("data2"));
	$.each(data2, function(i, country){
		appendCountry(country);
	});
}

function checkCountry(country){
	return country.artikelnummer === this;
}

function verzend(country){
	var bestelling = { "artikelnummer": country.artikelnummer, "prijs" : country.prijs, "type" : country.type, "kleur" : country.kleur, "afbeelding" : country.afbeelding};
	window.sessionStorage.setItem("bestelling", JSON.stringify(bestelling));
	
	window.location = "/bestellen.html";
}

$(document).ready(initPage());
