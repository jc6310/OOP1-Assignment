package classes;

public class Bill {

    double charges;
    double tip;

    public double getTotal() {
        return charges + tip;
    }
}
