package com.buymore.billing;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "total")
public class Total {
	
	double billTotal;
	String customerId;

	public double getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(double billTotal) {
		this.billTotal = billTotal;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
