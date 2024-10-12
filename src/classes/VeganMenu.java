package classes;

import java.util.ArrayList;

public class VeganMenu implements Menu {

    private final ArrayList<MenuItem> menu;

    public VeganMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public void displayMenu() {
        for (MenuItem menuItem : menu) System.out.println(menuItem.getName()+" "+ menuItem.getPrice());
    }
}
