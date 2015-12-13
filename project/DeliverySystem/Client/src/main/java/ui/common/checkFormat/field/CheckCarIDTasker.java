package ui.common.checkFormat.field;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckCarIDTasker extends CheckFieldTasker {

    public CheckCarIDTasker(Label err_Label, TextField field) {
        super(err_Label, field);
    }

    @Override
    public boolean check() {
        CheckFormMessage msg = checkService.checkTruckLoadID(field.getText());
        return handle(msg);
    }
}
