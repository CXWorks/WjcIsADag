package ui.common.checkFormat.field;

import javafx.scene.control.PasswordField;

/**
 * Created by Sissel on 2015/12/14.
 */
public class PasswordOnlyField extends PasswordField {

    private static final String regex = "([0-9]|[a-z]|(A-Z)|[!@#$%^&\\*_-])*";

    @Override
    public void replaceText(int start, int end, String text) {
        if(text.matches(regex)){
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        if(replacement.matches(regex)){
            super.replaceSelection(replacement);
        }
    }
}
