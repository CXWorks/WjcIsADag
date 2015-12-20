package ui.financeui;

import bl.blService.financeblService.BankAccountBLService;
import factory.FinanceBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import ui.informui.InformController;
import vo.accountvo.AccountVO;
import vo.financevo.BankAccountVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/29.
 */
public class AccountEditDialogController {
    public enum EditType{
        NEW,
        EDIT
    }

    @FXML
    public TextField editName_Field;
    @FXML
    public TextField editBalance_Field;

    public EditType type;
    public Stage stage;

    // VO that is passed in by the creator
    private BankAccountVO editVO;
    private BankAccountBLService bankAccountBLService = FinanceBLFactory.getBankAccountBLService();

    private InformController informController;

    /**
     *
     * @param type
     * @param editVO
     * @return
     * @throws IOException
     */
    public static AccountEditDialogController newDialog(EditType type, BankAccountVO editVO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AccountEditDialogController.class.getResource("accountEditDialog.fxml"));
        Pane pane = loader.load();

        Stage stage = new Stage();
        stage.setTitle(
                type == EditType.EDIT ? "修改账户" : "新建账户"
        );
        stage.initOwner(Main.primaryStage);
        stage.setScene(new Scene(pane));

        AccountEditDialogController controller = (AccountEditDialogController)loader.getController();
        controller.informController = InformController.newInformController(pane);
        controller.editVO = editVO;
        controller.stage = stage;
        controller.type = type;
        controller.init();

        return controller;
    }

    private void init(){
        switch (type){
            case EDIT:
                editName_Field.setText(editVO.getAccountName());
                editBalance_Field.setText(editVO.getBalance());
                editBalance_Field.setEditable(false);
                break;
            case NEW:
                editName_Field.clear();
                editBalance_Field.clear();
                break;
        }
    }

    public void ok(ActionEvent actionEvent) {
        // TODO check

        if(type == EditType.EDIT){
            bankAccountBLService.editAccount(editVO, editName_Field.getText());
        }else{
            editVO.accountName = editName_Field.getText();
            editVO.balance = editBalance_Field.getText();

            bankAccountBLService.addAccount(editVO);
        }

        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }
}
