package ui.orderui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.AbstractCheckItem;
import vo.delivervo.DeliverVO;

public class DeliverAbstractCheckItem extends AbstractCheckItem<DeliverVO> {

	DeliverAbstractCheckItem(DeliverVO vo){
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
