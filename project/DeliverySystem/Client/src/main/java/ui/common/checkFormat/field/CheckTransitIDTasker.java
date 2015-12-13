package ui.common.checkFormat.field;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;
import ui.common.checkFormat.CheckFieldTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckTransitIDTasker extends CheckFieldTasker {

    public CheckTransitIDTasker(Label err_Label, TextField field) {
        super(err_Label, field);
    }

    @Override
    public boolean check() {
        CheckFormMessage msg = checkService.checkTransitID(field.getText());
        return handle(msg);
    }
}
