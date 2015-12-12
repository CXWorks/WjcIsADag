package ui.common.checkFormat;

import bl.blService.FormatCheckService.FormatCheckService;
import factory.FormFactory;
import factory.FormatCheckFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;
import ui.initui.CheckInitInfoController;

/**
 * Created by Sissel on 2015/12/12.
 */
public class CheckOrderIDField extends CheckTextField implements Checkable {

    FormatCheckService formatCheckService = FormatCheckFactory.getFormatCheckService();

    @Override
    public boolean check() {
        CheckFormMessage msg = formatCheckService.checkOrderID(this.getText());
        return handle(msg);
    }
}
