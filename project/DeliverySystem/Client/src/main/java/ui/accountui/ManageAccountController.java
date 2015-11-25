package ui.accountui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.scene.control.ListView;
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

    private List<AccountVO> accounts;

	public TextField search_Field;
	public ListView accounts_ListView;

	public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageAccountController.class.getResource("manageAccount.fxml"));
    }

	public void makeTest(){
        accounts = new ArrayList<>();
        accounts.add(new AccountVO());
    }
	
    public void initialize(){

    }
	
	public void Delete(ActionEvent actionEvent){
		
	}
	
	public void Search(ActionEvent actionEvent){
		
	}

	public void selectAll(ActionEvent actionEvent) {
	}

	public void search(ActionEvent actionEvent) {
	}

	public void delete(ActionEvent actionEvent) {
	}

	public void edit(ActionEvent actionEvent) {
	}
}
