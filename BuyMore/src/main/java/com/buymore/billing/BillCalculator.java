package com.buymore.billing;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/bill")
public class BillCalculator {
	
	final double vat_percentage = 0.15;

	@POST
	@Path("/calculate")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calcualte(Total t) {
		double vat = t.getBillTotal() * vat_percentage;
		double final_total = t.getBillTotal() + vat;
		Total newTotal = new Total();
		newTotal.setBillTotal(final_total);
		return Response.ok(newTotal)
				.header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
				.build();
	}
}