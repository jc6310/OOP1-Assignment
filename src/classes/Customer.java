package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Customer  {

    String orderStatus = "Preparing";
    final List<Order> order;
    String customerOrder;
    LocalDateTime orderTime;
    int rewardsTotal;
    int discount;

    public Customer(List<Order> order, String customerOrder, LocalDateTime orderTime) {
        this.order = Collections.unmodifiableList(order);  // [A1] defensive copying
        this.customerOrder = customerOrder;
        this.orderTime = orderTime;
        this.rewardsTotal = 0;
        this.discount = 0;
    }

    public void getOrder() {
        for (Order orderItem : order) System.out.println(orderItem.getOrderNo() +
                ":"+orderItem.getPrice()+" - "+ orderItem.getItemName());
    }

    public void getOrderforRecipt(StringBuilder sb) {
        for (Order orderItem : order)  sb.append(orderItem.getItemName() +" €" +orderItem.getPrice()+"\n");
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

    public int getRewardsTotal() {
        return rewardsTotal;
    }

    public void setRewardsTotal(int rewardsTotal) {
        this.rewardsTotal = rewardsTotal;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getBillTotal() {
        double totalPrice = 0;
        for (Order itemPrice : this.order)
        {
            totalPrice += itemPrice.getPrice();
        }
        return totalPrice;
    }

    // [A3_OOP2] NIO2 Write receipt to file
    public void printReceipt(String filePath){
        String receipt = generateReceipt();
        Path receiptPath = Paths.get("receipts", filePath);
        try {
            Files.createDirectories(receiptPath.getParent());
            Files.writeString(receiptPath, receipt);
            System.out.println("Receipt saved to: " + receiptPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // [A3_OOP2] NIO2 Read and print the receipt
    public void readReceipt(String filePath) throws IOException {

        Path receiptPath = Paths.get("receipts", filePath);
        if (Files.exists(receiptPath)) { // Ensure file exists before reading
            List<String> lines = Files.readAllLines(receiptPath.toAbsolutePath());
            System.out.println("\n--- Receipt ---");
            lines.forEach(System.out::println);
        } else {
            System.out.println("Error: Receipt file not found!");
        }
    }

    private String generateReceipt() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(dtf);

        sb.append("========================\n");
        sb.append("      RECEIPT      \n");
        sb.append("========================\n");
        sb.append("Date: ").append(dateTime).append("\n");
        sb.append("------------------------\n");
        sb.append("Item              Price\n");
        sb.append("------------------------\n");
        this.getOrderforRecipt(sb);
        sb.append("------------------------\n");
        sb.append("Discount Applied"+this.getBillTotal()+"%\n");
        sb.append("Rewards "+this.getRewardsTotal()+"%\n");
        sb.append("------------------------\n");
        sb.append("TOTAL: €"+this.getBillTotal()+"\n");
        sb.append("========================\n");
        sb.append("Thank you for shopping!\n");
        sb.append("========================\n");

        return sb.toString();
    }
}
