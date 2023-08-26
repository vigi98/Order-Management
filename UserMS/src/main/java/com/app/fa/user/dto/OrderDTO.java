package com.app.fa.user.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderDTO {
	
	
	private int orderid;
	@Min(value=1,message="buyerid required")
	private int buyerid;
	private double amount;
	private Date date;
	@NotNull(message = "{address cannot Be Null}")
	 @Size(max = 100, message 
    = "Must be within 100 characters")
	private String address;
	private String status;
	List<ProductsorderedDTO> pord;
	
	public List<ProductsorderedDTO> getPord() {
		return pord;
	}


	public void setPord(List<ProductsorderedDTO> pord) {
		this.pord = pord;
	}
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


	@Override
	public String toString() {
		return "OrderDTO [orderid=" + orderid + ", buyerid=" + buyerid + ", amount=" + amount + ", date=" + date
				+ ", address=" + address + ", status=" + status + ", pord=" + pord + "]";
	}


	
	
	
	
	
}
