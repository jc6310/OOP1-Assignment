package classes;

import java.util.ArrayList;

public final class StandardMenu implements Menu {

    private final ArrayList<MenuItem> menu;

    public StandardMenu(ArrayList<MenuItem> menu) {
        this.menu = menu; 
    }

    // [04] Overriding example from the menu
    @Override
    public void displayMenu() {
        for (MenuItem menuItem : menu) System.out.println(menuItem.getName()+" "+ menuItem.getPrice());
    }
}
