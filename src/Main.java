import classes.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // [8] Example Of StringBuilder
        StringBuilder resTitle = new StringBuilder("Whatever Takeaway Restaurant");
        resTitle.insert(0, "Local ");
        System.out.println(resTitle);

        //Build Kids Menu
        var kidMenuItem = new ArrayList<MenuItem>();
        kidMenuItem =  buildMenu(String.valueOf(MenuType.KIDS));
        KidsMenu kidsMenu = new KidsMenu(kidMenuItem);
        kidsMenu.displayMenu();

        //Build Vegan Menu
        var veganMenuItem = new ArrayList<MenuItem>();
        veganMenuItem =  buildMenu(String.valueOf(MenuType.VEGAN));
        // [4] Polymorphism example
        VeganMenu veganMenu = new VeganMenu(veganMenuItem);
        veganMenu.displayMenu();

        //Build standard Menu
        var standardMenuItem = new ArrayList<MenuItem>();
        standardMenuItem =  buildMenu(String.valueOf(MenuType.STANDARD));

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

        Order orderOne = new Order("Chicken And Chips", 52, 13.99);
        Order orderTwo = new Order("Chips With Taco", 32, 8.99);

        // [7] example of arraylist
        var orderItems = new ArrayList<Order>();
        orderItems.add(orderOne);
        orderItems.add(orderTwo);

        // [8] Example Of date api
        LocalDateTime purchaseTime = LocalDateTime.now();

        Customer CustomerOne = new Customer(orderItems, "DCX5682117", purchaseTime);
        CustomerOne.getBillTotal();
        System.out.println("Order Status:" +CustomerOne.getOrderStatus());
        System.out.println("Please pay, cost of order " +CustomerOne.getBillTotal());
        // [A1] Call-by-value
        CustomerOne.updateOrderStatus("Order Ready");
        System.out.println("Order: " + CustomerOne.getCustomerOrder() +" Status: "+ CustomerOne.getOrderStatus());
        CustomerOne.getOrder();
    }

    private static ArrayList<MenuItem> buildMenu(String menuType) {
        //[01] example of LVTI
        var menu_items = new ArrayList<MenuItem>();
        String[] titles = new String[4];
        titles[0] = "Burger";
        titles[1] = "Chips";
        titles[2] = "Sausage";
        titles[3] = "Chicken";

        switch (menuType) {
            case "KIDS":
                menu_items.add(new MenuItem(1, titles[0], 5.99));
                menu_items.add(new MenuItem(2, titles[1], 3.99));
                menu_items.add(new MenuItem(3,  titles[2], 2.99));
                menu_items.add(new MenuItem(4, titles[3] +" Nuggets", 2.99));
                break;
            case "VEGAN":
                menu_items.add(new MenuItem(31, "Vegan "+ titles[0], 12.99));
                menu_items.add(new MenuItem(32, titles[1] +" With Taco", 8.99));
                menu_items.add(new MenuItem(33, "Veg salad soup", 6.99));
                menu_items.add(new MenuItem(34, "Potato Nuggets", 9.99));
                break;
            default:

                menu_items.add(new MenuItem(51, titles[1] +" And "+ titles[0], 15.99));
                menu_items.add(new MenuItem(52, titles[3] +" And "+ titles[1], 13.99));
                menu_items.add(new MenuItem(53, titles[2] +" And Mashed Potatoes", 16.99));
                menu_items.add(new MenuItem(54, "Chicken Brest And Potatoes", 14.99));
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