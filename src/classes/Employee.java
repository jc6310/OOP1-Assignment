package classes;

abstract class Employee {

    private final Integer id;
    private final String name;
    private final double wage;

    public Employee(double wage, String name, Integer id) {
        this.wage = wage;
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWage() {
        return wage;
    }

    // [01] method overloading
    public String displayEmployeeDetails() {
       return getName()+ "("+getId()+")" ;
    }

    public String displayEmployeeDetails(Boolean showPay) {
        return getName()+ "("+getId()+") hourly rate "+ getWage();
    }

    public double totalEmployeeWage(double time) {
        return time * getWage();
    }

    public abstract String displayAllEmployeeDetails();
}
