package com.app.fa.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.fa.order.dto.OrderDTO;
import com.app.fa.order.dto.ProductsorderedDTO;
import com.app.fa.order.entity.Order;
import com.app.fa.order.service.OrderService;

@RestController
@CrossOrigin
public class OrderController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderService orderService;
	
	@Autowired
	DiscoveryClient client;

	
	@PostMapping( value = "/order",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createOrder(@Valid@RequestBody OrderDTO odto) {
		logger.info("Creation request for order {}", odto);
		return orderService.createOrder(odto);
	}
	
	//Getting all Orders
    @GetMapping(value = "/order",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getAllOrders() {
		logger.info(" Request for All orders ");
        return orderService.getAllOrders();
	}
	
    //Adding Products Ordered
    @PostMapping(value = "/order/productordered",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createProductOrdered(@Valid@RequestBody ProductsorderedDTO podto) {
		logger.info("Creation request for product ordered: {}",podto);
		return orderService.createproductOrder(podto);
	}
    //Getting Products Ordered Based on BuyerId
    @GetMapping(value = "/order/productordered/{buyerid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsorderedDTO> getProductOrderBasedOnBuyerId(@PathVariable Integer buyerid) {

		logger.info("Request for products ordered based on buyer id {}",buyerid);

		return orderService.getProductOrderBasedOnBuyerId(buyerid);
	}
    
    //Getting Products Ordered Based on BuyerId and prodId
    @GetMapping(value = "/order/productordered/{buyerid}/{prodid}",produces = MediaType.APPLICATION_JSON_VALUE)
   	public ProductsorderedDTO getProductOrderBasedOnBuyerIdAndProdId(@PathVariable Integer buyerid,@PathVariable Integer prodid) {

   		logger.info("Request for products ordered based prodid and buyerid id",buyerid);

   		return orderService.getProductOrderBasedOnBuyerIdAndProdId(buyerid,prodid);
   	}
    
    
    //Getting Order Based On Order Id
    @GetMapping(value = "/order/{orderid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO getOrderBasedOnOrderId(@PathVariable Integer orderid) {
		logger.info("Request for Order based on Order id {}",orderid);
		return orderService.getOrderBasedOnOrderId(orderid);
	}
    
    //Getting Orders Based on BuyerId
    @GetMapping(value = "/order/buyer/{buyerid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getOrderBasedOnBuyerId(@PathVariable Integer buyerid) {
		logger.info("Request for Order based on Order id {}",buyerid);
		return orderService.getOrderBasedOnBuyerId(buyerid);
	}
	
    //Updating Status of Order
    @PatchMapping(value="/order/{orderid}")
    public Order updateOrder(@PathVariable Integer orderid,@RequestBody OrderDTO odto) throws Exception{
		return orderService.updateOrderStatus(orderid,odto.getStatus());
	}
	
}
