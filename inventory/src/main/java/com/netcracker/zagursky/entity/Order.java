package com.netcracker.zagursky.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orderP")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int description;
    private double totalPrice;
    private int totalCount;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orderp_orderitem", joinColumns = {
            @JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "orderitem_id", nullable = false, updatable = false)

            })
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private String costumersEmail;
    private Date orderDate;
    private Date dateOfThePayment;
    private boolean signOfThePayment;

    public Order() {
    }

    public Order(String name, int description, String costumersEmail) {
        this.name = name;
        this.description = description;
        this.costumersEmail = costumersEmail;
        orderDate = new Date();
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return description == order.description &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                totalCount == order.totalCount &&
                signOfThePayment == order.signOfThePayment &&
                Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(orderItems, order.orderItems) &&
                Objects.equals(costumersEmail, order.costumersEmail) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(dateOfThePayment, order.dateOfThePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, totalPrice, totalCount, orderItems, costumersEmail, orderDate, dateOfThePayment, signOfThePayment);
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
            dateOfThePayment = new Date();
        } else {
            dateOfThePayment = null;
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
