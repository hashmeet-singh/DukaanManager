package com.rrdlabs.dukaanmanager.entities;

import com.rrdlabs.dukaanmanager.dto.OrderItemDto;

import java.util.List;

public class OrderRequest {
    private Long customerId;
    private Long staffId;
    private List<OrderItemDto> orderItems;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
