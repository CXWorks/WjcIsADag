package ui.common.checkFormat;

import bl.blService.FormatCheckService.FormatCheckService;
import factory.FormatCheckFactory;
import javafx.scene.control.Label;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/13.
 */
public abstract class CheckTasker implements Checkable {

    protected Label errLabel;
    protected FormatCheckService checkService;

    protected CheckTasker(Label errLabel) {
        this.checkService = FormatCheckFactory.getFormatCheckService();
        this.errLabel = errLabel;
    }

    public boolean handle(CheckFormMessage msg){
        if(msg.getCheckResult()){
            if(errLabel != null){
                errLabel.setText("");
            }
            return true;
        }else{
            if(errLabel != null){
                errLabel.setText(msg.getReason());  // clear error tips
            }
            return false;
        }
    }
}
