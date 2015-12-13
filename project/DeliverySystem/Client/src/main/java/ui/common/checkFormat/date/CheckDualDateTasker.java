package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import jdk.nashorn.internal.parser.DateParser;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckDualDateTasker extends CheckTasker {

    private DatePicker before;
    private DatePicker after;

    public CheckDualDateTasker(Label errLabel, DatePicker after, DatePicker before) {
        super(errLabel);
        this.after = after;
        this.before = before;
    }

    @Override
    public boolean check() {
        return false;
    }
}
