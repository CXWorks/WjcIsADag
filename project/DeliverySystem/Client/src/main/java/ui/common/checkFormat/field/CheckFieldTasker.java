package ui.common.checkFormat.field;

import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import factory.FormFactory;
import factory.FormatCheckFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckFieldTasker extends CheckTasker {

    protected TextField field;

    protected CheckFieldTasker(Label err_Label, TextField field) {
        super(err_Label);
        this.field = field;
    }

}
