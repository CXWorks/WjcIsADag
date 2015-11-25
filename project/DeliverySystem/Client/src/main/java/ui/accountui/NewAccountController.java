package ui.accountui;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import factory.BLFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import po.memberdata.StaffTypeEnum;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import vo.accountvo.AccountVO;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class NewAccountController {

	public TextField id_Field;
	public ChoiceBox<SimpleEnumProperty<StaffTypeEnum>> type_ChoiceBox;
	public TextField password_Field;

	private StaffTypeEnum staffTypeEnum;

	private AccountBLManageService accountBLManageService = AccountFactory.getManageService();

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

        clear(null);
    }

	public void saveDraft(ActionEvent actionEvent) {
	}

	public void clear(ActionEvent actionEvent) {
        id_Field.clear();
        password_Field.clear();
        type_ChoiceBox.setValue(type_ChoiceBox.getItems().get(0));
        staffTypeEnum = StaffTypeEnum.DELIVER;
	}

	public void sure(ActionEvent actionEvent) {
	}
}
