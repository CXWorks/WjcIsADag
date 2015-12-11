package ui.hallui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.AbstractCheckItem;
import vo.managevo.car.CarVO;

public class CarAbstractCheckItem extends AbstractCheckItem<CarVO> {

	CarAbstractCheckItem(CarVO vo){
        this.vo = new SimpleObjectProperty<>(vo);
        this.selected = new SimpleBooleanProperty(false);
        selected.addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(vo.getCarID() + " change to " + newValue.booleanValue());
                }
        );
    }

    @Override
    public String toString() {
    	CarVO vo = this.vo.getValue();

        return vo.getCarID()+vo.getNameID()+vo.getUseTime();
    }
}
