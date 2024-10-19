package classes;

public class Waiter extends Employee{

    boolean isAllowedServiceAlcohol;

    public Waiter(double pay, String name, int id, boolean isAllowedServiceAlcohol) {
        super(pay, name, id);
        this.isAllowedServiceAlcohol = isAllowedServiceAlcohol;
    }

    public boolean isAllowedServiceAlcohol() {
        return isAllowedServiceAlcohol;
    }

    public String displayAllEmployeeDetails(){
        return getId() +" : " +getName() + " who's pay rate is "+ getWage();
    }
}
