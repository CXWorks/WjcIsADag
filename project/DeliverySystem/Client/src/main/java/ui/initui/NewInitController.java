package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import po.systemdata.SystemState;
import ui.financeui.ManageBankAccountController;
import userinfo.UserInfo;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/27.
 */
public class NewInitController {
    public Label systemState_Label;
    private InitializationBLService initializationBLService = InitBLFactory.getInitializationBLService();
    private Pane father;

    public static Parent launch(Pane father) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CheckInitInfoController.class.getResource("newInit.fxml"));
        Pane pane = loader.load();
        NewInitController controller = loader.getController();
        controller.father = father;
        return pane;
    }

    public void manageStore(ActionEvent actionEvent) {

    }

    public void manageAccount(ActionEvent actionEvent) {
        father.getChildren().clear();
        try {
            father.getChildren().add(ManageBankAccountController.launch());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageInstitution(ActionEvent actionEvent) {
    }

    public void manageCars(ActionEvent actionEvent) {
    }

    public void manageStaff(ActionEvent actionEvent) {
    }

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
        if(UserInfo.getSystemState() == SystemState.INITIALIZING){
            initializationBLService.abortInitData();
            father.getChildren().clear();
            try {
                father.getChildren().add(CheckInitInfoController.launch(father));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void commit(ActionEvent actionEvent) {
        initializationBLService.uploadInitialData();
        father.getChildren().clear();
        try {
            father.getChildren().add(CheckInitInfoController.launch(father));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
