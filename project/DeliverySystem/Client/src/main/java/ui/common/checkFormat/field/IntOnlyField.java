package ui.common.checkFormat.field;

import javafx.scene.control.TextField;

/**
 * Created by Sissel on 2015/12/13.
 */
public class IntOnlyField extends TextField {
    private static final String regex = "0|[1-9][0-9]*";

    @Override
    public void replaceText(int start, int end, String text) {
        String origin = this.getText();
        if((origin + text).matches(regex)){
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        String origin = this.getText();
        if((origin + replacement).matches(regex)){
            super.replaceSelection(replacement);
        }
    }
}
