package ui.searchui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.common.AbstractCheckItem;
import vo.logisticsvo.LogisticsVO;
import vo.ordervo.OrderVO;

public class LogisticsAbstractCheckItem extends AbstractCheckItem<LogisticsVO> {

	LogisticsAbstractCheckItem(LogisticsVO vo){
        this.vo = new SimpleObjectProperty<>(vo);
        this.selected = new SimpleBooleanProperty(false);
        selected.addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(vo.getLocation()+ " change to " + newValue.booleanValue());
                }
        );
    }

    @Override
    public String toString() {
    	LogisticsVO vo = this.vo.getValue();

        return vo.getLocal();
    }
}
