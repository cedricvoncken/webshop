package nl.hu.v1wac.firstapp.webservices;

import java.io.InputStream;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Artikel;
import nl.hu.v1wac.firstapp.model.Order;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/artikelen")
public class ArtikelResource {
	
	private JsonObjectBuilder artikelToJson(Artikel artikel){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("artikelnummer", artikel.getArtikelNummer())
			.add("prijs", artikel.getPrijs())
			.add("type", artikel.getType())
			.add("kleur", artikel.getKleur())
			.add("afbeelding", artikel.getAfbeelding());
		return job;
	}

	@POST
	@Produces("application/json")
	public String createArtikel(InputStream is) {
		WorldService service = ServiceProvider.getWorldService();
	    JsonObject object = Json.createReader(is).readObject();
	    
	    int artikelnummer = object.getInt("artikelnummer");
	    String pr = object.getString("prijs");
	    String type = object.getString("type");
	    String kleur = object.getString("kleur");
	    String afbeelding = object.getString("afbeelding");
	    
	    double prijs = Double.parseDouble(pr);
	    
	    Artikel artikel = new Artikel(artikelnummer, prijs, type, kleur, afbeelding);
	    service.addartikel(artikel);
	    return artikelToJson(artikel).build().toString();
	  }
	
	@GET
	@Produces("application/json")
	public String getAllArtikelen(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Artikel c : service.getAllArtikelen()){
			jab.add(artikelToJson(c));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/Shirts")
	@Produces("application/json")
	public String getShirts(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Artikel c : service.getAllShirts()){
			jab.add(artikelToJson(c));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/Schoenen")
	@Produces("application/json")
	public String getSchoenen(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Artikel c : service.getAllSchoenen()){
			jab.add(artikelToJson(c));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getartikelByCode(@PathParam("code") int code){
		WorldService service = ServiceProvider.getWorldService();
		Artikel c = service.getartikelByCode(code);

		return artikelToJson(c).build().toString();
	}
	
	@DELETE
	@Path("{code}")
	public Response deleteArtikel(@PathParam("code") int code){
		WorldService service = ServiceProvider.getWorldService();
		Artikel found = service.getartikelByCode(code);
		
		if(found == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			if(service.delete(found)){
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		}
	}
}
