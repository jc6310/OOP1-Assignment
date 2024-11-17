package classes;

// [02] Encapsulation example where data is hidden that can be used via get/setters
public class Manager extends Employee{

    private String title;

    public Manager(double pay, String name, Integer id) {
        // [04] Super() refers to the the parent class constructor
        super(pay, name, id);
    }

    public Manager(double pay, String name, Integer id, String title ) {
        // [01] Example of this() and this different
        this(pay, name, id); //[01] is used to another class constructor in this case the employee
        this.title = title;  //[01] can be used to refer current class instance variable
    }

    public String getTitle() {
        return title;
    }

    public String displayAllEmployeeDetails(){
        return getId() +" : " +getName() + " who's pay rate is "+ getWage();
    }

    public String displayGeneralManagerDetails() {
        // [04] super refers to parent class objects
        return title +" Manager: "+ super.displayEmployeeDetails();
    }
}
