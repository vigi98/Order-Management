package com.app.fa.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.fa.user.dto.BuyerDTO;
import com.app.fa.user.dto.CartDTO;
import com.app.fa.user.dto.LoginDTO;
import com.app.fa.user.dto.OrderDTO;
import com.app.fa.user.dto.ProductDTO;
import com.app.fa.user.dto.ProductsorderedDTO;
import com.app.fa.user.dto.SellerDTO;
import com.app.fa.user.dto.SubscribedproductDTO;
import com.app.fa.user.dto.WishlistDTO;
import com.app.fa.user.service.BuyerService;
import com.app.fa.user.service.SellerService;

@RestController
@CrossOrigin
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerService buyerservice;
	
	 @Autowired
	  SellerService sellerservice;
	
	 //Register Buyer
	 @PostMapping(value = "/buyer/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String createBuyer(@Valid@RequestBody BuyerDTO buyerDTO) {
	    	logger.info("Register request for buyer {}",buyerDTO);
	        return buyerservice.saveBuyer(buyerDTO);
	    }
	 
	 //Get All Buyer
	 @GetMapping(value = "/buyer", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<BuyerDTO> getAllBuyer() {
			logger.info("Fetching all Buyers");
			return buyerservice.getAllBuyer();
		}
	 
	 //Get Buyer By BuyerId
	 @GetMapping(value = "/buyer/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public BuyerDTO getBuyerById(@PathVariable int buyerid) {
			logger.info("Fetching particular Buyers");
			return buyerservice.getBuyerById(buyerid);
		}
	 
	 //Login 
	 @PostMapping(value = "/buyer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
		public String loginForBuyer(@Valid@RequestBody LoginDTO loginDTO) {
			logger.info("Login request for customer {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			boolean success=buyerservice.login(loginDTO);
			if(success) {
				return "Login Successful";
			}
			
			return "Invalid Username or Password";
		}
	 
	 //Deactivate Buyer
	 @PatchMapping(value="/buyer/deactivate/{buyerid}")
	 public String deActivateBasedOnBuyerId(@PathVariable Integer buyerid) {
		 logger.info("Deactivation Request {}",buyerid);
		 return buyerservice.deActivate(buyerid);
	 }
	 
	 //Adding Product to cart
	 @PostMapping(value = "/buyer/cart/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public String addToCart(@Valid@RequestBody CartDTO cartDTO) {
	    	logger.info("Request for addition of product to cart",cartDTO);
	    	
	    	//Check if product exists or is there enough quantity available in stock
	    	ProductDTO pdto= new RestTemplate().getForObject("http://localhost:8400/product/"+cartDTO.getProdid(),ProductDTO.class);
	        BuyerDTO bdto=buyerservice.getBuyerById(cartDTO.getBuyerid());
	       
	        if(bdto==null||bdto.isIsactive()==false)
	        	return "Invalid buyer id";
	    	
	        if(pdto==null) {
	        	return "Invalid product id";
	        }
	        
	        if(pdto.getStock()<10)
	        	return "Sorry Not enough stock in inventory so cant take order for this product now";
	        
	        if(buyerservice.checkCartAlreadyExists(cartDTO.getBuyerid(), cartDTO.getProdid()))
	        	return "Cannot add to cart as already exists";
	        
	       
	    	if(pdto.getStock()<cartDTO.getQuantity()) {
	        	return "Cannot add to cart as required quantity("+cartDTO.getQuantity()+") is unavailabe.Stock availabe : "+pdto.getStock();
	        }
	    	
	    	String success=buyerservice.addToCart(cartDTO);
	    	return success;
	    }
	 
	    //Apply for priveledge buyer
		 @PatchMapping(value = "/buyer/apply/priviledge/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
			public String applyPriviledge(@PathVariable int buyerid) {
				logger.info("Apply For priveledge");
				return buyerservice.applyPriviledge(buyerid);
			}
		 
		 //Subscribe to product
		 @PostMapping(value = "/buyer/priviledge/subscribe/", consumes = MediaType.APPLICATION_JSON_VALUE)
		 public String subscribeToProduct(@Valid@RequestBody SubscribedproductDTO sdto) {
			 
			 BuyerDTO bdto=buyerservice.getBuyerById(sdto.getBuyerid());
			 
			 if(bdto==null||bdto.isIsactive()==false)
				 return "Invalid Buyer Id";
			 
			 if(bdto.isIsprivileged()==false)
				 return "Sorry Only Priviledge Buyers Allowed";
			 
			 ProductDTO pdto= new RestTemplate().getForObject("http://localhost:8400/product/"+sdto.getProdid(),ProductDTO.class);
			 
			 if(pdto==null)
				 return "Product does not exists";
			 
			 String res=new RestTemplate().postForObject("http://localhost:8400/subscriptions/add",sdto,String.class);
			 
			 return res;
		 }
	 
	 //Add to wishlist
	 @PostMapping(value = "/buyer/wishlist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public String addToWishlist(@Valid@RequestBody WishlistDTO wDTO) {
	    	logger.info("Request for addition of product to wishlist",wDTO);
	    	
	    	BuyerDTO bdto=buyerservice.getBuyerById(wDTO.getBuyerid());
		       
	        if(bdto==null)
	        	return "Invalid buyer id";
	    	
	    	ProductDTO pdto= new RestTemplate().getForObject("http://localhost:8400/product/"+wDTO.getProdid(),ProductDTO.class);
	        if(pdto==null) {
	        	return "Invalid product id";
	        }
	    	String success=buyerservice.addToWishlist(wDTO);
	    	return success;
	    }
	 
	 //Get All in Wishlist
	 @GetMapping(value = "/buyer/wishlist", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<WishlistDTO> getAllWishlist() {
			logger.info("Fetching everything from wishlist");
			return buyerservice.getAllWishlist();
		}
	 
	 //Get Wishlist based on buyerid
	 @GetMapping(value = "/buyer/wishlist/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<WishlistDTO> getWishlistBasedOnByerId(@PathVariable int buyerid) {
			logger.info("Fetching everything from wishlist");
			return buyerservice.getWishlistBasedOnBuyerId(buyerid);
		} 
	 
	 
	 //Getting products from cart based on buyerid 
	 @GetMapping(value = "/buyer/cart/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<CartDTO> getProductsBasedOnBuyerIdCart(@PathVariable int buyerid) {
			logger.info("Fetching Products Based on BuyerId");
			return buyerservice.getBasedOnBuyerIdCart(buyerid);
		}
	 
	
	 
	 //Place Order
	 @PostMapping(value = "/buyer/placeorder/{availrpts}", consumes = MediaType.APPLICATION_JSON_VALUE)
	  public String PlaceOrder(@Valid@RequestBody OrderDTO oDTO,@PathVariable boolean availrpts) {
		 //Buyerid,address
		 
			String res="";
		//Getting Buyer Details
	    	BuyerDTO bdto=buyerservice.getBuyerById(oDTO.getBuyerid());
	    	logger.info("Buyer Info:"+bdto);
	    	
	    	if(bdto==null||bdto.isIsactive()==false)
	    		return "Buyer id is inactive";
	    	
	    	logger.info("Request for placing an order",oDTO);
	    	
	    	if(bdto.isIsprivileged()) {
	    		logger.info("Is a priveledged Buyer");
	    		res+="Is a priveledged Buyer\n";
	    	}
	    	
	    	double totalAmount=0;
	    	
	    	//Getting Cart Details
	    	List<CartDTO>cartDTO=getProductsBasedOnBuyerIdCart(oDTO.getBuyerid());
	    	if(cartDTO==null||cartDTO.size()==0)
	    		return "Nothing in the cart with this buyer id";
	   
	    	
	    	//Visititing each cart element
	    	for(CartDTO cdto:cartDTO) {
	    		int prodid=cdto.getProdid();
	    		int quantity=cdto.getQuantity();
	    		//Get the product
	    		ProductDTO pdto= new RestTemplate().getForObject("http://localhost:8400/product/"+prodid,ProductDTO.class);
	    	    //check if product exists
	    		if(pdto==null) {
	    	    	res+="Prodid:"+prodid+" does not exists anymore. \n";
	    	    }
	    		else if(pdto.getStock()<10) {
	    	      logger.info("Cannot place order for product: "+pdto+". As the stock is less");
	    	      res+="Cannot place order for product: "+pdto+". As the stock is less"+"\n";
	    	    }
	    		
	    		else if( pdto.getStock()<quantity) {
	    			logger.info("Cannot place order for product: "+pdto+". As the quantity ordered is greater than stock");
	    		    res+="Cannot place order for product: "+pdto+". As the quantity ordered is greater than stock"+"\n";
	    		}
	    	    
	    		else {
	    			//Can place order
	    			//Calculating amount
	    	    totalAmount+=quantity*pdto.getPrice();
	    	    
	    	    ProductDTO reqsend= new ProductDTO();
	    	    
		    	reqsend.setStock(pdto.getStock()-cdto.getQuantity());
		    	
		    	//Updating Product Stock
		    	pdto=new RestTemplate().postForObject("http://localhost:8400/product/buyer/"+prodid,reqsend,ProductDTO.class);
		    	logger.info("Stock Reduced to :"+pdto.getStock()+". Product details:"+pdto);
		    	
//		    	{
//		    		"buyerid":2,
//		    		"prodid":4,
//		    		"sellerid":2,
//		    		"quantity":7
//		    		}
		    	//Product Ordered
		    	ProductsorderedDTO prodordered=new ProductsorderedDTO();
		    	prodordered.setBuyerid(cdto.getBuyerid());
		    	prodordered.setProdid(pdto.getProdid());
		    	prodordered.setQuantity(cdto.getQuantity());
		    	prodordered.setSellerid(pdto.getSellerid());
		    	
		    	//Posting new product Ordered
		    	new RestTemplate().postForObject("http://localhost:8500/order/productordered",prodordered,String.class);
	    		logger.info("Products ordered by  {} are {}",cdto.getBuyerid(),prodordered);
	    		res+="Products ordered by"+cdto.getBuyerid()+" are "+prodordered+"\n";
	    		}
	           
	    		//Deleting the cart of just oredered product
	    		String delres=buyerservice.deletefromCart(cdto.getBuyerid(),prodid); 
	    		logger.info(delres);
	    		//res+=delres+"\n";
	    	}
	    	
	    	//Now have to calculate total bill and send to order    
	    	if(totalAmount==0)
	    		return res+"\n Nothing is ordered";
	    	res+="Total Amt: Rs."+totalAmount+"\n";
	    	
	    	int deliverycharge=40;
	    	
	    	int rewardPoints=bdto.getRewardpoints();
	    	
	    	//If Buyer Wants to avail Reward Points
	    	if(availrpts==true) {
	    		logger.info("Avail Rewards Given as yes");
	    		if(rewardPoints>4) {
	    		
	    			res+="\nCurrent Reward points are :"+rewardPoints;
	    	   //Should be able to use the reward points to avail discount, where 4 reward points equals 1₹
	    		int discount=rewardPoints/4;
	    		res+="Discount:"+discount+"\n";
	    		int remainingRewardPts=rewardPoints%4;
	    		//Implementing ghe discount
	    		totalAmount-=discount;
	    		rewardPoints=remainingRewardPts;
	    		res+="\nUpdated Reward points after subtraction are :"+rewardPoints;
	    		}
	    		else {
	    			res+="Can't avail discounts as not enough rewardPoints \n";
	    		}
	    	}
	        
	    	//Delivery Charge if non-privelege buyer
	    	if(bdto.isIsprivileged()==false)
	    	    totalAmount+=deliverycharge;
	    	
	    	//In the total bill amount reward 1 point for per 100₹ and addng to reward points of user
	    	int extraRewardPoints=(int) (totalAmount/100);
	    	rewardPoints+=extraRewardPoints;
	    	res+="\nNew Reward points  are :"+rewardPoints;
	         //Update Buyer's Reward Points
	    	logger.info("Buyer Updated to :"+buyerservice.updateBuyersRewardPoints(oDTO.getBuyerid(), rewardPoints));
	    	
	    	if(bdto.isIsprivileged()==false)
	    	  res+="\nTotal Amount by applying discount + Rs.40 shipping charges is :"+totalAmount+"\n";
	    	
	    	if(bdto.isIsprivileged()==true) {
	    		 res+="Total Amount by applying discount:"+totalAmount+"\n";
	    		 res+="And also no Shipping price for you.Wohoo!! \n";
	    	}
	    	//Now Sending Order
	    	//{
//	    	    "buyerid":1,
//	    	    "amount":1200,//totalAmount
//	    	    "address":"Bangalore West"
//	    	}
	    		
	       OrderDTO storingOrder=new OrderDTO();
	       storingOrder.setBuyerid(oDTO.getBuyerid());
	       storingOrder.setAddress(oDTO.getAddress());
	       storingOrder.setAmount(totalAmount);
	       res+=new RestTemplate().postForObject("http://localhost:8500/order",storingOrder,String.class);
	       return res;
	       
	    }
	 
	 //Getting orders based on orderid
	 @GetMapping(value = "/buyer/order/{orderid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public OrderDTO getOrderBasedOnOrderId(@PathVariable int orderid) {
			logger.info("Fetching orders based on orderid");
			
			OrderDTO odto= new RestTemplate().getForObject("http://localhost:8500/order/"+orderid,OrderDTO.class);
			if(odto==null)
				return odto;
			
			@SuppressWarnings("unchecked")
			List<ProductsorderedDTO> pordered= new RestTemplate().getForObject("http://localhost:8500/order/productordered/"+odto.getBuyerid(),List.class);
			odto.setPord(pordered);
			return odto;
		}
	 //Reordering
	 @PostMapping(value = "/buyer/reorder/{buyerid}/{prodid}/{availrpts}", produces = MediaType.APPLICATION_JSON_VALUE)
		public String doReorder(@PathVariable int buyerid,@PathVariable int prodid,@PathVariable boolean availrpts,@RequestBody OrderDTO oinp) {
			logger.info("Request for Reorder");
			
			
			ProductsorderedDTO checkifPreviouslyOrdered= new RestTemplate().getForObject("http://localhost:8500/order/productordered/"+buyerid+"/"+prodid,ProductsorderedDTO.class);;
			
			if(checkifPreviouslyOrdered==null)
				return "Not previously ordered this product";
			
			String res="";
			BuyerDTO bdto=buyerservice.getBuyerById(checkifPreviouslyOrdered.getBuyerid());
	    	logger.info("Buyer Info:"+bdto);
	    	
	    	//Validate for Buyer
	    	if(bdto.isIsactive()==false)
	    		return "Buyer id is inactive";
	    	
	    	logger.info("Request for placing an Reorder",checkifPreviouslyOrdered);
	    	
	    	if(bdto.isIsprivileged())
	    		logger.info("Is a priveledged Buyer");
	    	
	    	ProductDTO pdto= new RestTemplate().getForObject("http://localhost:8400/product/"+prodid,ProductDTO.class);
	    	//Validate for product
	    	if(pdto==null) {
    	    	return"Prodid:"+prodid+" does not exists anymore. \n";
    	    }
    		else if(pdto.getStock()<10) {
    	      logger.info("Cannot place order for product: "+pdto+". As the stock is less");
    	      return"Cannot place order for product: "+pdto+". As the stock is less"+"\n";
    	    }
    		
    		else if( pdto.getStock()<checkifPreviouslyOrdered.getQuantity()) {
    			logger.info("Cannot place order for product: "+pdto+". As the quantity ordered is greater than stock");
    		    return "Cannot place order for product: "+pdto+". As the quantity ordered is greater than stock"+"\n";
    		}
	    	
	    	//Both Buyer and Product Valid
	    	
	    	double totalAmount=0;
	    	
	    	totalAmount+=checkifPreviouslyOrdered.getQuantity()*pdto.getPrice();
    	    
    	    ProductDTO reqsend= new ProductDTO();
    	    
	    	reqsend.setStock(pdto.getStock()-checkifPreviouslyOrdered.getQuantity());
	    	
	    	//Updating Product Stock
	    	pdto=new RestTemplate().postForObject("http://localhost:8400/product/buyer/"+prodid,reqsend,ProductDTO.class);
	    	logger.info("Stock Reduced to :"+pdto.getStock()+". Product details:"+pdto);
	    	
             res+="Total Amt: Rs."+totalAmount+"\n";
	    	
	    	int deliverycharge=40;
	    	
	    	int rewardPoints=bdto.getRewardpoints();
	    	
	    	//If Buyer Wants to avail Reward Points
	    	if(availrpts==true) {
	    		logger.info("Avail Rewards Given as yes");
	    		if(rewardPoints>4) {
	    	    
	    	   //Should be able to use the reward points to avail discount, where 4 reward points equals 1₹
	    		int discount=rewardPoints/4;
	    		res+="Discount:"+discount+"\n";
	    		int remainingRewardPts=rewardPoints%4;
	    		//Implementing ghe discount
	    		totalAmount-=discount;
	    		rewardPoints=remainingRewardPts;
	    		}
	    		else {
	    			res+="Can't avail discounts as not enough rewardPoints \n";
	    		}
	    	}
	        
	    	//Delivery Charge if non-privelege buyer
	    	if(bdto.isIsprivileged()==false)
	    	    totalAmount+=deliverycharge;
	    	
	    	//In the total bill amount reward 1 point for per 100₹ and addng to reward points of user
	    	int extraRewardPoints=(int) (totalAmount/100);
	    	rewardPoints+=extraRewardPoints;
	    	
	         //Update Buyer's Reward Points
	    	logger.info("Buyer Updated to :"+buyerservice.updateBuyersRewardPoints(buyerid, rewardPoints));
	    	
	    	if(bdto.isIsprivileged()==false)
	    	  res+="Total Amount by applying discount + Rs.40 shipping charges is :"+totalAmount+"\n";
	    	
	    	if(bdto.isIsprivileged()==true) {
	    		 res+="Total Amount by applying discount:"+totalAmount+"\n";
	    		 res+="And also no Shipping price for you.Wohoo!! \n";
	    	}
	    	//Now Sending Order
	    	//{
//	    	    "buyerid":1,
//	    	    "amount":1200,//totalAmount
//	    	    "address":"Bangalore West"
//	    	}
	    		
	       OrderDTO storingOrder=new OrderDTO();
	       storingOrder.setBuyerid(buyerid);
	       storingOrder.setAddress(oinp.getAddress());
	       storingOrder.setAmount(totalAmount);
	       res+=new RestTemplate().postForObject("http://localhost:8500/order",storingOrder,String.class);
	       return res+"\nReorder successfully done";
			
	 }	 
	 
	 //Add from Wishlist to cart and delete from wishlist
	 @PostMapping(value = "/buyer/wishlist/cart", consumes = MediaType.APPLICATION_JSON_VALUE)
	 public String addFromWishlistToCart(@Valid@RequestBody CartDTO cartDTO) {
		
		 if(buyerservice.checkWishAlreadyExists(cartDTO.getBuyerid(), cartDTO.getProdid())==false){
			 return "Wishlist does not exists";
		 }
		 
		 String messg=addToCart(cartDTO);
		 
		 messg+=" "+buyerservice.deleteWishlist(cartDTO.getBuyerid(), cartDTO.getProdid());
		 return messg;
	 }
	 
	 
	 //Delete my wishlist
	 @DeleteMapping(value = "/buyer/wishlist/{buyerid}/{prodid}")
		public String deleteWishlist(@PathVariable int buyerid,@PathVariable int prodid){
			
			return buyerservice.deleteWishlist(buyerid,prodid);
		}
	 
	 //Seeing all values in cart 
	 @GetMapping(value = "/buyer/cart", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<CartDTO> getAllProductsinCart() {
			logger.info("Fetching all Buyers");
			return buyerservice.getAllCart();
		}
	  
	 //Delete a product from cart
	 @DeleteMapping(value = "/buyer/cart/{buyerid}/{prodid}")
		public String deleteCart(@PathVariable int buyerid,@PathVariable int prodid){
			String res= buyerservice.deletefromCart(buyerid,prodid);
	    	return res;
			
		}
	 
	 //
	 
	 //SELLER
	 
     //Register a seller
	 @PostMapping(value = "/seller/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public String createSeller(@Valid@RequestBody SellerDTO sellerDTO) {
	    	logger.info("Register request for seller {}",sellerDTO);
	        return sellerservice.saveSeller(sellerDTO);
	    }
	 
	 //Getting All Seller
	 @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<SellerDTO> getAllSeller() {
			logger.info("Fetching all Sellers");
			return sellerservice.getAllSeller();
		}
	 
	 //Get Seller Id
	 @GetMapping(value = "/seller/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public SellerDTO getSellerById(@PathVariable int sellerid) {
			logger.info("Fetching Sellers By id");
			return sellerservice.getSellerById(sellerid);
		}
	 
	 //Login for seller
	 @PostMapping(value = "/seller/login", consumes = MediaType.APPLICATION_JSON_VALUE)
		public String loginForSeller(@Valid@RequestBody LoginDTO loginDTO) {
			logger.info("Login request for seller {} with password {}", loginDTO.getEmail(),loginDTO.getPassword());
			boolean success=sellerservice.login(loginDTO);
			if(success) {
				return "Login Successful";
			}
			
			return "Invalid Username or Password";
		}
	 
	 //Deactivating a seller and deletinghis products
	 @PatchMapping(value="/seller/deactivate/{sellerid}")
	 public String deActivateBasedOnSellerId(@PathVariable Integer sellerid) {
		 logger.info("Deactivation Request {}",sellerid);
		 boolean isSuccess=sellerservice.deActivate(sellerid);
		 
		 if(!isSuccess)
			return "Seller Id:"+sellerid+" does not exists";
		 new RestTemplate().delete("http://localhost:8400/product/seller/"+sellerid,String.class);
	     return "Seller with seller id:"+sellerid+" is deactivated.Also its products have also been deleted"; 
	 }   
	 
	 
    //Used By Product MS. Check seller is active or not
	 @GetMapping(value = "/seller/exists/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean CheckSellerIsactiveOrExists(@PathVariable int sellerid) {
			logger.info("Fetching Sellers By id");
			SellerDTO sdto=sellerservice.getSellerById(sellerid);
			if(sdto==null)
				return false;
			return sdto.isIsactive();
		}
   
}
