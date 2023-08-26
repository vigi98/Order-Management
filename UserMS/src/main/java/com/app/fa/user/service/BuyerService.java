package com.app.fa.user.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fa.user.dto.BuyerDTO;
import com.app.fa.user.dto.CartDTO;
import com.app.fa.user.dto.LoginDTO;
import com.app.fa.user.dto.WishlistDTO;
import com.app.fa.user.entity.Buyer;
import com.app.fa.user.entity.Cart;
import com.app.fa.user.entity.CartId;
import com.app.fa.user.entity.Wishlist;
import com.app.fa.user.entity.WishlistId;
import com.app.fa.user.repository.BuyerRepository;
import com.app.fa.user.repository.CartRepository;
import com.app.fa.user.repository.WishlistRepository;

@Service
@Transactional
public class BuyerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerRepository bRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	WishlistRepository wishRepo;
	

	//Register a buyer
	public String saveBuyer(BuyerDTO buyerDTO) {
        int ifPresent=deleteBuyer(buyerDTO.getEmail());
        if(ifPresent==1) {
        	 logger.info("Buyer is already registered and active so cannot register");
        	 return "Cannot register as Buyer already registered and is active";
        }
        
        Buyer isbuyerAvailable = bRepo.findByPhonenumber(buyerDTO.getPhonenumber());
        if(isbuyerAvailable!=null)
        	return "A Buyer with same phone number already exists";
        buyerDTO.setIsactive(true);
        Buyer buyer = buyerDTO.createEntity();
        Buyer buyers=bRepo.save(buyer);
        logger.info("Buyer registered : {}", buyers);
        return "Buyer Registered successfully";
    }
	//Getting All Buyers
	public List<BuyerDTO> getAllBuyer() {

		List<Buyer> buyers =bRepo.findAll();
		List<BuyerDTO> buyerDTOs = new ArrayList<>();

		for (Buyer buyer : buyers) {
			BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
			buyerDTOs.add(buyerDTO);
		}

		logger.info("Buyer Details : {}", buyerDTOs);
		return buyerDTOs;
	}
	
	//Login as a buyer
	public boolean login(LoginDTO loginDTO) {
		Buyer buy = bRepo.findByEmail(loginDTO.getEmail());
		if (buy != null && buy.getPassword().equals(loginDTO.getPassword())&& buy.isIsactive()==true) {
			return true;
		}
		return false;
	}
	
	//deactivate a buyer
	public String deActivate(int buyerid) {
		Buyer existingbuyer = bRepo.findById(buyerid).orElse(null);
		//buyer id doesnot exists or is already deactivated
		 if(existingbuyer == null||existingbuyer.isIsactive()==false) {
			  return "Buyer Id:"+buyerid+" does not exists";
	        }
		 existingbuyer.setIsactive(false);
		String del=deleteCartandWishlistBasedOnBuyerId(buyerid); 
       Buyer bUps=  bRepo.save(existingbuyer);
       logger.info("Updated Buyer to inactive {}:",bUps);
         return "Buyer Id :"+buyerid+" deactivated."+del;	
	}
	
	public String deleteCartandWishlistBasedOnBuyerId(int buyerid) {
	  	cartRepo.deleteByBuyerid(buyerid);
	  	wishRepo.deleteByBuyerid(buyerid);
	  	return "Contents of buyer in cart and wishlist is also is deleted,if any";
	}
	
	//delete a buyer if it is inactive
	public int deleteBuyer(String email ) {
		Buyer buyer = bRepo.findByEmail(email);
		logger.info("Searching buyer if registered earlier :{}",buyer);
		if(buyer!=null && buyer.isIsactive()==false)
		{
		  bRepo.deleteById(buyer.getBuyerid());
		  logger.info("Buyer was inactive earlier , now deleted");
		  return 0;
		}
		
		if(buyer!=null && buyer.isIsactive()==true)
		  return 1;
		
		return 0;
	}
	
	public String addToCart(CartDTO cartDTO) {
		
		if(getBuyerById(cartDTO.getBuyerid()).isIsactive()==false)
			return "Buyer does not exist";
		
		Cart cart = cartDTO.createEntity();
		Cart cartS=cartRepo.save(cart);
        logger.info("Added to cart value : {}", cartS);
        return "Added to Cart successfully";
	}
	
	public String addToWishlist(WishlistDTO wDTO) {
		
		if(this.checkWishAlreadyExists(wDTO.getBuyerid(),wDTO.getProdid()))
			return "Wishlist Already Exists";
		
		if(getBuyerById(wDTO.getBuyerid()).isIsactive()==false)
			return "Buyer does not exist";
		
		Wishlist wish=wDTO.createEntity();
		Wishlist wS=wishRepo.save(wish);
		logger.info("Wishlist radded : {}",wS);
        return "Added product: "+wDTO.getProdid()+" to Wishlist successfully";
	}
	
	public boolean checkWishAlreadyExists(int buyerid, int prodid) {
		
		return wishRepo.findByBuyeridAndProdid(buyerid,prodid)!=null;
	}
	
	//Check the cart already contains
	public boolean checkCartAlreadyExists(int buyerid,int prodid) {
		//CartId ob= new CartId(buyerid,prodid);
		return cartRepo.findByBuyeridAndProdid(buyerid,prodid)!=null;
	}
	
	//Delete from wishlist
	public String deleteWishlist(int buyerid, int prodid) {
		if(wishRepo.findByBuyeridAndProdid(buyerid,prodid)==null)
			return "Wrong prodid or buyerid";
		WishlistId wid=new WishlistId(buyerid,prodid);
		wishRepo.deleteById(wid);
		return "Deleted SuccessFully from wishlist";
	}
	
	//Get All Carts
	public List<CartDTO> getAllCart() {
		List<Cart> carts =cartRepo.findAll();
		List<CartDTO> cartDTOs = new ArrayList<>();
		for (Cart cart : carts) {
			CartDTO cartDTO = CartDTO.valueOf(cart);
			cartDTOs.add(cartDTO);
		}

		logger.info("Cart Details : {}", cartDTOs);
		return cartDTOs;
	}
	
	//Delete from Cart
	public String deletefromCart(int buyerid, int prodid) {
		if(cartRepo.findByBuyeridAndProdid(buyerid,prodid)==null)
			return "Wrong prodid or buyerid";
		CartId cid=new CartId(buyerid,prodid);
		cartRepo.deleteById(cid);
		return "Deleted SuccessFully from cart";
	}
	
	//Get Specific Cart
	public CartDTO getCartById(int buyerid, int prodid) {
		CartId cid=new CartId(buyerid,prodid);
		Cart cart= cartRepo.findById(cid).orElse(null);
		return CartDTO.valueOf(cart);
	}
	
	//Get Carts Based on BuyerId
	public List<CartDTO> getBasedOnBuyerIdCart(int buyerid) {
		List<Cart> carts =cartRepo.findByBuyerid(buyerid);
		List<CartDTO> cartDTOs = new ArrayList<>();
		for (Cart cart : carts) {
			CartDTO cartDTO = CartDTO.valueOf(cart);
			cartDTOs.add(cartDTO);
		}

		logger.info("Cart Details : {}", cartDTOs);
		return cartDTOs;
	}
	
	//Get By Buyer Id
	public BuyerDTO getBuyerById(int buyerid) {
		Buyer buyer=bRepo.findById(buyerid).orElse(null);
		if(buyer==null)
			return null;
		BuyerDTO bdto= BuyerDTO.valueOf(buyer);
		return bdto;
	}

    public BuyerDTO updateBuyersRewardPoints(int buyerid,int rewardpoints) {

		logger.info("Request for updation of reward points in Buyer with buyerid:{} ", buyerid);	
    	Buyer existingBuyer=bRepo.findById(buyerid).orElse(null);
    	
    	 if(existingBuyer != null) {
    		 existingBuyer.setRewardpoints(rewardpoints);
    		 Buyer  buyer= bRepo.save(existingBuyer); 
	         return BuyerDTO.valueOf(buyer);
	        }
    	 
	        return null;
    }
    //Get All Wishlist
	public List<WishlistDTO> getAllWishlist() {
		
		List<Wishlist> wishAll=wishRepo.findAll();
		List<WishlistDTO>wdtos=new ArrayList<>();
		
		for (Wishlist w : wishAll) {
			WishlistDTO wdto = WishlistDTO.valueOf(w);
			wdtos.add(wdto);
			}

		logger.info("Wishlist Details : {}", wdtos);
		return wdtos;
	}
    //Get Wishlist based on buyerid
	public List<WishlistDTO> getWishlistBasedOnBuyerId(int buyerid) {
		List<Wishlist> wishAll=wishRepo.findByBuyerid(buyerid);
		List<WishlistDTO>wdtos=new ArrayList<>();
		
		for (Wishlist w : wishAll) {
			WishlistDTO wdto = WishlistDTO.valueOf(w);
			wdtos.add(wdto);
			}

		logger.info("Wishlist Details : {}", wdtos);
		return wdtos;
	}
	
	public String applyPriviledge(int buyerid) {
		// TODO Auto-generated method stub
		
		Buyer buyer=bRepo.findById(buyerid).orElse(null);
    	
		if(buyer==null||buyer.isIsactive()==false)
			return "Wrong Buyer Id";
		
		int rewardpoints=buyer.getRewardpoints();
		
		if(rewardpoints>=10000) {
			buyer.setIsprivileged(true);
			bRepo.save(buyer); 
			return "Congrats Now You Are  a Priveledged Buyer";
		}
		return "Sorry Not Enough Reward Points(Has to be minimum 10000) and you have :"+rewardpoints;
	}
	
}
