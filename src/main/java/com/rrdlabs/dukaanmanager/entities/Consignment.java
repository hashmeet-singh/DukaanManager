package com.rrdlabs.dukaanmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "product_supplier")
public class Consignment {

    @Id
    @Column(name = "product_delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "delivery_date")
    @NotNull
    private LocalDate deliveryDate;

    @Column(name = "delivery_qty")
    @Positive(message = "Consignment Quantity must be greater than 0")
    private int deliveryQuantity;

    @Column(name = "landing_price")
    @PositiveOrZero(message = "Price cannot be less than 0")
    private double landingPrice;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Consignment() {
    }

    public Consignment(Product product, Supplier supplier, LocalDate deliveryDate, int deliveryQuantity, double landingPrice, Timestamp createdAt) {
        this.product = product;
        this.supplier = supplier;
        this.deliveryDate = deliveryDate;
        this.deliveryQuantity = deliveryQuantity;
        this.landingPrice = landingPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public double getLandingPrice() {
        return landingPrice;
    }

    public void setLandingPrice(double landingPrice) {
        this.landingPrice = landingPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
