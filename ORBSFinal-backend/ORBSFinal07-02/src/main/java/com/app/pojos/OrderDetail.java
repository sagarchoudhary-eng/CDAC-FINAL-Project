package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String productName;
	private float subtotal;
	private float shipping;
	private float tax;
	private float total;

	public OrderDetail() {
		System.out.println("in OrderDetail default constrctor");
	}

	public OrderDetail(String productName, String subtotal, String shipping, String tax, String total) {
		this.productName = productName;
		this.subtotal = Float.parseFloat(subtotal);
		this.shipping = Float.parseFloat(shipping);
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
	}

	public String getProductName() {
		return productName;
	}

	public String getSubtotal() {
		return String.format("%.2f", subtotal);
	}

	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}

	public String getTotal() {
		return String.format("%.2f", total);
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", productName=" + productName + ", subtotal=" + subtotal + ", shipping="
				+ shipping + ", tax=" + tax + ", total=" + total + "]";
	}

}
