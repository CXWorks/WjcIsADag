package ui.accountui;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import message.OperationMessage;
import po.accountdata.AuthorityEnum;
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

    public EditType type;
    public Stage stage;
    private AccountVO editVO;

	public EngOnlyField id_Field;
	public EngOnlyField password_Field;

	private AccountBLManageService accountBLManageService = AccountFactory.getManageService();
	 private InformController informController;

    public static EditAccountDialogController newDialog(Stage stage, EditType type, AccountVO editVO, InformController informController) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditAccountDialogController.class.getResource("editAccountDialog.fxml"));
        Pane pane = loader.load();

        stage.setTitle(
                type == EditType.EDIT ? "修改账户" : "新建账户"
        );
        stage.initOwner(Main.primaryStage);

        EditAccountDialogController controller = (EditAccountDialogController)loader.getController();
        controller.informController = informController;
        stage.setScene(new Scene(pane));

        controller.editVO = editVO == null ? new AccountVO() : editVO;
        controller.stage = stage;
        controller.type = type;
        controller.init();

        return controller;
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
    }

	public void cancel(ActionEvent actionEvent) {
		stage.close();
	}

    public void commit(ActionEvent actionEvent) {
        OperationMessage msg;
        if(type == EditType.EDIT){
            msg = accountBLManageService.modifyAccount(editAccountVO());
        }else{
            msg  = accountBLManageService.addAccount(editAccountVO());
        }

        if(msg.operationResult){
            informController.inform("账户修改成功");
            System.out.println("commit successfully");
        }else{
            informController.inform("账户修改失败: " + msg.getReason());
            System.out.println("fail: " + msg.getReason());
        }
        stage.close();
	}

    private AccountVO editAccountVO(){
        // TODO check
        if(type == EditType.NEW){
            return new AccountVO(id_Field.getText(), password_Field.getText(), AuthorityEnum.DONT_HAVE);
        }
        editVO.password = password_Field.getText();
        return  editVO;
    }
}