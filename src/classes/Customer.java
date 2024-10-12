package classes;

import java.util.ArrayList;

public class Customer {

    private final ArrayList<Order> order;
    String customerOrder;

    public Customer(ArrayList<Order> order, String customerOrder) {
        this.order = order;
        this.customerOrder =  customerOrder;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public double getBill() {
        return order.getFirst().getbill();
    }
}
