package com.buymore.billing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/bill")
public class BillCalculator {

	final double vat_percentage = 0.15;
	double discount = 0.0;

	@POST
	@Path("/calculate")
	@Consumes("application/json")
	@Produces("application/json")
	public Response calcualte(Total t) {
		double vat = t.getBillTotal() * vat_percentage;
		double final_total = t.getBillTotal() + vat;
		int loyaltyTier = isLoyalCustomer(t.getCustomerId());
		if (loyaltyTier > 0) {
			switch (loyaltyTier) {
			case 1:
				discount = 0.05;
				break;
			case 2:
				discount = 0.10;
				break;
			case 3:
				discount = 0.15;
				break;
			}
			double discountAmount = final_total * discount;
			final_total = final_total - discountAmount;
		}
		
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
	
	private int isLoyalCustomer(String customerId) {
		int loyaltyTier = 0;
		try {
			String urlString = "http://localhost:8888/services/loyal_customer/loyalty/" + customerId;
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				loyaltyTier = Integer.parseInt(output);
			}
			
			conn.disconnect();
			return loyaltyTier;
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		return loyaltyTier;
	}

}