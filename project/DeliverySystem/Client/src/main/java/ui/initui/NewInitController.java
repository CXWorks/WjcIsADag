package ui.initui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import po.systemdata.SystemState;
import ui.financeui.ManageBankAccountController;
import ui.hallui.ManageCarDriverController;
import ui.manangeui.organization.ManageOrganizationController;
import ui.manangeui.staff.ManageStaffController;
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

    @FXML
    public void manageStore(ActionEvent actionEvent) {
        // TODO:damn
    }

    @FXML
    public void manageAccount(ActionEvent actionEvent) throws IOException {
        jumpTo(ManageBankAccountController.launch(initializationBLService));
    }

    @FXML
    public void manageInstitution(ActionEvent actionEvent) throws IOException {
        jumpTo(ManageOrganizationController.launch(initializationBLService, initializationBLService));
    }

    @FXML
    public void manageCars(ActionEvent actionEvent) throws IOException {
        jumpTo(ManageCarDriverController.launch(initializationBLService));
    }

    @FXML
    public void manageStaff(ActionEvent actionEvent) throws IOException {
        jumpTo(ManageStaffController.launch(initializationBLService));
    }

    @FXML
    public void saveDraft(ActionEvent actionEvent) {
    }

    @FXML
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

    @FXML
    public void commit(ActionEvent actionEvent) {
        initializationBLService.uploadInitialData();
        father.getChildren().clear();
        try {
            father.getChildren().add(CheckInitInfoController.launch(father));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jumpTo(Node pane){
        father.getChildren().clear();
        father.getChildren().add(pane);
    }
}
