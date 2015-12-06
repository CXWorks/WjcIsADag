package ui.accountui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
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
    private AccountBLManageService accountBLManageService = AccountFactory.getManageService();
    private List<AccountVOCheckItem> accounts = new ArrayList<>();

    public TableView<AccountVOCheckItem> accounts_TableView;
    public TableColumn<AccountVOCheckItem, AccountVOCheckItem> check_TableColumn;
    public TableColumn<AccountVOCheckItem, String> id_TableColumn;
    public TableColumn<AccountVOCheckItem, String> password_TableColumn;
    public TableColumn<AccountVOCheckItem, String> staff_TableColumn;

	public TextField search_Field;
    public CheckBox all_CheckBox;

	public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageAccountController.class.getResource("manageAccount.fxml"));
    }

	private void makeTest(){
        accounts = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            accounts.add(new AccountVOCheckItem(new AccountVO("dora", "1243", AuthorityEnum.HAVE)));
        }
        accounts.add(new AccountVOCheckItem(new AccountVO("wjr", "2333", AuthorityEnum.DONT_HAVE)));
    }
	
    public void initialize(){
    	this.accounts_TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        accounts_TableView.setItems(FXCollections.observableArrayList(accounts));

        id_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getVo().getID())
        );
        password_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getVo().getPassword())
        );
        check_TableColumn.setCellFactory(
                o -> new MyTableCell()
        );
        check_TableColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue())
        );
    }

	public void selectAll(ActionEvent actionEvent) {
        if((!all_CheckBox.isSelected()) && isAllSelected()){
            setAllSelectedValue(false);
        }else if(all_CheckBox.isSelected()){
            setAllSelectedValue(true);
        }else{
            // do nothing
        }
    }

    @FXML
	public void search(ActionEvent actionEvent) {
        String filter = search_Field.getText();
        accounts.clear();
        accounts_TableView.getItems().clear();
        for (AccountVO accountVO : accountBLManageService.getAccountVOs()) {
            accounts.add(new AccountVOCheckItem(accountVO));
        }
        accounts_TableView.getItems().addAll(accounts);
        // TODO filter accountBLManageService
	}

    @FXML
	public void delete(ActionEvent actionEvent) {
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getSelected()){
                accounts.remove(i);
                accounts_TableView.getItems().remove(i);
                //accountBLManageService.deleteAccount(account.getVo());
            }
        }
    }

    @FXML
	public void edit(ActionEvent actionEvent) {
        AccountVO selected = accounts_TableView.getSelectionModel().getSelectedItem().getVo();
        // TODO POPUP EDIT WINDOW

        accountBLManageService.modifyAccount(selected);
	}

    private boolean isAllSelected(){
        for (AccountVOCheckItem account : accounts) {
            if(!account.getSelected()){
                return false;
            }
        }
        return true;
    }

    private void setAllSelectedValue(boolean value){
        for (AccountVOCheckItem account : accounts) {
            account.setSelected(value);
        }
    }

    private class MyTableCell extends TableCell<AccountVOCheckItem, AccountVOCheckItem> {
        @Override
        protected void updateItem(AccountVOCheckItem item, boolean empty) {
            super.updateItem(item, empty);

            if(item == null || empty){
                return;
            }

            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

            setGraphic(checkBox);
        }
    }
}
