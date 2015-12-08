package ui.orderui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.VOCheckItem;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;

public class DeliverVOCheckItem extends VOCheckItem<DeliverVO> {

	DeliverVOCheckItem(DeliverVO vo){
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
    	DeliverVO vo = this.vo.getValue();
    	
        return vo.getOrderID()+vo.getReceivePeople();
    }
}
