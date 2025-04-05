package classes;

import java.util.ListResourceBundle;

public class Language_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "goodbye", "Thanks you for shopping" },
                { "farewell", "Please Come again" }
        };
    }
}
