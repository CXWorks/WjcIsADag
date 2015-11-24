package ui.accountui;

import java.io.IOException;





import po.memberdata.StaffTypeEnum;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import ui.financeui.ManageBankAccountController;
import vo.accountvo.AccountVO;
import vo.managevo.staff.StaffVO;

public class ManageAccountController {

	public TextField account_Search;
	public TableColumn<StaffVO,String> id_Column;
	public TableColumn<StaffVO,StaffTypeEnum> type_Column;
	public CheckBox choose_All;
	
    public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageAccountController.class.getResource("manageAccount.fxml"));
    }
	
    public void initialize(){
    	 id_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
    	// type_Column.setCellValueFactory(cellData->new SimpleEnumProperty(cellData.getValue().getType()));
    }
	
	
	public void Delete(ActionEvent actionEvent){
		
	}
	
	public void Search(ActionEvent actionEvent){
		
	}
	
}
