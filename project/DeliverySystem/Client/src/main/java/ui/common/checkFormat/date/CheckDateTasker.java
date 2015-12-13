package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckDateTasker extends CheckTasker {
    protected DatePicker datePicker;

    protected CheckDateTasker(Label errLabel, DatePicker datePicker) {
        super(errLabel);
        this.datePicker = datePicker;
    }

}
