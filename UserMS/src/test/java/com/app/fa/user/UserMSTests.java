package com.app.fa.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.fa.user.dto.BuyerDTO;
import com.app.fa.user.dto.LoginDTO;
import com.app.fa.user.dto.SellerDTO;
import com.app.fa.user.entity.Buyer;
import com.app.fa.user.entity.Seller;
import com.app.fa.user.repository.BuyerRepository;
import com.app.fa.user.repository.SellerRepository;
import com.app.fa.user.service.BuyerService;
import com.app.fa.user.service.SellerService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMSTests{
	
	@Mock
	BuyerRepository brepo;
	
	@Mock 
	SellerRepository srepo;
	
	@InjectMocks
	BuyerService bservice;
	
	@InjectMocks
	SellerService sellerS;
//	
	//Valid
	@Test
	public void buyerloginTestValid()  {
		LoginDTO ldto= new LoginDTO();
		ldto.setEmail("Gotia@gmail.com");
		ldto.setPassword("Vignesh@01");
		
		Buyer buyer= new Buyer();
		buyer.setEmail("Gotia@gmail.com");
		buyer.setPassword("Vignesh@01");
		buyer.setIsactive(true);
		
		Mockito.when(brepo.findByEmail(ldto.getEmail())).thenReturn(buyer);
		
		boolean res = bservice.login(ldto);
		Assertions.assertEquals(true,res);
		
	} 
	
	
	//Invalid 
	@Test
	public void buyerloginTestInValid()  {
		LoginDTO ldto= new LoginDTO();
		ldto.setEmail("Gotia@gmail.com");
		ldto.setPassword("Vignesh@01");
		
		//Wrong password
		Buyer buyer= new Buyer();
		buyer.setEmail("Gotia@gmail.com");
		buyer.setPassword("Vignesh01");
		buyer.setIsactive(true);
		Mockito.when(brepo.findByEmail(ldto.getEmail())).thenReturn(buyer);
		boolean res = bservice.login(ldto);
		Assertions.assertEquals(false,res);
		
		//User is inactive
		buyer.setIsactive(false);
		Mockito.when(brepo.findByEmail(ldto.getEmail())).thenReturn(buyer);
		boolean res2 = bservice.login(ldto);
		Assertions.assertEquals(false,res2);
		

		//Wrong username
		Mockito.when(brepo.findByEmail(ldto.getEmail())).thenReturn(null);
		boolean res3 = bservice.login(ldto);
		Assertions.assertEquals(false,res3);
	
	}
	
	@Test
	public void buyerRegisterValid() {
		Buyer buyer= new Buyer();
		BuyerDTO bdto= new BuyerDTO();
		bdto.setName("Roli");
		bdto.setEmail("roli@gmail.com");
		bdto.setPhonenumber("9834127658");
		bdto.setPassword("Roli@01");
		
		Mockito.when(brepo.findByPhonenumber(bdto.getPhonenumber())).thenReturn(null);
		Mockito.when(brepo.save(buyer)).thenReturn(buyer);//not null
		
		String actual=bservice.saveBuyer(bdto);
		Assertions.assertEquals("Buyer Registered successfully",actual);
	}
	
	
	//Invalid
	@Test
	public void buyerRegisterInValid() {
		Buyer buyer= new Buyer();
		buyer.setBuyerid(2);
		buyer.setEmail("joel@gmail.com");
		buyer.setPhonenumber("9834127658");
		buyer.setName("Joel");
	    buyer.setPassword("Joel@123");
	    buyer.setIsactive(true);
	    buyer.setRewardpoints(1234);
	    
		BuyerDTO bdto= new BuyerDTO();
		bdto.setName("Roli");
		bdto.setEmail("roli@gmail.com");
		bdto.setPhonenumber("9834127658");
		bdto.setPassword("Roli@01");
		
		//Check for diff user but already existing Phone number
		Mockito.when(brepo.findByEmail(bdto.getEmail())).thenReturn(null);
		Mockito.when(brepo.findByPhonenumber(bdto.getPhonenumber())).thenReturn(buyer);
		String actual=bservice.saveBuyer(bdto);
		Assertions.assertEquals("A Buyer with same phone number already exists",actual);
		
		//User already exists
		bdto.setEmail("joel@gmail.com");
		bdto.setPhonenumber("9869024567");//diff phone no.So no error from this one
		Mockito.when(brepo.findByEmail(bdto.getEmail())).thenReturn(buyer);
		String actual2=bservice.saveBuyer(bdto);
		Assertions.assertEquals("Cannot register as Buyer already registered and is active",actual2);
	}
	
	
	//Valid
		@Test
		public void sellerloginTestValid()  {
			LoginDTO ldto= new LoginDTO();
			ldto.setEmail("Gotia@gmail.com");
			ldto.setPassword("Vignesh@01");
			
			Seller seller= new Seller();
			 seller.setEmail("Gotia@gmail.com");
			 seller.setPassword("Vignesh@01");
			 seller.setIsactive(true);
			
			Mockito.when(srepo.findByEmail(ldto.getEmail())).thenReturn(seller);
			
			boolean res = sellerS.login(ldto);
			Assertions.assertEquals(true,res);
		} 
		
		
		//Invalid 
		@Test
		public void sellerloginTestInValid()  {
			LoginDTO ldto= new LoginDTO();
			ldto.setEmail("Gotia@gmail.com");
			ldto.setPassword("Vignesh@01");
			
			//Wrong password
			Seller seller= new Seller();
			 seller.setEmail("Gotia@gmail.com");
			 seller.setPassword("Vgnes@01");
			 seller.setIsactive(true);
			
			 Mockito.when(srepo.findByEmail(ldto.getEmail())).thenReturn(seller);
				
				boolean res =sellerS.login(ldto);
				Assertions.assertEquals(false,res);
			
			//User is inactive
				 seller.setIsactive(false);
				
				 Mockito.when(srepo.findByEmail(ldto.getEmail())).thenReturn(seller);
					
					boolean res2 =sellerS.login(ldto);
					Assertions.assertEquals(false,res2);
				
			

			//Wrong username
			Mockito.when(srepo.findByEmail(ldto.getEmail())).thenReturn(null);
			boolean res3 = sellerS.login(ldto);
			Assertions.assertEquals(false,res3);
		
		}
		
		
		@Test
		public void sellerRegisterValid() {
			Seller seller= new Seller();
			SellerDTO sdto= new SellerDTO();
			 sdto.setName("Roli");
			 sdto.setEmail("roli@gmail.com");
			 sdto.setPhonenumber("9834127658");
			 sdto.setPassword("Roli@01");
			
			Mockito.when(srepo.findByPhonenumber(sdto.getPhonenumber())).thenReturn(null);
			Mockito.when(srepo.save(seller)).thenReturn(seller);//not null
			
			String actual=sellerS.saveSeller(sdto);
			Assertions.assertEquals("Seller Registered successfully",actual);
		}
		

		@Test
		public void sellerRegisterInValid() {
			
			Seller seller= new Seller();
			seller.setSellerid(2);
			seller.setEmail("joel@gmail.com");
			seller.setPhonenumber("9834127658");
			seller.setName("Joel");
			seller.setPassword("Joel@123");
			seller.setIsactive(true);
		 
			SellerDTO sdto= new SellerDTO();
			 sdto.setName("Roli");
			 sdto.setEmail("roli@gmail.com");
			 sdto.setPhonenumber("9834127658");
			 sdto.setPassword("Roli@01");
			 
			//Phone number already there
			Mockito.when(srepo.findByPhonenumber(sdto.getPhonenumber())).thenReturn(seller);
			Mockito.when(srepo.save(seller)).thenReturn(null);//not null
			String actual=sellerS.saveSeller(sdto);			
			Assertions.assertEquals("A Seller with same phone number already exists",actual);
		
			//Seller already exists
			 sdto.setEmail("joel@gmail.com");
			 sdto.setPhonenumber("9869024567");//diff phone no.So no error from this one
			Mockito.when(srepo.findByEmail( sdto.getEmail())).thenReturn(seller);
			String actual2=sellerS.saveSeller(sdto);
			Assertions.assertEquals("Cannot register as Seller already registered and is active",actual2);
		}
		
		
		
	
	
	
	
}


