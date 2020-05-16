package com.example.retail.model;

public class Order {

    private long orderId;

    private String itemName;

    private Integer quantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Order(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Order() {
    }
}