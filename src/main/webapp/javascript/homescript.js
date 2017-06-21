function appendCountrySh(country){
	$("#shirts").append("<div class='item'><span id='"+ country.artikelnummer +"' class='country'>" +
			"<img src='foto/"+country.afbeelding +".jpg' alt='artikel' style='width:180px;height:140px;'>"+
			"<p>prijs: &euro; " + country.prijs + "</p>" +
		   "</span></div>");
	}

function appendCountrySc(country){
	$("#schoenen").append("<div class='item'><span id='"+ country.artikelnummer +"' class='country'>" +
			"<img src='foto/"+country.afbeelding +".jpg' alt='artikel' style='width:180px;height:140px;'>"+
			"<p>prijs: &euro; " + country.prijs + "</p>" +
		   "</tr>");
	}


function initPage(){
	$.get("/firstapp/restservices/artikelen/Shirts", function(data){
		window.sessionStorage.setItem("data", JSON.stringify(data));
		var i = 2;
		$.each(data, function(i, country){
			if (i < 2) {
			appendCountrySh(country);
			}
		});
	});
	$.get("/firstapp/restservices/artikelen/Schoenen", function(data){
		window.sessionStorage.setItem("data2", JSON.stringify(data));
		var i = 2;
		$.each(data, function(i, country){
			if (i < 2) {
			appendCountrySc(country);
			}
		});
	});
}

$(".container1").click(function() {
	  window.location = $(this).find("a").attr("href"); 
	  return false;
	});
$(".shirts").click(function() {
	  window.location = $(this).find("a").attr("href"); 
	  return false;
	});
$(".schoenen").click(function() {
	  window.location = $(this).find("a").attr("href"); 
	  return false;
	});

$(document).ready(initPage());