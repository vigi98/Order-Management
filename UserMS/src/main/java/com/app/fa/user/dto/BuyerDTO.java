package com.app.fa.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.app.fa.user.entity.Buyer;

public class BuyerDTO {

	private int buyerid;
	
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
	private boolean isprivileged;
	private int rewardpoints;
	
	public BuyerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuyerDTO(int buyerid, String name, String email, String phonenumber, String password, boolean isactive,
			boolean isprivileged, int rewardpoints) {
		super();
		this.buyerid = buyerid;
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password = password;
		this.isactive = isactive;
		this.isprivileged = isprivileged;
		this.rewardpoints = rewardpoints;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
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

	public boolean isIsprivileged() {
		return isprivileged;
	}

	public void setIsprivileged(boolean isprivileged) {
		this.isprivileged = isprivileged;
	}

	public int getRewardpoints() {
		return rewardpoints;
	}

	public void setRewardpoints(int rewardpoints) {
		this.rewardpoints = rewardpoints;
	}
	
	//Entity to DTO
	public static BuyerDTO valueOf(Buyer buyer) {
		    BuyerDTO buyerDTO = new BuyerDTO();
		    buyerDTO.setBuyerid(buyer.getBuyerid());
		    buyerDTO.setName(buyer.getName());
			buyerDTO.setEmail(buyer.getEmail());
			buyerDTO.setPhonenumber(buyer.getPhonenumber());
			buyerDTO.setPassword(null);
			buyerDTO.setIsactive(buyer.isIsactive());
			buyerDTO.setIsprivileged(buyer.isIsprivileged());
			buyerDTO.setRewardpoints(buyer.getRewardpoints());

		return buyerDTO;
	}
	
	//DTO to Entity
	public Buyer createEntity() {
        Buyer buyer = new Buyer();
        //buyer.setBuyerid(this.getBuyerid());
        buyer.setName(this.getName());
        buyer.setPhonenumber(this.getPhonenumber());
        buyer.setEmail(this.getEmail());
        buyer.setPassword(this.getPassword());
        buyer.setIsprivileged(this.isIsprivileged());
        buyer.setRewardpoints(this.getRewardpoints());
        buyer.setIsactive(this.isIsactive());
        return buyer;
    }
	
	
	
	

	@Override
	public String toString() {
		return "BuyerDTO [buyerid=" + buyerid + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber
				+ ", password=" + password + ", isactive=" + isactive + ", isprivileged=" + isprivileged
				+ ", rewardpoints=" + rewardpoints + "]";
	}
	
	
    
	
	
}
