package com.rrdlabs.dukaanmanager.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class LineItemDto {

    @NotNull
    private Long productId;

    @PositiveOrZero(message = "Price cannot be less than 0")
    private double price;

    @Positive(message = "Minimum quantity required is atleast 1")
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
