package ui.accountui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.util.Callback;
import po.accountdata.AuthorityEnum;
import po.memberdata.StaffTypeEnum;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.financeui.ManageBankAccountController;
import vo.accountvo.AccountVO;
import vo.managevo.staff.StaffVO;

public class ManageAccountController {

    private List<AccountVOCheckItem> accounts;

	public TextField search_Field;
	public ListView<AccountVOCheckItem> accounts_ListView;

	public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageAccountController.class.getResource("manageAccount.fxml"));
    }

	public void makeTest(){
        accounts = new ArrayList<>();
        accounts.add(new AccountVOCheckItem(new AccountVO("dora", "1243", AuthorityEnum.HAVE)));
        accounts.add(new AccountVOCheckItem(new AccountVO("wjr", "2333", AuthorityEnum.DONT_HAVE)));
    }
	
    public void initialize(){
        makeTest();

        accounts_ListView.setItems(FXCollections.observableArrayList(accounts));

        accounts_ListView.setCellFactory(
                CheckBoxListCell.forListView(
                        (item) -> item.selectedProperty()
                )
        );
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

    static class MyCheckBoxListCell extends CheckBoxListCell<String>{

    }
}
