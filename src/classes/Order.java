package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public String getItemName() {
        return itemName;
    }

    public int getOrderNo() {
        return orderNo;
    }
}
