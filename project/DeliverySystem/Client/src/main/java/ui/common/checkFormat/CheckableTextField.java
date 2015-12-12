package ui.common.checkFormat;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Sissel on 2015/12/7.
 */
public class CheckableTextField extends TextField implements Checkable{

    Label errLabel;
    Checkable checkItem;

    CheckableTextField(Label errLabel){
        this.errLabel = errLabel;
    }

    @Override
    public boolean check() {
        return checkItem.check();
    }

    public void setCheckItem(Checkable checkItem){
        this.checkItem = checkItem;
    }
}
