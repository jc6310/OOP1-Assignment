package classes;

import java.util.ArrayList;

public class KidsMenu implements Menu {

    private final ArrayList<MenuItem> menu;

    public KidsMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public void displayMenu() {
        for (MenuItem menuItem : menu) System.out.println(menuItem.getName()+" "+ menuItem.getPrice());
    }
}
