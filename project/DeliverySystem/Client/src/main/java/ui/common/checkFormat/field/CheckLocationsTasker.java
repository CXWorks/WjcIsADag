package ui.common.checkFormat.field;

import javafx.scene.control.TextField;
import message.CheckFormMessage;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/31.
 */
public class CheckLocationsTasker extends CheckTasker{

    private TextField from;
    private TextField to;

    @Override
    protected boolean handle(CheckFormMessage message) {
        if(message.getCheckResult()){
            return true;
        }else{
            from.clear();
            from.setPromptText(message.getReason());
            to.clear();
            to.setPromptText(message.getReason());
            return false;
        }
    }

    @Override
    public boolean check() {
        CheckFormMessage message = checkService.checkLocation(from.getText(), to.getText());
        return handle(message);
    }
}
