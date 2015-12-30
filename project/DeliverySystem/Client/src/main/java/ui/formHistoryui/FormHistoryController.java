package ui.formHistoryui;

import bl.blService.FormBLService;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.FormVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sissel on 2015/12/30.
 */
public class FormHistoryController {
    public TableView<FormVO> forms_TableView;
    public TableColumn<FormVO, String> formID_TableColumn;
    public TableColumn<FormVO, String> formType_TableColumn;
    public TableColumn<FormVO, String> formState_TableColumn;
    public TableColumn<FormVO, String> creator_TableColumn;
    public TextField formID_Field;

    private FormBLService formBLService;
    private InformController informController;
    private FormVO selectedFormVO;

    public static Pane launch(FormBLService formBLService){
        try {
            FXMLLoader loader = new FXMLLoader(FormHistoryController.class.getResource("formHistory.fxml"));
            Pane pane = loader.load();

            FormHistoryController controller = loader.getController();
            controller.formBLService = formBLService;
            controller.informController = InformController.newInformController(pane);
            controller.fillTable(formBLService.getHistory(UserInfo.getUserID()));

            return controller.informController.stackPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initialize(){
        creator_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCreaterID()));
        formType_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFormType().getChinese()));
        formID_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFormID()));
        formState_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getState().getChinese()));

        forms_TableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedFormVO = newValue
        );
    }

    private void fillTable(List<FormVO> forms){
        forms_TableView.getItems().clear();
        forms_TableView.getItems().addAll(forms);
    }

    public void search(ActionEvent actionEvent) {
        fillTable(formBLService.getHistory(UserInfo.getUserID()));
    }

    public void viewDetail(ActionEvent actionEvent) {
        if(selectedFormVO == null){
            informController.inform("请选择一个表单");
        }else{
            Stage stage = ViewFormDetailController.launch(selectedFormVO);
            stage.show();
        }
    }
}
