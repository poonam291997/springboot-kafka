package com.example.retail;

import com.example.retail.mapper.OrderMapper;
import com.example.retail.model.Order;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDAOTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void testInsertOrder() {
        // create test object
        Order testOrder = new Order("corns", 5);
        // insert data
        long orderId = orderMapper.insertOrder(testOrder);
        Assert.assertEquals("corns", orderMapper.getOrderById(((int) orderId)).getItemName());
        Assert.assertEquals(5, orderMapper.getOrderById(((int) orderId)).getQuantity().intValue());

    }

}
