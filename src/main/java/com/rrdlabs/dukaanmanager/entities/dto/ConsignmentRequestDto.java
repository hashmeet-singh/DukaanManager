package com.rrdlabs.dukaanmanager.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class ConsignmentRequestDto {

    @NotNull
    private Long supplierId;

    @NotNull
    private List<LineItemDto> lineItems;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate deliveryDate;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDto> lineItems) {
        this.lineItems = lineItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
