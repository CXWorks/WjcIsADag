package ui.hallui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.AbstractCheckItem;
import vo.managevo.staff.DriverVO;

public class DriverAbstractCheckItem extends AbstractCheckItem<DriverVO> {

	DriverAbstractCheckItem(DriverVO vo){
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