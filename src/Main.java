import classes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Setup Restaurant ");

        //Build Kids Menu
        var kidMenuItem = new ArrayList<MenuItem>();
        kidMenuItem =  buildMenu("kids");

        KidsMenu kidsMenu = new KidsMenu(kidMenuItem);
        kidsMenu.displayMenu();

        //Build Vegan Menu
        var veganMenuItem = new ArrayList<MenuItem>();
        veganMenuItem =  buildMenu("vegan");

        VeganMenu veganMenu = new VeganMenu(veganMenuItem);
        veganMenu.displayMenu();

        //Build standard Menu
        var standardMenuItem = new ArrayList<MenuItem>();
        standardMenuItem =  buildMenu("standard");

        StandardMenu standardMenu = new StandardMenu(standardMenuItem);
        standardMenu.displayMenu();

        //create waiters and staff
        Waiter waiter = new Waiter(14.04, "Raul", 101, true);
        Waiter waiter1 = new Waiter(11.04, "Johnny", 99, false);

        Manager manager = new Manager(501.04, "Bob", 15);
        Manager managerGeneral = new Manager(601.04, "James", 21, "General");

        System.out.println(waiter.displayEmployeeDetails());
        System.out.println(waiter1.displayEmployeeDetails());
        System.out.println(manager.displayEmployeeDetails());
        System.out.println(managerGeneral.displayGeneralManagerDetails());

        showEmployeeWorking(waiter, waiter1);  // Varargs
        showEmployeeWorking(manager);

    }

    private static ArrayList<MenuItem> buildMenu(String menuType) {
        //[01] example of LVTI
        var menu_items = new ArrayList<MenuItem>();

        switch (menuType) {
            case "kids":
                menu_items.add(new MenuItem(1, "Burger", 5.99));
                menu_items.add(new MenuItem(2, "Chips", 3.99));
                menu_items.add(new MenuItem(3, "Sausage", 2.99));
                menu_items.add(new MenuItem(4, "Chicken Nuggets", 2.99));
                break;
            case "vegan":
                menu_items.add(new MenuItem(1, "Vegan Burger", 12.99));
                menu_items.add(new MenuItem(2, "Chips With Taco", 8.99));
                menu_items.add(new MenuItem(3, "Veg salad soup", 6.99));
                menu_items.add(new MenuItem(4, "Potato Nuggets", 9.99));
                break;
            default:
                menu_items.add(new MenuItem(1, "Burger And Chips", 15.99));
                menu_items.add(new MenuItem(2, "Chicken And Chips", 13.99));
                menu_items.add(new MenuItem(3, "Sausages And Mashed Potatoes", 16.99));
                menu_items.add(new MenuItem(4, "Chicken Brest And Potatoes", 14.99));
        }
        return menu_items;
    }

    // [01] example of Varargs and method overload
    static void showEmployeeWorking(Waiter... employee)
    {
        System.out.println("Waiters Rostered tonight");

        for (Waiter i : employee)
            System.out.println(i.getName());
    }

    // [01] example of Varargs and method overload
    static void showEmployeeWorking(Manager... employee)
    {
        for (Manager i : employee)
            System.out.println("Duty manager "+i.getName());
    }
}