package com.app.fa.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.app.fa.user.entity.Seller;

public class SellerDTO {
	
	private int sellerid;
	
	@NotNull(message = "{Name cannot Be Null}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="Name should contain only alphabets and space")
	private String name;
	
	@NotNull(message = "{Email cannot Be Null}")
	@Pattern(regexp="^[0-9A-Za-z]+@[a-zA-z]+\\.com$",message = "Please enter valid email")
	private String email;

	@NotNull(message = "{Phone no. cannot Be Null}")
	@Pattern(regexp="^[6-9]\\d{9}$",message = "Please provide valid mobile number")
	private String phonenumber;
	
	@NotNull(message = "{Password cannot Be Null}")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,20}$", message="Password not proper .It should be 7 to 20 characters in length (both inclusive). It should contain at least one uppercase, at least one lowercase, at least one digit. It should also contain a special character amongst -! @, #, $, %, ^, &, *")
	private String password;
	
	private boolean isactive;
	
	public SellerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerDTO(int sellerid, String name, String email,String phonenumber, String password, boolean isactive) {
		super();
		this.sellerid = sellerid;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.isactive = isactive;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	// Converts Entity into DTO
			public static SellerDTO valueOf(Seller seller) {
					SellerDTO sellerDTO = new SellerDTO();
					sellerDTO.setSellerid(seller.getSellerid());
					sellerDTO.setName(seller.getName());
					sellerDTO.setEmail(seller.getEmail());
					sellerDTO.setPhonenumber(seller.getPhonenumber());
					sellerDTO.setPassword(null);
					sellerDTO.setIsactive(seller.isIsactive());
	    return sellerDTO;
	}
			
			//Converts DTO to Entity
			public Seller createEntity() {
	            Seller seller = new Seller();
	           // seller.setSellerid(this.getSellerid());
	            seller.setName(this.getName());
	            seller.setPhonenumber(this.getPhonenumber());
	            seller.setEmail(this.getEmail());
	            seller.setPassword(this.getPassword());
	            seller.setIsactive(this.isIsactive());
	            return seller;
	        }
			
	

	@Override
	public String toString() {
		return "SellerDTO [sellerid=" + sellerid + ", name=" + name + ", email=" + email + ", phonenumber="
				+ phonenumber + ", password=" + password + ", isactive=" + isactive + "]";
	}
	
	
}
