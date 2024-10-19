package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer {

    private final ArrayList<Order> order;
    String customerOrder;
    LocalDateTime orderTime;

    public Customer(ArrayList<Order> order, String customerOrder, LocalDateTime orderTime) {
        this.order = order;
        this.customerOrder = customerOrder;
        this.orderTime = orderTime;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public double getBill() {
        double totalPrice = 0;
        for (Order itemPrice : this.order)
        {
            totalPrice += itemPrice.getPrice();
        }
        return totalPrice;
    }
}
