package ui.common.checkFormat.field;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckOrderTasker extends CheckFieldTasker {

    public CheckOrderTasker(TextField field) {
        super(field);
    }

    @Override
    public boolean check() {
        CheckFormMessage msg = checkService.checkOrderID(field.getText());
        return handle(msg);
    }
}
