package com.app.fa.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fa.user.dto.LoginDTO;
import com.app.fa.user.dto.SellerDTO;
import com.app.fa.user.entity.Seller;
import com.app.fa.user.repository.SellerRepository;

@Service
@Transactional
public class SellerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SellerRepository sRepo;
	
	//Login as a seller
		public boolean login(LoginDTO loginDTO) {
			Seller seller = sRepo.findByEmail(loginDTO.getEmail());
			if (seller!= null && seller.getPassword().equals(loginDTO.getPassword())&& seller.isIsactive()==true) {
				return true;
			}
			return false;
		}
		
		//deactivate a Seller
		public boolean deActivate(int sellerid) {
			Seller existingseller = sRepo.findById(sellerid).orElse(null);
			
			//sellerid doesnot exists or is already deactivated
			if(existingseller == null||existingseller.isIsactive()==false) {
				return false;
		        }
			
		   existingseller.setIsactive(false);
	       Seller sUps=  sRepo.save(existingseller);
	       logger.info("Updated Seller to inactive {}:",sUps);
	        //return "Seller Id :"+sellerid+" deactivated";	
	       return true;
		}
		
		//delete a Seller if it is inactive
		public int deleteSeller(String email ) {
			Seller seller = sRepo.findByEmail(email);
			logger.info("Searching seller if registered earlier :{}",seller);
			if(seller!=null && seller.isIsactive()==false)
			{
			  sRepo.deleteById(seller.getSellerid());
			  logger.info("Seller was inactive earlier , now deleted");
			  return 0;
			}
			
			if(seller!=null &&seller.isIsactive()==true)
			  return 1;
			
			return 0;
		}
		
		//Getting All Sellers
		public List<SellerDTO> getAllSeller() {

			List<Seller> sellers =sRepo.findAll();
			List<SellerDTO> sellerDTOs = new ArrayList<>();

			for (Seller seller : sellers) {
				SellerDTO sellerDTO = SellerDTO.valueOf(seller);
				sellerDTOs.add(sellerDTO);
			}

			logger.info("Seller Details : {}", sellerDTOs);
			return sellerDTOs;
		}
		
		//Register a Seller
		public String saveSeller(SellerDTO sellerDTO) {
	        int ifPresent=deleteSeller(sellerDTO.getEmail());
	        if(ifPresent==1) {
	        	 logger.info("Seller is already registered and active so cannot register");
	        	 return "Cannot register as Seller already registered and is active";
	        }
	        
	        Seller issellerAvailableWithPhoneNumber = sRepo.findByPhonenumber(sellerDTO.getPhonenumber());
	        if(issellerAvailableWithPhoneNumber!=null)
	        	return "A Seller with same phone number already exists";
	        
	        sellerDTO.setIsactive(true);
	        Seller seller = sellerDTO.createEntity();
	        sRepo.save(seller);
	        logger.info("Seller registered : {}", seller);
	        return "Seller Registered successfully";
	    }

		public SellerDTO getSellerById(int sellerid) {
			 Seller seller= sRepo.findById(sellerid).orElse(null);
			 
			 if(seller==null)
				  return null;
			 
			 return SellerDTO.valueOf(seller);
		}

}
