package ui.accountui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import ui.common.VOCheckItem;
import vo.accountvo.AccountVO;

/**
 * Created by Sissel on 2015/11/25.
 */
public class AccountVOCheckItem extends VOCheckItem<AccountVO> {

    AccountVOCheckItem(AccountVO vo, ChangeListener cl){
        this.vo = new SimpleObjectProperty<>(vo);
        this.selected = new SimpleBooleanProperty(false);
        this.selected.addListener(cl);
    }

    @Override
    public String toString() {
        AccountVO vo = this.vo.getValue();

        return vo.getID() + vo.getPassword();
    }

}
