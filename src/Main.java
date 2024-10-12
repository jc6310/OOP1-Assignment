import classes.*;

import java.security.KeyStore;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Setup Restaurant ");

        //Build Kids Menu
        ArrayList<MenuItem> kidMenuItem = new ArrayList<>();
        kidMenuItem.add(new MenuItem(1, "Burger", 5.99));
        kidMenuItem.add(new MenuItem(2, "Chips", 3.99));
        kidMenuItem.add(new MenuItem(3, "Sausage", 2.99));
        kidMenuItem.add(new MenuItem(4, "Chicken Nuggets", 2.99));

        KidsMenu kidsMenu = new KidsMenu(kidMenuItem);

        //Build Vegan Menu
        ArrayList<MenuItem> veganMenuItem = new ArrayList<>();
        veganMenuItem.add(new MenuItem(1, "Vegan Burger", 12.99));
        veganMenuItem.add(new MenuItem(2, "Chips With Taco", 8.99));
        veganMenuItem.add(new MenuItem(3, "Veg salad soup", 6.99));
        veganMenuItem.add(new MenuItem(4, "Potato Nuggets", 9.99));

        VeganMenu veganMenu = new VeganMenu(veganMenuItem);

        //Build Kids Menu
        ArrayList<MenuItem> standardMenuItem = new ArrayList<>();
        standardMenuItem.add(new MenuItem(1, "Burger And Chips", 15.99));
        standardMenuItem.add(new MenuItem(2, "Chicken And Chips", 13.99));
        standardMenuItem.add(new MenuItem(3, "Sausages And Mashed Potatoes", 16.99));
        standardMenuItem.add(new MenuItem(4, "Chicken Brest And Potatoes", 14.99));

        StandardMenu standardMenu = new StandardMenu(standardMenuItem);

        kidsMenu.displayMenu();
        veganMenu.displayMenu();
        standardMenu.displayMenu();

        Waiter waiter = new Waiter(14.04,"Raul", 101, true);
        Waiter waiter1 = new Waiter(11.04,"Johnny", 99, false);

        Manager manager = new Manager(601.04,"James", 21);

        System.out.println(waiter.displayEmployeeDetails());
        System.out.println(waiter1.displayEmployeeDetails());
        System.out.println(manager.displayEmployeeDetails());
    }
}