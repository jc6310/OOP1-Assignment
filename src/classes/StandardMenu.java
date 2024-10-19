package classes;

import java.util.ArrayList;

public class StandardMenu implements Menu {

    private final ArrayList<MenuItem> menu;

    public StandardMenu(ArrayList<MenuItem> menu) {
        this.menu = menu; 
    }
    // [4] Overriding example from the menu
    @Override
    public void displayMenu() {
        for (MenuItem menuItem : menu) System.out.println(menuItem.getName()+" "+ menuItem.getPrice());
    }
}
