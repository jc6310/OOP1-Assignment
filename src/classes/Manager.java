package classes;

public class Manager extends Employee{

    private String title;

    public Manager(double pay, String name, int id) {
        super(pay, name, id);
    }

    public Manager(double pay, String name, int id, String title ) {
        //[01] Example of this() and this different
        this(pay, name, id); //[01] can be used to current class constructor to reuse the constructor
        this.title = title;  //[01] can be used to refer current class instance variable
    }

    public String isTitle() {
        return title;
    }

    public String displayGeneralManagerDetails() {
        return title +" Manager: "+ super.displayEmployeeDetails();
    }

}
