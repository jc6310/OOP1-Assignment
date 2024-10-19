package classes;

abstract class Employee {

    private final int id;
    private final String name;
    private final double wage;

    public Employee(double wage, String name, int id) {
        this.wage = wage;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWage() {
        return wage;
    }

    public String displayEmployeeDetails() {
       return getName()+ "("+getId()+")" ;
    }

    public String displayEmployeeDetailsWithPay() {
        return getName()+ "("+getId()+") hourly rate "+ getWage();
    }

    public double totalEmployeeWage(double time) {
        return time * getWage();
    }

    public abstract String displayAllEmployeeDetails();
}
