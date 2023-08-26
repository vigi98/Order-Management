package com.app.fa.order.dto;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.app.fa.order.entity.Order;

public class OrderDTO {
	
	
	
	private int orderid;
	@Min(value=1,message="buyerid required")
	private int buyerid;
	@Min(value=1,message="amount required")
	private double amount;
	private Date date;
	@NotNull(message = "{address cannot Be Null}")
	private String address;
	 
	private String status;
	
	public OrderDTO() {
		super();
	}

	
	public OrderDTO(int orderid, int buyerid, double amount, Date date, String address, String status) {
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
	
	//Converts Entity to DTO
	public static OrderDTO valueOf(Order orders) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderid(orders.getOrderid());
		orderDTO.setBuyerid(orders.getBuyerid());
		orderDTO.setAmount(orders.getAmount());
		orderDTO.setDate(orders.getDate());
		orderDTO.setAddress(orders.getAddress());
		orderDTO.setStatus(orders.getStatus());

		return orderDTO;
	}
	
	//Converts DTO to Entity
	public Order createEntity() {
		Order ord = new Order();
     //	prod.setProdid(this.getProdid());
		ord.setBuyerid(this.getBuyerid());
		ord.setAmount(this.getAmount());
		ord.setDate(this.getDate());
		ord.setAddress(this.getAddress());
		ord.setStatus(this.getStatus());
		return ord;
	}
	

	@Override
	public String toString() {
		return "OrderDTO [orderid=" + orderid + ", buyerid=" + buyerid + ", amount=" + amount + ", date=" + date
				+ ", address=" + address + ", status=" + status + "]";
	}
	
	
	

}
