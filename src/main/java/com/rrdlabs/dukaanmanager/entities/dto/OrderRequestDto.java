package com.rrdlabs.dukaanmanager.entities.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long staffId;

    @NotNull
    private List<LineItemDto> lineItems;

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

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDto> orderItems) {
        this.lineItems = orderItems;
    }
}
