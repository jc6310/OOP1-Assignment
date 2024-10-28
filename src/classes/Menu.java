package classes;

// [3] interface example
public sealed interface Menu permits KidsMenu, VeganMenu, StandardMenu{
    void displayMenu();
}
