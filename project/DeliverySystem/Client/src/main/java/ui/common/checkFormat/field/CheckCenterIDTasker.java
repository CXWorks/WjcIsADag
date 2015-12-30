package ui.common.checkFormat.field;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckCenterIDTasker extends CheckFieldTasker {

    public CheckCenterIDTasker(Label err_Label, TextField field) {
        super(field);
    }

    @Override
    public boolean check() {
        CheckFormMessage msg = checkService.checkTransportCenterID(field.getText());
        return handle(msg);
    }
}
