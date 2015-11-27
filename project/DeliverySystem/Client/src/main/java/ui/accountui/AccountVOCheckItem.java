package ui.accountui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.VOCheckItem;
import vo.accountvo.AccountVO;

/**
 * Created by Sissel on 2015/11/25.
 */
public class AccountVOCheckItem extends VOCheckItem<AccountVO> {

    AccountVOCheckItem(AccountVO vo){
        this.vo = new SimpleObjectProperty<>(vo);
        this.selected = new SimpleBooleanProperty(false);
        selected.addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(vo.getID() + " change to " + newValue.booleanValue());
                }
        );
    }

    @Override
    public String toString() {
        AccountVO vo = this.vo.getValue();

        return vo.getID() + vo.getPassword();
    }
}
