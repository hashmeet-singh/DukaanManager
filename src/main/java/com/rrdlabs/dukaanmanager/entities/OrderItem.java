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
    private double price;

    @Column(name = "status")
    private String status;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, double price, String status) {
        orderItemId = new OrderItemId();
        orderItemId.setOrder(order);
        orderItemId.setProduct(product);
        this.quantity = quantity;
        this.price = price;
        this.status = status;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
