package ui.hallui;

import java.io.IOException;
import java.time.LocalDate;

import po.orderdata.DeliverTypeEnum;
import tool.ui.SimpleEnumProperty;
import ui.accountui.ManageAccountController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

/**
 * Created by Sissel on 2015/11/27.
 */
public class RevenueFormController {
    public TableView revenues_TableView;
    public TableColumn date_TableColumn;
    public TableColumn money_TableColumn;
    public TableColumn deliver_TableColumn;
    public TableColumn order_TableColumn;
    public DatePicker revenue_DatePicker;
    public TextField money_Field;
    public TextField order_Field;
    public ChoiceBox<SimpleEnumProperty<DeliverTypeEnum>> deliver_ChoiceBox;

    
	public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageAccountController.class.getResource("revenueForm.fxml"));
    }

    
    
    public void add(ActionEvent actionEvent) {
    }

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void commit(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {
    	revenue_DatePicker.setValue(LocalDate.now());
    	money_Field.clear();
    	order_Field.clear();
    	deliver_ChoiceBox
    	
    }
}
