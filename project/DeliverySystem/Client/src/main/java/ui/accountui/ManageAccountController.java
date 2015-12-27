package ui.accountui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ui.common.checkFormat.field.FloatOnlyField;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import vo.accountvo.AccountVO;

public class ManageAccountController {
    public AnchorPane outerPane;
    private AccountBLManageService accountBLManageService = AccountFactory.getManageService();
    private List<AccountCheckItem> accounts = new ArrayList<>();

    public TableView<AccountCheckItem> accounts_TableView;
    public TableColumn<AccountCheckItem, AccountCheckItem> check_TableColumn;
    public TableColumn<AccountCheckItem, String> id_TableColumn;
    public TableColumn<AccountCheckItem, String> password_TableColumn;
    public TableColumn<AccountCheckItem, String> staff_TableColumn;

	public TextField search_Field;
    public CheckBox all_CheckBox;
    public Label err_Label;

    private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(ManageAccountController.class.getResource("manageAccount.fxml"));
        Pane pane = loader.load();
        ManageAccountController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller.informController.stackPane;
    }

    @FXML
    public void initialize(){
    	accounts_TableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        accounts_TableView.setItems(FXCollections.observableArrayList(accounts));

        id_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getVo().getID())
        );
        password_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getVo().getPassword())
        );

        // check and select
        check_TableColumn.setCellFactory(
                o -> new MyTableCell()
        );
        check_TableColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue())
        );

        accounts_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        System.out.println("selected empty");
                    } else {
                        System.out.println("selected " + newValue.getVo().getID());
                    }
                }
        );
        refreshItems(accountBLManageService.getAccountVOs());

        // column width
        outerPane.widthProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("inner width change to : " + newValue);
                }
        );

        ObservableValue<Number> width =  accounts_TableView.widthProperty().subtract(70).divide(3);
        id_TableColumn.prefWidthProperty().bind(width);
        password_TableColumn.prefWidthProperty().bind(width);
        staff_TableColumn.prefWidthProperty().bind(width);
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        try {
            Stage dialogStage = new Stage();
            EditAccountDialogController controller = EditAccountDialogController.newDialog
                    (dialogStage, EditAccountDialogController.EditType.NEW, null, informController);
            dialogStage.showAndWait();
            refreshItems(accountBLManageService.getAccountVOs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
	public void search(ActionEvent actionEvent) {
        String filter = search_Field.getText();
        List<AccountVO> results = accountBLManageService.fuzzySearch(filter);
        refreshItems(results);
    }

    @FXML
	public void delete(ActionEvent actionEvent) {
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getSelected()){
                accounts.remove(accounts.get(i));
                ObservableList<AccountCheckItem> list = accounts_TableView.getItems();
                accountBLManageService.deleteAccount(list.get(i).getVo());
                list.remove(list.get(i));
                --i;
            }
        }
        refreshItems(accountBLManageService.getAccountVOs());
    }

    @FXML
	public void edit(ActionEvent actionEvent) {
        ObservableList<AccountCheckItem> checkItems = accounts_TableView.getSelectionModel().getSelectedItems();
        if(checkItems.size() != 1){
            informController.inform("请选择一个账户");
            System.out.println("please choose one item");
            return;
        }
        try {
            Stage dialogStage = new Stage();
            EditAccountDialogController controller = EditAccountDialogController.newDialog
                    (dialogStage, EditAccountDialogController.EditType.EDIT,  checkItems.get(0).getVo(), informController);
            dialogStage.showAndWait();
            refreshItems(accountBLManageService.getAccountVOs());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private boolean isAllSelected(){
        for (AccountCheckItem account : accounts) {
            if(!account.getSelected()){
                return false;
            }
        }
        return true;
    }

    private void setAllSelectedValue(boolean value){
        for (AccountCheckItem account : accounts) {
            account.setSelected(value);
        }
        ObservableList<AccountCheckItem> list = accounts_TableView.getItems();
        for (int i = 0; i < list.size(); i++) {
            accounts_TableView.getSelectionModel().select(i);
        }
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

    private class MyTableCell extends TableCell<AccountCheckItem, AccountCheckItem> {
        @Override
        protected void updateItem(AccountCheckItem item, boolean empty) {
            super.updateItem(item, empty);

            if(item == null || empty){
                setGraphic(null);
                return;
            }

            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().bindBidirectional(item.selectedProperty());
            checkBox.selectedProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        int row = accounts_TableView.getItems().indexOf(item);
                    }
            );

            setGraphic(checkBox);
        }
    }

    private void refreshItems(List<AccountVO> accountVOs){
        accounts.clear();
        accounts_TableView.getItems().clear();
        for (AccountVO accountVO : accountVOs) {
            accounts.add(new AccountCheckItem(accountVO));
        }
        accounts_TableView.getItems().addAll(accounts);
    }
}
