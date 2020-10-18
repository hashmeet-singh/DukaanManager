package com.rrdlabs.dukaanmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_description")
    private String description;

    public ProductCategory(){
    }

    public ProductCategory(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
