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
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;
import nl.hu.v1wac.firstapp.model.Order;

@Path("/orders")
public class OrderResource {
	
	private JsonObjectBuilder orderToJson(Order order){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("ordernummer", order.getOrdernummer())
			.add("datum", order.getDatum())
			.add("postcode", order.getPostcode())
			.add("huisnummer", order.getHuisnummer())
			.add("land", order.getLand())
			.add("artikelnummer", order.getArtikelnummer())
			.add("email", order.getEmail())
			.add("betaald", order.getBetaald());
		return job;
	}
	
	@POST
	  @Produces("application/json")
	  public String createCustomer(InputStream is) {
		WorldService service = ServiceProvider.getWorldService();
	    JsonObject object = Json.createReader(is).readObject();
	    
	    int ordernummer = object.getInt("ordernummer");
	    String datum = object.getString("datum");
	    String postcode = object.getString("postcode");
	    String huisnummer = object.getString("huisnummer");
	    String land = object.getString("land");
	    int artikelnummer = object.getInt("artikelnummer");
	    String email = object.getString("email");
	    String betaald = object.getString("betaald");
	    
	    Order newCustomer = new Order(ordernummer, datum, postcode, huisnummer, land, artikelnummer, email, betaald);
	    service.addOrder(newCustomer);
	    return orderToJson(newCustomer).build().toString();
	  }

	
	@GET
	@Produces("application/json")
	public String getOrders(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Order o : service.getAllOrders()){
			jab.add(orderToJson(o));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/Open")
	@Produces("application/json")
	public String getOpen(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Order o : service.getAllOrdersOpen()){
			jab.add(orderToJson(o));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/Betaald")
	@Produces("application/json")
	public String getBetaald(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Order o : service.getAllOrdersBetaald()){
			jab.add(orderToJson(o));
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getOrderByCode(@PathParam("code") int code){
		WorldService service = ServiceProvider.getWorldService();
		Order o = service.getOrderByCode(code);

		return orderToJson(o).build().toString();
	}
	
	@PUT
	@Path("{code}")
	public Response updateOrder(@PathParam("code") int code){
		WorldService service = ServiceProvider.getWorldService();
		
		Order order =  service.getOrderByCode(code);
		if(order != null){
			order.setBetaald("Ja");
			String a = orderToJson(service.update(order)).build().toString();
			return Response.ok(a).build();
		}
		
		throw new WebApplicationException("Order not found!");
	}
	@DELETE
	@Path("{code}")
	public Response deleteOrder(@PathParam("code") int code){
		WorldService service = ServiceProvider.getWorldService();
		Order found = service.getOrderByCode(code);
		
		if(found == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			if(service.deleteOrder(found)){
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		}
	}
}