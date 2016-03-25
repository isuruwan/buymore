package com.buymore.billing;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "total")
public class Total {
	
	double billTotal;

	public double getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(double billTotal) {
		this.billTotal = billTotal;
	}

}
