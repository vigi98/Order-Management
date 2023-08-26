package com.app.fa.order.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.fa.order.dto.OrderDTO;
import com.app.fa.order.dto.ProductsorderedDTO;
import com.app.fa.order.entity.Order;
import com.app.fa.order.entity.Productsordered;
import com.app.fa.order.entity.ProductsorderedId;
import com.app.fa.order.repository.OrderRepository;
import com.app.fa.order.repository.ProductsorderedRepository;

@Service
@Transactional
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsorderedRepository poRepo;
	
	 @Autowired
	 OrderRepository oRepo;

	public String createOrder(OrderDTO odto) {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new Date(millis);  
		//System.out.println(date);  
		odto.setDate(date);
		odto.setStatus("Order placed");
		Order order =oRepo.save(odto.createEntity());
		logger.info("Ordered saved {}",order);
		return "Order placed successfully";
	}
	
	public List<OrderDTO> getAllOrders(){
		List<Order> order =oRepo.findAll();
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		for(Order or : order) {
			logger.info("Getting order:{}",or);
			orderList.add(OrderDTO.valueOf(or));
		}
		return orderList;
	}
	
	public String createproductOrder(ProductsorderedDTO podto) {
		
		Productsordered po= podto.createEntity();
		Productsordered psave=poRepo.save(po);
		logger.info("Product Ordered successful {}",psave);
		return "Product Order Successfull";
	}
	
	public List<ProductsorderedDTO> getProductOrderBasedOnBuyerId(int buyerid){
		List<Productsordered> prodered =poRepo.findByBuyerid(buyerid);
		List<ProductsorderedDTO> productorderedList = new ArrayList<ProductsorderedDTO>();
		for(Productsordered pr : prodered) {
			productorderedList.add(ProductsorderedDTO.valueOf(pr));
		}
		return productorderedList;
	}
	
	public OrderDTO getOrderBasedOnOrderId(int orderid) {
		Order order =oRepo.findById(orderid).orElse(null);
		logger.info(" Order info {}",order);
		if(order==null)
			 return null;
		OrderDTO odto= OrderDTO.valueOf(order);	
		return odto;
		
	}
	
	public ProductsorderedDTO  getProductOrderBasedOnBuyerIdAndProdId(int buyerid,int prodid) {
		ProductsorderedId poid= new ProductsorderedId(buyerid,prodid);
		Productsordered porder =poRepo.findById(poid).orElse(null); 
		logger.info(" ProductOrdered info {}",porder);
		if(porder==null)
			 return null;
		ProductsorderedDTO podto= ProductsorderedDTO.valueOf(porder);	
		return podto;
		
	}
	
	public List<OrderDTO> getOrderBasedOnBuyerId(int buyerid){
		List<Order> order =oRepo.findByBuyerid(buyerid);
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		for(Order or : order) {
			orderList.add(OrderDTO.valueOf(or));
		}
		return orderList;
	} 
	
	public Order updateOrderStatus(int orderid, String status) throws Exception {
		
		logger.info("Request for updation of status for order id :{} ", orderid);
		if(status.equals("Order Placed")|| status.equals("Packing")||status.equals("Dispatched")||status.equals("Delivered")) {
		Order existingOrder = oRepo.findById(orderid).orElse(null);
		 if(existingOrder == null) {
			  throw new Exception("Order Id does not exists");
	        }
		 
		 existingOrder.setStatus(status);;
         return oRepo.save(existingOrder);
		}
		else {
			throw new Exception("status value : "+status+" is wrong");
		}
	}
}
