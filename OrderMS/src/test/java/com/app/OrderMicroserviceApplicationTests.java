package com.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.fa.order.dto.OrderDTO;
import com.app.fa.order.entity.Order;
import com.app.fa.order.repository.OrderRepository;
import com.app.fa.order.repository.ProductsorderedRepository;
import com.app.fa.order.service.OrderService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMicroserviceApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Mock
	OrderRepository orderRepository;

	@Mock
	ProductsorderedRepository productsOrderedRepo;
	@InjectMocks
	OrderService orderService = new OrderService();


	@Test
	public void orderValidTest()  {
		List<Order> orderList = new ArrayList<Order>();

		Order orderEntity = new Order();
		orderEntity.setOrderid(1);
		orderEntity.setBuyerid(2);
		orderEntity.setAmount(1000.0);
		orderEntity.setAddress("KUPT kdd");
		//orderEntity.setDate(new Date(2020-9-12));
		orderEntity.setStatus("ORDER PLACED");

		orderList.add(orderEntity);

		Mockito.when(orderRepository.findAll()).thenReturn(orderList);

		List<OrderDTO> reProduct = orderService.getAllOrders();

		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());

	}

	@Test
	public void orderInvalidTest()  {
		List<Order> orderList = new ArrayList<Order>();

		Order orderEntity = new Order();
		
//		orderEntity.setOrderid("20");
//		orderEntity.setBuyerid("B101");
//		orderEntity.setAmount(1000.0);
//		orderEntity.setAddress("KUPT");
//		orderEntity.setOrderdate(new Date(2020-9-12));
//		orderEntity.setStatus("ORDERPLACED");
		

		
		Optional opt = Optional.of(orderEntity);// Valid

		Optional opt1 = Optional.empty();// Invalid
		

		Mockito.when(orderRepository.findById(Mockito.anyInt())).thenReturn(opt1);

//	     Mockito.when(productrepo.findAll()).thenReturn(productList);

		List<OrderDTO> reProduct = orderService.getAllOrders();
//	          System.out.println(reProduct.get(0));
		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());
	}
}
