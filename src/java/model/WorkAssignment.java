/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class WorkAssignment {
    private int waid;
    private PlanDetails details;
    private Employee employee;
    private int quantity;

    public WorkAssignment(int waid, PlanDetails details, Employee employee, int quantity) {
        this.waid = waid;
        this.details = details;
        this.employee = employee;
        this.quantity = quantity;
    }
    
    public int getWaid() {
        return waid;
    }

    public void setWaid(int waid) {
        this.waid = waid;
    }

    public PlanDetails getDetails() {
        return details;
    }

    public void setDetails(PlanDetails details) {
        this.details = details;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
