package ui.common.checkFormat.field;

import javafx.scene.control.TextField;
import message.CheckFormMessage;

/**
 * Created by Sissel on 2015/12/31.
 */
public class CheckIsNullTasker extends CheckFieldTasker {
	private CheckIsNullTasker(){
		super(null);
	}

    public CheckIsNullTasker(TextField field) {
        super(field);
    }

    @Override
    public boolean check() {
        CheckFormMessage message = checkService.checkIsNull(field.getText());
        return handle(message);
    }
}
