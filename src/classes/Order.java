package classes;

import java.util.ArrayList;

public class Order {

    int orderNo;
    private final ArrayList<Bill> bill;

    public Order(ArrayList<Bill> bill) {
        this.bill = bill;
    }

    public double getbill(){
        return bill.getFirst().getTotal();
    }

}
