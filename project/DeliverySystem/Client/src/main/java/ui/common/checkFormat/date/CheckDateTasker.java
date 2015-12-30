package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import message.CheckFormMessage;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckDateTasker extends CheckTasker {
    protected DatePicker datePicker;
    protected Label errLabel;

    protected CheckDateTasker(Label errLabel, DatePicker datePicker) {
        super();
        this.errLabel = errLabel;
        this.datePicker = datePicker;
    }

    @Override
    protected boolean handle(CheckFormMessage message) {
        if(message.getCheckResult()){
            errLabel.setText("");
            return true;
        }else {
            errLabel.setText(message.getReason());
            return false;
        }
    }
}
