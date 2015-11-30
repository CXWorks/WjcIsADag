package ui.hallui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.VOCheckItem;
import vo.accountvo.AccountVO;
import vo.managevo.staff.DriverVO;

public class DriverVOCheckItem extends VOCheckItem<DriverVO> {

	DriverVOCheckItem(DriverVO vo){
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
    	DriverVO vo = this.vo.getValue();

        return vo.getID() +vo.getName();
    }
}