package ui.financeui;

import bl.blService.financeblService.BankAccountBLService;
import factory.FinanceBLFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
    private BankAccountBLService bankAccountBLService = FinanceBLFactory.getBankAccountBLService();
    private BankAccountVO bankAccountVO = null;

    public TextField name_Field;
    public TableColumn<BankAccountVO, String> name_Column;
    public TableColumn<BankAccountVO, String> balance_Column;
    public TableView<BankAccountVO> accounts_TableView;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(ManageBankAccountController.class.getResource("manageBankAccount.fxml"));
    }

    @FXML
    public void initialize(){
        name_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getAccountName())
        );
        balance_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBalance())
        );
        accounts_TableView.setItems(
                FXCollections.observableArrayList(
                        new BankAccountVO("wjr", "wjr", "-998"),
                        new BankAccountVO("bill", "Micro$oft", "99999999999999999999999")
                )
        );
        accounts_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("selected " + newValue.accountName);
                    
                    bankAccountVO = newValue;
                }
        );
    }

    public void newAccount(ActionEvent actionEvent) throws IOException {
        BankAccountVO bankAccountVO = new BankAccountVO(null, null, null);
        AccountEditDialogController controller = AccountEditDialogController.newDialog
                (AccountEditDialogController.EditType.NEW, bankAccountVO);

        controller.stage.showAndWait();

        accounts_TableView.getItems().add(bankAccountVO);
        //bankAccountBLService.addAccount(bankAccountVO);
    }

    public void deleteAccount(ActionEvent actionEvent) {
    }

    public void editAccount(ActionEvent actionEvent) throws IOException {
        BankAccountVO bankAccountVO = this.bankAccountVO;
        AccountEditDialogController controller = AccountEditDialogController.newDialog
                (AccountEditDialogController.EditType.EDIT, bankAccountVO);

        controller.stage.showAndWait();

        //bankAccountBLService.editAccount(bankAccountVO, bankAccountVO.accountName);
    }

    public void checkHistory(ActionEvent actionEvent) {
        // TODO mageji youyaoxinchuangkou xian ting ting
    }

    public void search(ActionEvent actionEvent) {

    }
}
