import classes.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        // [05] Exceptions checked
        File file = new File("fileThatDoesNotExist.txt");
        try {
            FileInputStream stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        Scanner scanner = new Scanner(System.in);

        // [08] Example Of StringBuilder
        StringBuilder resTitle = new StringBuilder("Whatever Takeaway Restaurant");
        resTitle.insert(0, "Local ");
        System.out.println(resTitle);

        //create waiters and staff
        Waiter waiter = new Waiter(14.04, "Raul", 101, true);
        Waiter waiter1 = new Waiter(11.04, "Johnny", 99, false);

        Manager manager = new Manager(501.04, "Bob", 25, "Assistant Mgt");
        Manager managerInTraining = new Manager(201.04, "Mary", 41, "Training");
        Manager managerGeneral = new Manager(601.04, "James", 11, "General");

        while(true) {
            System.out.println("Options Menu Available");
            System.out.println("1. Kids Menu");
            System.out.println("2. Vegan Menu");
            System.out.println("3. Main Menu");
            System.out.println("4. Special Menu");
            System.out.println("5. Show Staff Currently Working");
            System.out.println("6. Show Contractors Working");
            System.out.println("7. Show Deliveroo Driver Details");
            System.out.println("8. Show Managers By Rank Working");
            System.out.println("q. Leave");

            String sc = scanner.next();

            if(sc.equalsIgnoreCase("q")) break;
            int userInput;

            // [05] Exceptions unchecked
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
                        System.out.println("Shows Waiter details");
                        System.out.println(waiter.displayEmployeeDetails());
                        System.out.println(waiter1.displayEmployeeDetails());

                        System.out.println("Shows Manager details");
                        System.out.println(manager.displayEmployeeDetails()); // [01] Method overloading
                        System.out.println(manager.displayEmployeeDetails(true)); // [01] Method overloading

                        System.out.println(managerGeneral.displayGeneralManagerDetails());
                        showEmployeeWorking(waiter, waiter1);  // [01] Varargs
                        showEmployeeWorking(manager);

                        // [A5] Lambdas (Predicate).
                        Predicate<Waiter> waitersThatCanServeAlcohol =  i -> !i.isAllowedServiceAlcohol();
                        System.out.println("waiter: " + waiter.getName() +
                                " serve alcohol " + waitersThatCanServeAlcohol.test(waiter));
                        System.out.println("waiter: " + waiter1.getName() +
                                " serve alcohol " + waitersThatCanServeAlcohol.test(waiter1));
                        break;
                    case 6:
                        // [A3] [07_OOP2] Records example
                        Contractor contractorElectrician = new Contractor(502, "Bob Murphy",
                                "Electrician", 300, "Fix Lighting");
                        Contractor contractorPlumber = new Contractor(501, "Alan Brown",
                                "Plumber", 150, "Fix Sink");
                        System.out.println("Name: "+contractorElectrician.name() + " worked on "
                                + contractorElectrician.task());
                        System.out.println("Name: "+contractorPlumber.name() + " worked on "
                                + contractorPlumber.task());
                        break;
                    case 7:
                        // [A4] a custom immutable type
                        DeliverooDelivery deliverooDelivery = new DeliverooDelivery(
                                "Johnny Brown",
                                1,
                                false);
                        System.out.println("Driver Name " +deliverooDelivery.getDriverName() +
                                " with order" +deliverooDelivery.getOrderNo() +
                                " status if delivery is pickup " +deliverooDelivery.getIsPickedUp());
                        break;
                    case 8:
                        // [A5] Lambdas (Predicate) - method references
                        List<Manager> mgtList = new ArrayList<>();
                        mgtList.add(manager);
                        mgtList.add(managerGeneral);
                        mgtList.add(managerInTraining);

                        Collections.sort(mgtList, Main::compareEmployeeIds);

                        mgtList.stream()
                                .map(mgt -> mgt.getTitle()+ " - "+mgt.getName())
                                .forEach(System.out::println);
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

        // [07] example of arraylist
        var orderItems = new ArrayList<Order>();
        orderItems.add(orderOne);
        orderItems.add(orderTwo);

        // [08] Example Of date api
        LocalDateTime purchaseTime = LocalDateTime.now();

        Customer CustomerOne = new Customer(orderItems, "DCX5682117", purchaseTime);
        greetingsMessage("cost", String.valueOf(CustomerOne.getBillTotal()));
        CustomerOne.getBillTotal();
        System.out.println("Order Status:" +CustomerOne.getOrderStatus());
        System.out.println("Please pay, cost of order " +CustomerOne.getBillTotal());

        // [06_OOP2] Date/Time API. That using  [04_OOP2] Switch expressions and pattern matching.
        LocalDate today = LocalDate.now();

        Offers thursdayOffer = new Offers("Thursday Specical 20% off", 1001,20, setOffersAvailable(String.valueOf(today.getDayOfWeek())));
        Offers satMatchDayOffer = new Offers("Match Day Special Buy 1 Get 1 Free", 1002,50, setOffersAvailable(String.valueOf(today.getDayOfWeek())));
        Offers loyalOffer = new Offers("Regular Customer Discount", 1003,10, true);

        RewardsCard rewardsCustomer1 = new RewardsCard("Bloggs", "Joe");
        rewardsCustomer1.addOffer(thursdayOffer);
        rewardsCustomer1.addOffer(satMatchDayOffer);
        rewardsCustomer1.addOffer(loyalOffer);

        // [O1_OOP2] Predicate example: Check if discount is above 5% and avaiavle
        Predicate<Offers> discountAbove18AndAvailable = offer -> thursdayOffer.getDiscount() > 5 && thursdayOffer.getIsAvailable();
        boolean isOfferAvailable = thursdayOffer.isDiscountAboveThreshold(discountAbove18AndAvailable);

        double currentPrice = CustomerOne.getBillTotal();

        if (isOfferAvailable) {
            // [O1_OOP2]  Supplier: Get discount message
            String discountMessage = loyalOffer.getDiscountMessage(() -> "Get " + thursdayOffer.getDiscount() + "% off on your next purchase.");
            System.out.println(discountMessage);

            // [O1_OOP2] Consumer: Apply discount
            thursdayOffer.applyDiscount(discount -> {
                System.out.println("Applying a discount of " + discount + "% on the product.");
            });
            // [O1_OOP2] Function: applying discount
            double updatedPrice = thursdayOffer.getFinalPrice(currentPrice, price -> price * (1 - thursdayOffer.getDiscount() / 100));

            currentPrice = updatedPrice;
        }
        System.out.println("Final price after discount: $" + currentPrice);

        // [A1] Call-by-value - changing the OrderStatus valave
        CustomerOne.updateOrderStatus("Order Ready");
        System.out.println("Order: " + CustomerOne.getCustomerOrder() +" Status: "+ CustomerOne.getOrderStatus());
        CustomerOne.getOrder();
        greetingsMessage("bye", "");

        System.out.println("Final price after discount: $" + currentPrice);

    }

    public static int compareEmployeeIds(Manager a, Manager b)
    {
        return a.getId().compareTo(b.getId());
    }

    private static ArrayList<MenuItem> buildMenu(String menuType) {
        //[01] example of LVTI
        var menu_items = new ArrayList<MenuItem>(); //[08] arraylist

        //[07] java array
        String[] titles = {"Burger", "Chips", "Sausage", "Chicken"};

        //[08] java code string
        String foodItem = "Chicken Brest And Potatoes";

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
                menu_items.add(new MenuItem(54, foodItem, 14.99));
        }
        return menu_items;
    }
    //[04_OOP2] Switch expressions with pattern matching.
    private static boolean setOffersAvailable(String weekday){

        System.out.println("week Day is -> "+weekday);

        switch (weekday) {
            case "THURSDAY", "SATURDAY" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    // [A6] Switch expressions and pattern matching.
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