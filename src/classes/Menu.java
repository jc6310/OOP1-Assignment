package classes;

import java.util.ArrayList;

// [3] interface example
public sealed interface Menu permits KidsMenu, VeganMenu, StandardMenu{
    void displayMenu();

    // [A2] Private, default and static interface methods.
    default void isSpecialMenu(String option) {
        System.out.println("Is default menu or not");
        if(option.equals("Special")) {
            printSpecial();
        }
    }

    private void printSpecial() {
        System.out.println("We can provide a is custom menu, on request");
    }

    // static method
    static StandardMenu createMenu() {
        var menu_items = new ArrayList<MenuItem>();
        menu_items.add(new MenuItem(31, "Special Vegan Burger", 12.99));
        menu_items.add(new MenuItem(32, "Duck With Taco", 8.99));
        menu_items.add(new MenuItem(33, "Shark salad soup", 6.99));
        menu_items.add(new MenuItem(34, "Potato Nuggets", 9.99));

        return new StandardMenu(menu_items);
    }
}
