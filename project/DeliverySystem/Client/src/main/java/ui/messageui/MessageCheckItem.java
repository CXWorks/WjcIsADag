package ui.messageui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import message.ChatMessage;
import ui.common.AbstractCheckItem;

/**
 * Created by Sissel on 2015/12/11.
 */
public class MessageCheckItem extends AbstractCheckItem<ChatMessage> {

    public MessageCheckItem(ChatMessage message) {
        this.vo = new SimpleObjectProperty<>(message);
        this.selected = new SimpleBooleanProperty(false);
        vo.addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println();
                }
        );
    }

    @Override
    public String toString() {
        return null;
    }
}
