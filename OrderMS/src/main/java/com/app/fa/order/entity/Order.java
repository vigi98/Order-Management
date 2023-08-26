package com.app.fa.order.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderid;
	@Column(nullable = false)
	
	private int buyerid;
	@Column(nullable = false)
	private double amount;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	 
	private String address;
	@Column(nullable = false)
	
	private String status;


	public Order() {
		super();
	}

	public Order(int orderid, int buyerid, double amount, Date date, String address, String status) {
		this();
		this.orderid = orderid;
		this.buyerid = buyerid;
		this.amount = amount;
		this.date = date;
		this.address = address;
		this.status = status;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", buyerid=" + buyerid + ", amount=" + amount + ", date=" + date
				+ ", address=" + address + ", status=" + status + "]";
	}

    
}
