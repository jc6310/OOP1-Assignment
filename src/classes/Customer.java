package classes;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Customer {

    String orderStatus = "Preparing";
    final List<Order> order;
    String customerOrder;
    LocalDateTime orderTime;

    public Customer(List<Order> order, String customerOrder, LocalDateTime orderTime) {
        this.order = Collections.unmodifiableList(order);  // [A1] defensive copying
        this.customerOrder = customerOrder;
        this.orderTime = orderTime;
    }

    public void getOrder() {
        for (Order orderItem : order) System.out.println(orderItem.getOrderNo() +
                ":"+orderItem.getPrice()+" - "+ orderItem.getItemName());
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void updateOrderStatus(String orderStatusUpdate) {
        this.orderStatus = orderStatusUpdate;
    }

    public double getBillTotal() {
        double totalPrice = 0;
        for (Order itemPrice : this.order)
        {
            totalPrice += itemPrice.getPrice();
        }
        return totalPrice;
    }
}
