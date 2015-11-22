package ui.common;

import javafx.event.ActionEvent;

/**
 * Created by Sissel on 2015/11/21.
 */
public abstract class BasicFormController {

    public abstract void commit(ActionEvent actionEvent);

    public abstract void clear(ActionEvent actionEvent);

    public abstract void saveDraft(ActionEvent actionEvent);
}
