package ui.accountui;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import factory.BLFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import message.OperationMessage;
import po.accountdata.AuthorityEnum;
import po.memberdata.StaffTypeEnum;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.financeui.AccountEditDialogController;
import vo.accountvo.AccountVO;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class NewAccountController {
	public enum EditType{
		NEW,
		EDIT
	}

    public EditType type;
    public Stage stage;
    private AccountVO editVO;

	public TextField id_Field;
	public ChoiceBox<SimpleEnumProperty<StaffTypeEnum>> type_ChoiceBox;
	public TextField password_Field;
	private StaffTypeEnum staffTypeEnum;

	private AccountBLManageService accountBLManageService = AccountFactory.getManageService();

    public static NewAccountController newDialog(EditType type, AccountVO editVO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NewAccountController.class.getResource("newAccount.fxml"));
        Pane pane = loader.load();

        Stage stage = new Stage();
        stage.setTitle(
                type == EditType.EDIT ? "修改账户" : "新建账户"
        );
        stage.initOwner(Main.primaryStage);
        stage.setScene(new Scene(pane));

        NewAccountController controller = (NewAccountController)loader.getController();
        controller.editVO = editVO;
        controller.stage = stage;
        controller.type = type;
        controller.init();

        return controller;
    }

    private void setType(StaffTypeEnum typeEnum){
        for (SimpleEnumProperty<StaffTypeEnum> enumProperty : type_ChoiceBox.getItems()) {
            if(enumProperty.getEnum() == typeEnum){
                type_ChoiceBox.setValue(enumProperty);
                return;
            }
        }
    }

    private void init() {
        switch (type){
            case NEW:
                id_Field.clear();
                password_Field.clear();
                break;
            case EDIT:
                id_Field.setText(editVO.getID());
                password_Field.setText(editVO.getPassword());

                break;
        }
    }

    public static Parent launch() throws IOException {
		return FXMLLoader.load(NewAccountController.class.getResource("newAccount.fxml"));
	}

	@FXML
	public void initialize(){
		type_ChoiceBox.setItems(
				Enum2ObservableList.transit(StaffTypeEnum.values())
		);

		type_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					staffTypeEnum = newValue.getEnum();
					System.out.println("selected "+staffTypeEnum);
				}
		);

	}

	public void clear(ActionEvent actionEvent) {
		stage.close();
	}

	public void commit(ActionEvent actionEvent) {
        OperationMessage msg = accountBLManageService.addAccount(generateAccountVO());
        if(msg.operationResult){
            System.out.println("add successfully");
        }else{
            System.out.println("fail: " + msg.getReason());
        }
        stage.close();
	}

    private AccountVO generateAccountVO(){
        // TODO check
        return new AccountVO(id_Field.getText(), password_Field.getText(), AuthorityEnum.DONT_HAVE);
    }
}