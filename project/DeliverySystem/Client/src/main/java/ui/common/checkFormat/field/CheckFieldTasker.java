package ui.common.checkFormat.field;

import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import factory.FormFactory;
import factory.FormatCheckFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;
import ui.common.checkFormat.CheckTasker;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckFieldTasker extends CheckTasker {

    protected TextField field;

    protected CheckFieldTasker(TextField field) {
        super();
        this.field = field;
    }

    @Override
    public boolean handle(CheckFormMessage msg){
        if(msg.getCheckResult()){
        	field.setPromptText(null);
            return true;
        }else{
            field.clear();
            field.getStyleClass().add("text-field-check");
            field.setPromptText(msg.getReason());
            return false;
        }
    }

}
