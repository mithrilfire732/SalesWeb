package com.acme.sales.orderline;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.acme.sales.order.Order;

public class Orderline {
	private int id;
	@Column(length=30, nullable=false)
	private String product;
	private int quantity;
	private int price;
	@ManyToOne(optional=false)
	@JoinColumn(name="orderId")
	private Order order;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Orderline() {}
}
