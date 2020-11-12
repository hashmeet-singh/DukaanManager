package com.rrdlabs.dukaanmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    @JsonIgnore
    private OrderItemId orderItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double sellingPrice;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, double sellingPrice) {
        orderItemId = new OrderItemId();
        orderItemId.setOrder(order);
        orderItemId.setProduct(product);
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }

    public OrderItemId getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(OrderItemId orderItemId) {
        this.orderItemId = orderItemId;
    }

    @Transient
    public Product getProduct() {
        return this.orderItemId.getProduct();
    }

    @Transient
    public double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double price) {
        this.sellingPrice = price;
    }

}
