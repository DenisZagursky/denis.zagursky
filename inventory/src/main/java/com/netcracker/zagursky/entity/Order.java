package com.netcracker.zagursky.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int name;
    private int description;
    private double totalPrice;
    private int totalCount;
    private List orderItems = new ArrayList<OrderItem>();
    private String costumersEmail;
    private Date orderDate;
    private Date dateOfThePayment;
    private boolean signOfThePayment;

    public Order() {
    }

    public Order(int id, int name, int description, String costumersEmail, List<OrderItem> orderItems) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.orderItems = orderItems;
        this.costumersEmail = costumersEmail;
        this.totalCount = orderItems.size();
        for (int i = 0; i < totalCount; i++) {
            totalPrice += orderItems.get(i).getPrice();
        }
        orderDate.setTime(System.currentTimeMillis());
        signOfThePayment = false;
    }


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        totalPrice += orderItem.getPrice();
        totalCount++;
    }

    public boolean removeOrderItem(OrderItem orderItem) {
        Boolean result = orderItems.remove(orderItem);
        if (result) {
            totalCount--;
            totalPrice -= orderItem.getPrice();
        }
        return result;
    }

    public void changeStatus() {
        orderDate.setTime(System.currentTimeMillis());
        signOfThePayment = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (name != order.name) return false;
        if (description != order.description) return false;
        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        if (totalCount != order.totalCount) return false;
        if (signOfThePayment != order.signOfThePayment) return false;
        if (orderItems != null ? !orderItems.equals(order.orderItems) : order.orderItems != null) return false;
        if (costumersEmail != null ? !costumersEmail.equals(order.costumersEmail) : order.costumersEmail != null)
            return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        return dateOfThePayment != null ? dateOfThePayment.equals(order.dateOfThePayment) : order.dateOfThePayment == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name;
        result = 31 * result + description;
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalCount;
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);
        result = 31 * result + (costumersEmail != null ? costumersEmail.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (dateOfThePayment != null ? dateOfThePayment.hashCode() : 0);
        result = 31 * result + (signOfThePayment ? 1 : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
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

    public Boolean getSignOfThePayment() {
        return signOfThePayment;
    }

    public void setSignOfThePayment(boolean signOfThePayment) {
        if (signOfThePayment) {
            orderDate.setTime(System.currentTimeMillis());
        }
        this.signOfThePayment = signOfThePayment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", totalPrice=" + totalPrice +
                ", totalCount=" + totalCount +
                ", orderItems=" + orderItems +
                ", costumersEmail='" + costumersEmail + '\'' +
                ", orderDate=" + orderDate +
                ", dateOfThePayment=" + dateOfThePayment +
                ", signOfThePayment=" + signOfThePayment +
                '}';
    }
}
