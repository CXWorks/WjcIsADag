package ui.common.checkFormat;

import bl.blService.FormatCheckService.FormatCheckService;
import factory.FormatCheckFactory;
import javafx.scene.control.Label;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckTasker implements Checkable {

    protected FormatCheckService checkService;

    protected CheckTasker() {
        this.checkService = FormatCheckFactory.getFormatCheckService();
    }

    protected abstract boolean handle(CheckFormMessage message);

}
