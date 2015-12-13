package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import message.CheckFormMessage;
import tool.time.TimeConvert;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckPreDateTasker extends CheckDateTasker {

    protected CheckPreDateTasker(Label errLabel, DatePicker datePicker) {
        super(errLabel, datePicker);
    }

    @Override
    public boolean check() {
        Calendar calendar = TimeConvert.convertDate(datePicker.getValue());
        CheckFormMessage msg = checkService.checkPreDate(calendar);
        return handle(msg);
    }
}
