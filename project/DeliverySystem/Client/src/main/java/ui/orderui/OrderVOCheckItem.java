package ui.orderui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.VOCheckItem;
import vo.ordervo.OrderVO;

public class OrderVOCheckItem extends VOCheckItem<OrderVO> {

	OrderVOCheckItem(OrderVO vo){
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
