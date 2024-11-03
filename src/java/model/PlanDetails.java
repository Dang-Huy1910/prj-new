/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class PlanDetails {
    private int pdid;
    private Plan plan;
    private Shift shift;
    private java.sql.Date date;
    private Product product;
    private int quantity;

    public PlanDetails() {
    }
    
    
    public PlanDetails(int pdid, Plan plan, Shift shift, Date date, Product product, int quantity) {
        this.pdid = pdid;
        this.plan = plan;
        this.shift = shift;
        this.date = date;
        this.product = product;
        this.quantity = quantity;
    }

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
