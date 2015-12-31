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
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.FormVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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

    private FormBLService[] formBLServices;
    private InformController informController;
    private FormVO selectedFormVO;
    
    private FormatCheckQueue formatCheckQueueNotNull;

    public static Pane launch(FormBLService...formBLServices){
        try {
            FXMLLoader loader = new FXMLLoader(FormHistoryController.class.getResource("formHistory.fxml"));
            Pane pane = loader.load();

            FormHistoryController controller = loader.getController();
            controller.formBLServices = formBLServices;
            controller.informController = InformController.newInformController(pane);
            controller.search(null);

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
        //inti check
        formatCheckQueueNotNull=new FormatCheckQueue();
        formatCheckQueueNotNull.addTasker(new CheckIsNullTasker(formID_Field));
    }

    private void fillTable(Collection<FormVO> forms){
        forms_TableView.getItems().clear();
        forms_TableView.getItems().addAll(forms);
    }

    public void search(ActionEvent actionEvent) {
    	//check
    	if (!formatCheckQueueNotNull.startCheck()) {
			return ;
		}
        String filters = formID_Field.getText();
        ArrayList<FormVO> formVOs = new ArrayList<>();
        for (FormBLService formBLService : formBLServices) {
            formVOs.addAll(formBLService.getHistory(UserInfo.getUserID()));
        }
        for (int i = 0; i < formVOs.size(); i++) {
            FormVO formVO = formVOs.get(i);
            if(!formVO.getFormID().contains(filters)){
                formVOs.remove(formVO);
                --i;
            }
        }
        fillTable(formVOs);
    }

    public void viewDetail(ActionEvent actionEvent) {
        if(selectedFormVO == null){
            informController.inform("请选择一个表单");
        }else{
            Stage stage = ViewFormDetailController.launchInHistory(selectedFormVO);
            stage.show();
        }
    }
}
