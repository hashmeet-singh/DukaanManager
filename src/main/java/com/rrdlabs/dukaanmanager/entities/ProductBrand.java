package com.rrdlabs.dukaanmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class ProductBrand {

    @Id
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name")
    private String brandName;

    public ProductBrand() {
    }

    public ProductBrand(String brandName) {
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
