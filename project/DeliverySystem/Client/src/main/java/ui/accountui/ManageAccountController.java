package ui.accountui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import javafx.beans.property.SimpleBooleanProperty;
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
    private List<AccountVOCheckItem> accounts;

	public TextField search_Field;
    public CheckBox all_CheckBox;
	public ListView<AccountVOCheckItem> accounts_ListView;

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
        makeTest();

        accounts_ListView.setItems(FXCollections.observableArrayList(accounts));
        accounts_ListView.setCellFactory(
                (listView)->new MyCell()
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
        // TODO filter accountBLManageService
	}

    @FXML
	public void delete(ActionEvent actionEvent) {
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getSelected()){
                accounts.remove(i);
                accounts_ListView.getItems().remove(i);
                //accountBLManageService.deleteAccount(account.getVo());
            }
        }
    }

    @FXML
	public void edit(ActionEvent actionEvent) {
        AccountVO selected = accounts_ListView.getSelectionModel().getSelectedItem().getVo();
        // TODO POPUP EDIT WINDOW

        accountBLManageService.modifyAccount(selected);
	}

    class MyCell extends ListCell<AccountVOCheckItem>{
        @Override
        protected void updateItem(AccountVOCheckItem item, boolean empty) {
            super.updateItem(item, empty);

            if(item == null || empty){
                return;
            }

            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

            double length = accounts_ListView.getWidth() - checkBox.getWidth();

            Label name_Label = new Label(item.getVo().getID());
            name_Label.setAlignment(Pos.BASELINE_CENTER);
            name_Label.setPrefWidth(length / 3);
            Label password_Label = new Label(item.getVo().getPassword());
            password_Label.setAlignment(Pos.BASELINE_CENTER);
            password_Label.setPrefWidth(length / 3);

            HBox hbox = new HBox();
            hbox.getChildren().addAll(checkBox, name_Label, password_Label);

            this.setGraphic(hbox);
        }
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

    private int getSelectedNumber(){
        int sum = 0;
        for (AccountVOCheckItem account : accounts) {
            if(account.getSelected()){
                ++sum;
            }
        }
        return sum;
    }
}
