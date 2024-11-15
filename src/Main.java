import classes.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        // [05] Exceptions unchecked
        File file = new File("fileThatDoesNotExist.txt");
        try {
            FileInputStream stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        Scanner scanner = new Scanner(System.in);

        // [8] Example Of StringBuilder
        StringBuilder resTitle = new StringBuilder("Whatever Takeaway Restaurant");
        resTitle.insert(0, "Local ");
        System.out.println(resTitle);

        //create waiters and staff
        Waiter waiter = new Waiter(14.04, "Raul", 101, true);
        Waiter waiter1 = new Waiter(11.04, "Johnny", 99, false);

        Manager manager = new Manager(501.04, "Bob", 15);
        Manager managerGeneral = new Manager(601.04, "James", 21, "General");

        while(true) {
            System.out.println("Options Menu Available");
            System.out.println("1. Kids Menu");
            System.out.println("2. Vegan Menu");
            System.out.println("3. Main Menu");
            System.out.println("4. Special Menu");
            System.out.println("5. Show Staff Currently Working");
            System.out.println("6. Show Contractors Working");
            System.out.println("7. Show Deliveroo Driver Details");
            System.out.println("q. Leave");

            String sc = scanner.next();

            if(sc.equalsIgnoreCase("q")) break;
            int userInput;

            // [05] Exceptions checked
            try {
                userInput = Integer.parseInt(sc);
                System.out.println("Option Select: " +userInput);

                switch(userInput) {
                    case 1:
                        //Build Kids Menu
                        var kidMenuItem = new ArrayList<MenuItem>();
                        kidMenuItem =  buildMenu(String.valueOf(MenuType.KIDS));
                        KidsMenu kidsMenu = new KidsMenu(kidMenuItem);
                        kidsMenu.displayMenu();
                        break;
                    case 2:
                        //Build Vegan Menu
                        var veganMenuItem = new ArrayList<MenuItem>();
                        veganMenuItem =  buildMenu(String.valueOf(MenuType.VEGAN));
                        // [4] Polymorphism example
                        VeganMenu veganMenu = new VeganMenu(veganMenuItem);
                        veganMenu.displayMenu();
                        break;
                    case 3:
                        // Build standard Menu
                        var standardMenuItem = new ArrayList<MenuItem>();
                        standardMenuItem =  buildMenu(String.valueOf(MenuType.STANDARD));
                        StandardMenu standardMenu = new StandardMenu(standardMenuItem);
                        standardMenu.displayMenu();
                        break;
                    case 4:
                        // [A2] Private, default and static interface methods.
                        Menu specialMenu = Menu.createMenu();
                        specialMenu.isSpecialMenu("Special");
                        specialMenu.displayMenu();
                        break;
                    case 5:
                        System.out.println(waiter.displayEmployeeDetails());
                        System.out.println(waiter1.displayEmployeeDetails());
                        System.out.println(manager.displayEmployeeDetails());
                        System.out.println(managerGeneral.displayGeneralManagerDetails());
                        showEmployeeWorking(waiter, waiter1);  // Varargs
                        showEmployeeWorking(manager);
                        // [A5] Lambdas (Predicate).
                        Predicate<Waiter> waitersThatCanServeAlcohol =  i -> !i.isAllowedServiceAlcohol();
                        System.out.println("waiter: " + waiter.getName() +
                                " serve alcohol " + waitersThatCanServeAlcohol.test(waiter));
                        System.out.println("waiter: " + waiter1.getName() +
                                " serve alcohol " + waitersThatCanServeAlcohol.test(waiter1));
                        break;
                    case 6:
                        // [A3] Records example
                        Contractor contractorElectrician = new Contractor(502, "Bob Murphy",
                                "Electrician", 300, "Fix Lighting");
                        Contractor contractorPlumber = new Contractor(501, "Alan Brown",
                                "Plumber", 150, "Fix Sink");
                        System.out.println("Name: "+contractorElectrician.name() + "worked on "
                                + contractorElectrician.task());
                        System.out.println("Name: "+contractorPlumber.name() + "worked on "
                                + contractorPlumber.task());
                        break;
                    case 7:
                        // [A4] a custom immutable type
                        DeliverooDelivery deliverooDelivery = new DeliverooDelivery(
                                "Johnny Brown",
                                1,
                                false);
                        System.out.println("Driver Name" +deliverooDelivery.getDriverName() +
                                " with order" +deliverooDelivery.getOrderNo() +
                                " status if delivery is pickup " +deliverooDelivery.getIsPickedUp());
                        break;
                    default:
                        System.out.println("Invalid number provided please try again");
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid input, only numbers are accetped");
            } finally{
                System.out.println("Please retry again");
            }
        }

        greetingsMessage("greeting",waiter.getName());
        Order orderOne = new Order("Chicken And Chips", 52, 13.99);
        Order orderTwo = new Order("Chips With Taco", 32, 8.99);

        // [7] example of arraylist
        var orderItems = new ArrayList<Order>();
        orderItems.add(orderOne);
        orderItems.add(orderTwo);

        // [8] Example Of date api
        LocalDateTime purchaseTime = LocalDateTime.now();

        Customer CustomerOne = new Customer(orderItems, "DCX5682117", purchaseTime);
        greetingsMessage("cost", String.valueOf(CustomerOne.getBillTotal()));
        CustomerOne.getBillTotal();
        System.out.println("Order Status:" +CustomerOne.getOrderStatus());
        System.out.println("Please pay, cost of order " +CustomerOne.getBillTotal());
        // [A1] Call-by-value
        CustomerOne.updateOrderStatus("Order Ready");
        System.out.println("Order: " + CustomerOne.getCustomerOrder() +" Status: "+ CustomerOne.getOrderStatus());
        CustomerOne.getOrder();
        greetingsMessage("bye", "");
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
    // [A6] Switch expressions with pattern matching.
    private static void greetingsMessage(String type, String message){
        switch (type) {
            case "greeting" -> System.out.println("Hi my name is " + message +" What u want to order?");
            case "cost" -> System.out.println("ur order will cost" + message);
            case "bye" -> System.out.println("Bye please come again");
            default -> System.out.println("Can u say that again");
        }
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

// [A4] a custom immutable type
final class  DeliverooDelivery {

    private final String driverName;
    private final int orderNo;
    private final Boolean isPickedUp;

    public DeliverooDelivery(String driverName, int orderNo,
                             Boolean isPickedUp)
    {
        this.driverName = driverName;
        this.orderNo = orderNo;
        this.isPickedUp = isPickedUp;
    }

    public String getDriverName() { return driverName; }
    public int getOrderNo() { return orderNo; }
    public Boolean getIsPickedUp() { return isPickedUp; }
}