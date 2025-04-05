import classes.*;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    private static final DecimalFormat roundingPrice = new DecimalFormat("0.00");
    public static void main(String[] args) throws IOException {

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

        // [06_OOP2] Date/Time API. That using [04_OOP2] Switch expressions and pattern matching.
        LocalDate today = LocalDate.now();

        Offers thursdayOffer = new Offers("Thursday Specical 20% off", 1001,20,
                setOffersAvailable(String.valueOf(today.getDayOfWeek())));
        Offers satMatchDayOffer = new Offers("Match Day Special Buy 1 Get 1 Free", 1002,50,
                setOffersAvailable(String.valueOf(today.getDayOfWeek())));
        Offers loyalOffer = new Offers("Regular Customer Discount", 1003,10, true);

        RewardsCard rewardsCustomer1 = new RewardsCard("Murphy", "Brian", 211);
        rewardsCustomer1.addOffer(thursdayOffer);
        rewardsCustomer1.addOffer(satMatchDayOffer);
        rewardsCustomer1.addOffer(loyalOffer);

        RewardsCard rewardsCustomer2 = new RewardsCard("Bloggs", "Joe", 558);
        rewardsCustomer2.addOffer(thursdayOffer);
        rewardsCustomer2.addOffer(satMatchDayOffer);
        rewardsCustomer2.addOffer(loyalOffer);

        RewardsCard rewardsCustomer3 = new RewardsCard("Costello", "Anne", 11);
        rewardsCustomer3.addOffer(thursdayOffer);
        rewardsCustomer3.addOffer(satMatchDayOffer);
        rewardsCustomer3.addOffer(loyalOffer);

        System.out.println();
        System.out.println("-------------- Date/Time API. Example ------------------");
        System.out.println("Customer3 RegisteredDate -> "+ rewardsCustomer3.getRegisteredDate());
        System.out.println("Customer3 getRegisteredTimeDate ->"+ rewardsCustomer3.getRegisteredTimeDate());
        System.out.println("-------------- Date/Time API. Example ------------------");

        System.out.println();

        System.out.println("-------------- Lambdas Example ------------------");
        System.out.println("-------------- Lambdas Predicate ----------------");

        double currentPrice = CustomerOne.getBillTotal();
        System.out.println("Current Price -> "+currentPrice);

        // [O1_OOP2] Predicate: Check if discount is above 5% and available
        Predicate<Offers> discountAbove5AndAvailable = offer -> loyalOffer.getDiscount() > 5 && loyalOffer.getIsAvailable();
        boolean isOfferAvailable = loyalOffer.isDiscountAboveThreshold(discountAbove5AndAvailable);
        System.out.println("Predicate condition -> "+ isOfferAvailable);

        if (isOfferAvailable) {
            // [O1_OOP2]  Supplier: Get discount message
            System.out.println("-------------- Lambdas Supplier ----------------");
            String discountMessage = loyalOffer.getDiscountMessage(() -> "Get " + loyalOffer.getDiscount() + "% off on your next purchase.");
            System.out.println(discountMessage);

            // [O1_OOP2] Consumer: Apply discount
            System.out.println("-------------- Lambdas Consumer ----------------");
            loyalOffer.applyDiscount(discount -> {
                System.out.println("Applying a discount of " + loyalOffer.getDiscount() + "% on the product.");
            });

            // [O1_OOP2] Function: applying discount
            System.out.println("-------------- Lambdas Function ----------------");
            double updatedPrice = loyalOffer.getFinalPrice(currentPrice, price -> price * (1 - loyalOffer.getDiscount() / 100));
            CustomerOne.setDiscount(10);
            currentPrice = updatedPrice;
        }
        System.out.println("Final price after discount: $" + roundingPrice.format(currentPrice));
        System.out.println("-------------- End Of Lambdas Example ------------------");
        System.out.println();

        // [A1] Call-by-value - changing the OrderStatus valave
        CustomerOne.updateOrderStatus("Order Ready");
        System.out.println("Order: " + CustomerOne.getCustomerOrder() +" Status: "+ CustomerOne.getOrderStatus());
        CustomerOne.getOrder();
        greetingsMessage("bye", "");

        System.out.println("Final price after discount: $" + currentPrice);

        System.out.println("-----------------------" );
        System.out.println("Add Rewards Points Summary" );

        List<RewardsCard> rewardsCards = Arrays.asList(rewardsCustomer3, rewardsCustomer2, rewardsCustomer1);

        System.out.println();
        System.out.println("-------------- Streams Example - Terminal Operations ------------------");

        // [02_OOP2] Streams terminal operations - count()
        getNumberOfRewardCards(rewardsCards);
        // [02_OOP2] Streams  terminal operations- min() max()
        getLowestAndHighestPoints(rewardsCards);
        // [02_OOP2] Streams terminal operations- forEach()
        printAllForEachCard(rewardsCards);
        // [02_OOP2] Streams terminal operations- allMatch(), anyMatch(), noneMatch()
        checkCardsIfBalancesMatches(rewardsCards, 101);
        // [02_OOP2] Streams terminal operations- findAny(),findFirst()
        findAnyName(rewardsCards);
        // [02_OOP2] Streams terminal operations- collect() - Collectors.toMap(), Collectors.groupingBy() and Collectors.partitioningBy()
        collectorsGroupBy(rewardsCards, 222);

        System.out.println("-------------- End Of Streams Example - Terminal Operations ------------------");
        System.out.println();

        System.out.println();
        System.out.println("-------------- Streams Example - Intermediate Operations ------------------");
        // [03_OOP2] Streams - Intermediate Operations
        streamTerminalOperations(rewardsCards, 0,100,2);
        streamTerminalOperations(rewardsCards, 1,100,2);
        streamTerminalOperations(rewardsCards, 2,100,2);
        streamTerminalOperations(rewardsCards, 3,100,2);
        streamTerminalOperations(rewardsCards, 4,100,2);
        streamTerminalOperations(rewardsCards, 5,100,2);

        System.out.println("-------------- End Of Streams Example - Terminal Operations ------------------");
        System.out.println();

        // [A3_OOP2] NIO2
        String filePath = LocalDateTime.now()+"-receipt.txt";
        CustomerOne.setDiscount(rewardsCustomer1.getRewardPoints());
        CustomerOne.printReceipt(filePath);
        CustomerOne.readReceipt(filePath);

        // [A2_OOP2] Concurrency e.g. using ExecutorService to process a list of Callables.
        processContractorConcurrently();
        //  [A1_OOP2] Collections/generics - for example: use of Comparator.comparing() for sorting.
        sortManagerSortByWage();

        //  [A4_OOP2] Localisation.
        System.out.println();
        System.out.println("-------------- Localisation Example ------------------");
        System.out.println("-------------- French ------------------");
        Locale frenchLocale = new Locale("fr", "FR");
        showMessagesByLanguage(frenchLocale);
        System.out.println("-------------- English ------------------");
        Locale englishLocale = new Locale("en", "EN");
        showMessagesByLanguage(englishLocale);
        System.out.println("-------------- Localisation Example End ------------------");
        System.out.println();
    }
    // [03_OOP2] Intermediate Stream Operations
    private static void streamTerminalOperations(List<RewardsCard> rewardsCards, int option, int amount, int limit) {

        switch (option) {
            case 0:
                // filter() - Filter RewardsCards that have reward points greater than
                List<RewardsCard> filteredCards = rewardsCards.stream()
                        .filter(card -> card.getRewardPoints() > amount)
                        .toList();
                System.out.println("Filtered Cards (Reward Points > "+amount+"):");
                filteredCards.forEach(card -> System.out.println(card.getName() + " " + card.getRewardPoints()));
                break;
            case 1:
                // distinct() - Get distinct offers across all RewardsCards (based on Offer name)
                List<Offers> distinctOffers = rewardsCards.stream()
                        .flatMap(card -> card.getOffers().stream()) // Flatten the list of offers
                        .distinct()
                        .toList();
                System.out.println("-------- ----------- ---------- ---");
                System.out.println("Distinct Offers:");
                distinctOffers.forEach(offer -> System.out.println(offer.getOfferName()));
                break;
            case 2:
                // limit() - Limit the stream
                List<RewardsCard> limitedCards = rewardsCards.stream()
                        .limit(limit)
                        .toList();
                System.out.println("-------- ----------- ---------- ---");
                System.out.println("Limited Cards (First "+limit+" Cards):");
                limitedCards.forEach(card -> System.out.println(card.getName() + " " + card.getRewardPoints()));
                break;
            case 3:
                // map() - Transform the stream by extracting just the reward points of each RewardsCard
                List<Integer> rewardPointsList = rewardsCards.stream()
                        .map(RewardsCard::getRewardPoints) // Map each RewardsCard to its reward points
                        .toList();
                System.out.println("-------- ----------- ---------- ---");
                System.out.println("Reward Points of All Cards: " + rewardPointsList);
                break;
            case 4:
                // sorted() - Sort RewardsCards by their reward points (ascending order)
                List<RewardsCard> sortedCards = rewardsCards.stream()
                        .sorted(Comparator.comparingInt(RewardsCard::getRewardPoints)) // Sorting by reward points
                        .toList();
                System.out.println("-------- ----------- ---------- ---");
                System.out.println("Sorted Cards by Reward Points (Ascending):");
                sortedCards.forEach(card -> System.out.println(card.getName() + " " + card.getRewardPoints()));
                break;
            default:
                // sorted() in descending order
                List<RewardsCard> sortedCardsDescending = rewardsCards.stream()
                        .sorted((card1, card2) -> Integer.compare(card2.getRewardPoints(), card1.getRewardPoints())) // Sorting by reward points descending
                        .toList();
                System.out.println("-------- ----------- ---------- ---");
                System.out.println("Sorted Cards by Reward Points (Descending):");
                sortedCardsDescending.forEach(card -> System.out.println(card.getName() + " " + card.getRewardPoints()));
        }

    }

    private static void collectorsGroupBy(List<RewardsCard> rewardsCards, int amount) {
        // Collect - Collect RewardsCards into a Map by their reward points
        Map<Integer, RewardsCard> rewardsCardMap = rewardsCards.stream()
                .collect(Collectors.toMap(RewardsCard::getRewardPoints, card -> card));
        System.out.println("Rewards Cards Map by Reward Points: " + rewardsCardMap);

        // Group by reward points
        Map<Integer, List<RewardsCard>> groupedByPoints = rewardsCards.stream()
                .collect(Collectors.groupingBy(RewardsCard::getRewardPoints));
        System.out.println("Rewards Cards Grouped by Reward Points: " + groupedByPoints);

        // Partition by whether the reward points are above or below 300
        Map<Boolean, List<RewardsCard>> partitionedByPoints = rewardsCards.stream()
                .collect(Collectors.partitioningBy(card -> card.getRewardPoints() > amount));
        System.out.println("Rewards Cards Partitioned by Points > 300: " + partitionedByPoints);
    }

    private static void findAnyName(List<RewardsCard> rewardsCards) {
        // [02 OOP2] Streams - findAny() - Find any RewardsCard (this could be any card)
        Optional<RewardsCard> anyCard = rewardsCards.stream().findAny();
        anyCard.ifPresent(card -> System.out.println("Any Reward Card found: " + card.getName()));

        // [02 OOP2] Streams -findFirst() - Find the first RewardsCard in the list
        Optional<RewardsCard> firstCard = rewardsCards.stream().findFirst();
        firstCard.ifPresent(card -> System.out.println("First Reward Card: " + card.getName()));
    }

    private static void checkCardsIfBalancesMatches(List<RewardsCard> rewardsCards, int amount) {
        // allMatch() - Check reward points greater than
        boolean allMatch = rewardsCards.stream().allMatch(card -> card.getRewardPoints() > amount);
        System.out.println("All cards have reward points greater than " +amount +" : " + allMatch);

        // anyMatch() - Check if any RewardsCard has reward points greater than
        boolean anyMatch = rewardsCards.stream().anyMatch(card -> card.getRewardPoints() > amount);
        System.out.println("Any card has reward points greater than " +amount +" : "  + anyMatch);

        // noneMatch() - Check if none of the RewardsCards has reward points greater than
        boolean noneMatch = rewardsCards.stream().noneMatch(card -> card.getRewardPoints() > amount);
        System.out.println("No cards have reward points greater than " +amount +" : " + noneMatch);
    }

    // [02 OOP2] Streams forEach() - Print each RewardsCard's details
    private static void printAllForEachCard(List<RewardsCard> rewardsCards) {
        // count() - Count the number of RewardsCards
        rewardsCards.stream().forEach(card -> System.out.println(card.getName() + " " + card.getSurname() + " Points: " + card.getRewardPoints()));

    }

    // [02 OOP2] Streams - min() max()
    private static void getNumberOfRewardCards(List<RewardsCard> rewardsCards) {
        // count() - Count the number of RewardsCards
        long count = rewardsCards.size();
        System.out.println("Total Rewards Cards: " + count);
    }
    // [02 OOP2] Streams - min() max()
    private static void getLowestAndHighestPoints(List<RewardsCard> rewardsCards) {
        // min() - Find the RewardsCard with the fewest reward points
        Optional<RewardsCard> minRewardCard = rewardsCards.stream()
                .min(Comparator.comparingInt(RewardsCard::getRewardPoints));
        minRewardCard.ifPresent(card -> System.out.println("Min Reward Points " + card.getRewardPoints()));

        // max() - Find the RewardsCard with the most reward points
        Optional<RewardsCard> maxRewardCard = rewardsCards.stream()
                .max(Comparator.comparingInt(RewardsCard::getRewardPoints));
        maxRewardCard.ifPresent(card -> System.out.println("Max Reward Points " + card.getRewardPoints()));
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

    // [A2_OOP2] Concurrency e.g. using ExecutorService to process a list of Callables.
    private static void processContractorConcurrently() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Contractor> contractor = List.of(
                new Contractor(1, "Alan Brown", "Electrician", 500, "Fix Lighting"),
                new Contractor(2, "joe bloggs", "Plumber", 550, "Fix toilet"),
                new Contractor(3, "Alan Brown", "Plumber", 300, "Fix Sink")
        );

        List<Callable<String>> contractorTasks = new ArrayList<>(contractor);

        try {
            List<Future<String>> results = executorService.invokeAll(contractorTasks);
            for (Future<String> future : results) {
                System.out.println("Contractor - " + future.get() + " ");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
    //[A1_OOP2] Collections/generics - for example: use of Comparator.comparing() for sorting.
    private static void sortManagerSortByWage() {
        List<Manager> employees = new ArrayList<>(List.of(
                new Manager(500, "Alan murohy", 10, "Manager"),
                new Manager(350, "joe bloggs", 30, "Supervisor"),
                new Manager(750, "Alan Brown", 15, "Assist Manager")
        ));
        employees.sort(Comparator.comparing(Manager::getWage));
        System.out.println("\n-------- Sorted Manager by Wage (using Comparator.comparing() ):");
        employees.forEach(employee -> System.out.println(employee.getName() +" is paid " + employee.getWage()));
    }

    private static void showMessagesByLanguage(Locale lang) {
        ResourceBundle messages = ResourceBundle.getBundle("classes.Language", lang);

        String goodbye = messages.getString("goodbye");
        String farewell = messages.getString("farewell");

        System.out.println("Greeting ->" + goodbye);
        System.out.println("Farewell ->" + farewell);
    }
    //[04_OOP2] Switch expressions with pattern matching.
    private static boolean setOffersAvailable(String weekday){
        System.out.println();
        System.out.println("----- Switch expressions with pattern matching example ------ ");
        System.out.println("Day Of Week is -> "+weekday);

        return switch (weekday) {
            case String s when s.equals("THURSDAY") || s.equals("SATURDAY") -> true;
            default -> false;
        };
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