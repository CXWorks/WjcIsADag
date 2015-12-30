package ui.common.checkFormat.date;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import jdk.nashorn.internal.parser.DateParser;
import message.CheckFormMessage;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public class CheckDualDateTasker extends CheckTasker {

    private DatePicker before;
    private DatePicker after;
    private Label errLabel;

    public CheckDualDateTasker(Label errLabel, DatePicker after, DatePicker before) {
        super();
        this.errLabel = errLabel;
        this.after = after;
        this.before = before;
    }

    @Override
    public boolean check() {
        return false;
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
