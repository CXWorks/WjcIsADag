package ui.financeui;

import bl.blService.financeblService.BankAccountBLService;
import factory.FinanceBLFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import po.financedata.FinancePayEnum;
import tool.time.TimeConvert;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/11/24.
 */
public class ManageBankAccountController {
    private BankAccountBLService bankAccountBLService;
    private BankAccountVO bankAccountVO = null;
    private List<BankAccountVO> bankAccountVOList = new ArrayList<>();

    public Button back_Btn;
    public TextField name_Field;
    public TableColumn<BankAccountVO, String> name_Column;
    public TableColumn<BankAccountVO, String> balance_Column;
    public TableView<BankAccountVO> accounts_TableView;
    public TableView<PaymentVO> history_TableView;
    public TableColumn<PaymentVO, String> type_TableColumn;
    public TableColumn<PaymentVO, String> date_TableColumn;
    public TableColumn<PaymentVO, String> amount_TableColumn;

    public static Parent launch(Pane father, Pane before, BankAccountBLService service) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ManageBankAccountController.class.getResource("manageBankAccount.fxml"));
        Pane pane = loader.load();
        ManageBankAccountController controller = loader.getController();
        controller.bankAccountBLService = service;

        if(father == null){
            pane.getChildren().remove(controller.back_Btn);
        }else{
            controller.back_Btn.setOnAction(
                    o -> {
                        father.getChildren().clear();
                        father.getChildren().add(before);}
            );
        }

        return pane;
    }

    @FXML
    public void newAccount(ActionEvent actionEvent) throws IOException {
        BankAccountVO bankAccountVO = new BankAccountVO(null, null, null);
        AccountEditDialogController controller = AccountEditDialogController.newDialog
                (AccountEditDialogController.EditType.NEW, bankAccountVO);

        controller.stage.showAndWait();

        accounts_TableView.getItems().add(bankAccountVO);
        //bankAccountBLService.addAccount(bankAccountVO);
    }

    @FXML
    public void deleteAccount(ActionEvent actionEvent) {
        bankAccountBLService.deleteAccount(this.bankAccountVO);

        accounts_TableView.getItems().remove(this.bankAccountVO);
    }

    @FXML
    public void editAccount(ActionEvent actionEvent) throws IOException {
        BankAccountVO bankAccountVO = this.bankAccountVO;
        AccountEditDialogController dialog = AccountEditDialogController.newDialog
                (AccountEditDialogController.EditType.EDIT, bankAccountVO);

        dialog.stage.showAndWait();

        //bankAccountBLService.editAccount(bankAccountVO, bankAccountVO.accountName);
    }

    @FXML
    public void checkHistory(ActionEvent actionEvent) {
        if(this.bankAccountVO == null){
            return;
        }
        history_TableView.getItems().clear();
        List<PaymentVO> paymentVOs = bankAccountBLService.getTradeHistory(this.bankAccountVO);
//        List<PaymentVO> paymentVOs = new ArrayList<>();
//        paymentVOs.add(new PaymentVO("11", Calendar.getInstance(), "3432", "432333", "程翔", "43242", "43243", "刘钦", "3223", FinancePayEnum.AWARD, "no"));

        history_TableView.getItems().addAll(paymentVOs);
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        if(name_Field.getText() == null || name_Field.getText().equals("")){
            setAccounts(bankAccountBLService.getAllAccounts());
        }else{
            List<BankAccountVO> newlist = bankAccountBLService.filterAccounts
                    (bankAccountVOList, name_Field.getText());
            setAccounts(newlist);
        }
    }

    private void setAccounts(List<BankAccountVO> list){
        bankAccountVOList.clear();
        bankAccountVOList.addAll(list);
        accounts_TableView.getItems().clear();
        accounts_TableView.getItems().addAll(bankAccountVOList);
    }

    @FXML
    public void initialize(){

        /**
         * configure accounts tableview
         */
        name_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountName())
        );
        balance_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBalance())
        );
        // TODO test
        accounts_TableView.setItems(
                FXCollections.observableArrayList(
                        new BankAccountVO("wjr", "wjr", "-998"),
                        new BankAccountVO("bill", "Micro$oft", "99999999999999999999999")
                )
        );
        accounts_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // TODO test
                    System.out.println("selected " + newValue.accountName);
                    bankAccountVO = newValue;
                    checkHistory(null);
                    
                }
        );

        /**
         * configure history tableview
         */
        type_TableColumn.setCellValueFactory(
                cellData -> {
                    PaymentVO cell = cellData.getValue();
                    String tp = this.bankAccountVO.bankID.equals(cell.payerAccID) ? "付款" : "收款";
                    return new SimpleStringProperty(tp);
                }
        );
        date_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(TimeConvert.getDisplayDate(cellData.getValue().date))
        );
        amount_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().amount)
        );
    }
}
