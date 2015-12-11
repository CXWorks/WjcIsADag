package ui.orderui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.AbstractCheckItem;
import vo.ordervo.OrderVO;

public class OrderAbstractCheckItem extends AbstractCheckItem<OrderVO> {

	OrderAbstractCheckItem(OrderVO vo){
        this.vo = new SimpleObjectProperty<>(vo);
        this.selected = new SimpleBooleanProperty(false);
        selected.addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(vo.getFormID() + " change to " + newValue.booleanValue());
                }
        );
    }

    @Override
    public String toString() {
    	OrderVO vo = this.vo.getValue();

        return vo.getFormID()+vo.getAddressTo()+vo.getNameTo();
    }
}
