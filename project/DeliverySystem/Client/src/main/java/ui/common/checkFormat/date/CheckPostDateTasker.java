package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import message.CheckFormMessage;
import tool.time.TimeConvert;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckPostDateTasker extends CheckDateTasker{

    protected CheckPostDateTasker(Label errLabel, DatePicker datePicker) {
        super(errLabel, datePicker);
    }

    @Override
    public boolean check() {
        Calendar calendar = TimeConvert.convertDate(datePicker.getValue());
        CheckFormMessage msg = checkService.checkPostDate(calendar);
        return handle(msg);
    }
}
