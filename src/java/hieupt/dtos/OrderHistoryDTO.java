
package hieupt.dtos;

import java.sql.Timestamp;

public class OrderHistoryDTO {
    private int orderID;
    private String customerID;
    private double total;
    private Timestamp orderDate;
    private String status;

    public OrderHistoryDTO(int orderID, String customerID, double total, Timestamp orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
