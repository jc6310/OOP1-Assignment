package classes;

import java.util.ListResourceBundle;

public class Language_fr extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "goodbye", "Merci pour vos achats" },
                { "farewell", "S'il te plaît, reviens" }
        };
    }
}
