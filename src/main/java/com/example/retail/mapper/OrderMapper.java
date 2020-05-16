package com.example.retail.mapper;

import com.example.retail.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface OrderMapper {

    @Select("INSERT INTO orders (itemName, quantity) VALUES" +
            "(#{itemName},#{quantity}) RETURNING orderId")
    long insertOrder(Order order);

    List<String> getItems();

    @Select("SELECT * FROM orders WHERE orderId = #{id}")
    Order getOrderById(int id);
}
