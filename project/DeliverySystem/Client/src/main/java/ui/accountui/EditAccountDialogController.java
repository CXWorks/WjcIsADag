package ui.accountui;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import message.OperationMessage;
import po.accountdata.AuthorityEnum;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.common.checkFormat.field.EngOnlyField;
import ui.informui.InformController;
import vo.accountvo.AccountVO;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditAccountDialogController {
	public enum EditType{
		NEW,
		EDIT
	}

    private EditType type;
    private Stage stage;
    private AccountVO editVO;

	public TextField id_Field;
	public TextField password_Field;
    private FormatCheckQueue formatCheckQueue;

	private AccountBLManageService accountBLManageService = AccountFactory.getManageService();
    private InformController informController;

    public static Stage newDialog(EditType type, AccountVO editVO, InformController ifc) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditAccountDialogController.class.getResource("editAccountDialog.fxml"));
        Pane pane = loader.load();
        Stage stage = new Stage();
        stage.setTitle(
                type == EditType.EDIT ? "修改账户" : "新建账户"
        );
        stage.initOwner(Main.primaryStage);

        EditAccountDialogController controller = loader.getController();
        controller.editVO = editVO == null ? new AccountVO() : editVO;
        controller.stage = stage;
        controller.type = type;
        controller.informController = ifc;
        controller.init();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane));

        return stage;
    }

    private void init() {
        switch (type){
            case NEW:
                id_Field.clear();
                password_Field.clear();
                break;
            case EDIT:
                id_Field.setText(editVO.getID());
                id_Field.setEditable(false);
                password_Field.setText(editVO.getPassword());
                break;
        }

        id_Field.requestFocus();

        formatCheckQueue = new FormatCheckQueue(
                new CheckIsNullTasker(id_Field),
                new CheckIsNullTasker(password_Field)
        );
    }

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}

    public void commit(ActionEvent actionEvent) {
        if(!formatCheckQueue.startCheck()){
            return;
        }

        OperationMessage msg;
        if(type == EditType.EDIT){
            msg = accountBLManageService.modifyAccount(editAccountVO());
        }else{
            msg  = accountBLManageService.addAccount(editAccountVO());
        }

        informController.inform(msg, "账户修改成功");
        stage.close();
	}

    private AccountVO editAccountVO(){
        if(type == EditType.NEW){
            return new AccountVO(id_Field.getText(), password_Field.getText(), AuthorityEnum.DONT_HAVE);
        }
        editVO.password = password_Field.getText();
        return  editVO;
    }
}