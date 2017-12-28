package com.netcracker.zagursky.entity.inventory;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Order {

    private int id;
    private String name;
    private String description;
    private double totalPrice;
    private int totalCount;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private String costumersEmail;
    private Date orderDate=new Date();
    private Date dateOfThePayment;
    private Status status;

    public Order() {
    }

    public Order(String name, String description, String costumersEmail) {
        this.name = name;
        this.description = description;
        this.costumersEmail = costumersEmail;
    }


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        totalPrice += orderItem.getPrice();
        totalCount++;
    }

    public boolean removeOrderItem(OrderItem orderItem) {

        if (orderItems.contains(orderItem)) {
            orderItems.remove(orderItem);
            totalCount--;
            totalPrice -= orderItem.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                totalCount == order.totalCount &&
                Objects.equals(name, order.name) &&
                Objects.equals(description, order.description) &&
                Objects.equals(orderItems, order.orderItems) &&
                Objects.equals(costumersEmail, order.costumersEmail) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(dateOfThePayment, order.dateOfThePayment) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, totalPrice, totalCount, orderItems, costumersEmail, orderDate, dateOfThePayment, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List getOrderItems() {
        return orderItems;
    }

    public String getCostumersEmail() {
        return costumersEmail;
    }

    public void setCostumersEmail(String costumersEmail) {
        this.costumersEmail = costumersEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDateOfThePayment() {
        return dateOfThePayment;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if(status==Status.PAID)
        {
            dateOfThePayment=new Date();
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalCount=" + totalCount +
                ", orderItems=" + orderItems +
                ", costumersEmail='" + costumersEmail + '\'' +
                ", orderDate=" + orderDate +
                ", dateOfThePayment=" + dateOfThePayment +
                ", status=" + status +
                '}';
    }
}
