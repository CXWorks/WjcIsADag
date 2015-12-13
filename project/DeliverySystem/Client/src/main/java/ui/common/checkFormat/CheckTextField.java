package ui.common.checkFormat;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/12.
 */
public class CheckTextField extends TextField{

    protected Label errLabel;

    public void setErrLabel(Label errLabel) {
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
