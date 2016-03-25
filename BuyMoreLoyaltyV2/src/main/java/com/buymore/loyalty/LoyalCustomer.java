package com.buymore.loyalty;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/loyalty")
public class LoyalCustomer {

	@GET
	@Path("{id}")
	@Produces("text/plain")
	public Response isLoyalCustomer(@PathParam("id") String id) {
		int customerId = Integer.parseInt(id);
		int tier = customerId%4;
		
		return Response.status(200).entity(tier)
				.header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
	            .build();
	}
	
}