package com.rrdlabs.dukaanmanager.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "product_supplier")
public class ProductSupplier {

    @Id
    @Column(name = "product_delivery_id")
    private int id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Supplier supplier;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "delivery_qty")
    private int deliveryQuantity;

    @Column(name = "landing_price")
    private double landingProce;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public ProductSupplier(){
    }

    public ProductSupplier(Product product, Supplier supplier, Date deliveryDate, int deliveryQuantity, double landingProce, Timestamp createdAt) {
        this.product = product;
        this.supplier = supplier;
        this.deliveryDate = deliveryDate;
        this.deliveryQuantity = deliveryQuantity;
        this.landingProce = landingProce;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public double getLandingProce() {
        return landingProce;
    }

    public void setLandingProce(double landingProce) {
        this.landingProce = landingProce;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
