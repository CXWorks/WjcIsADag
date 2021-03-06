package ui.common.checkFormat.field;

import javafx.scene.control.TextField;

/**
 * Created by Sissel on 2015/12/13.
 */
public class NumberOnlyField extends TextField {

    private static final String regex = "[0-9]*";

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
