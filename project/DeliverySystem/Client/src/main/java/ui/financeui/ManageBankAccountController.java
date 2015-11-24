package ui.financeui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import vo.financevo.BankAccountVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/24.
 */
public class ManageBankAccountController {
    public TextField name_Field;
    public TableColumn<BankAccountVO, String> name_Column;
    public TableColumn<BankAccountVO, String> balance_Column;
    public TableView<BankAccountVO> accounts_TableView;

    private ObservableList<BankAccountVO> accountVOs;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageBankAccountController.class.getResource("manageBankAccount.fxml"));
    }

    @FXML
    public void initialize(){
        name_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAccountName()));
        balance_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getBalance()));
        accounts_TableView.setItems(accountVOs);

    }

    public void newAccount(ActionEvent actionEvent) {
    }

    public void deleteAccount(ActionEvent actionEvent) {
    }

    public void editAccount(ActionEvent actionEvent) {
    }

    public void checkHistory(ActionEvent actionEvent) {
    }

    public void search(ActionEvent actionEvent) {
    }
}
