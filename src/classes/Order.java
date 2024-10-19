package classes;

import java.util.ArrayList;

public class Order {

    int orderNo;
    double price;
    String itemName;

    public Order(String itemName, int orderNo, double price) {
        this.orderNo = orderNo;
        this.itemName = itemName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderNo() {
        return orderNo;
    }
}
