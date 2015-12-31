package ui.manangeui.staff;

import bl.blService.manageblService.ManageblStaffService;
import factory.StaffFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import message.OperationMessage;
import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.informui.InformController;
import vo.managevo.staff.StaffVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/27.
 */
public class EditStaffDialogController {
    public enum EditType{
        NEW,
        EDIT
    }

    public TextField name_Field;
    public TextField institutionID_Field;
    public ChoiceBox<SimpleEnumProperty<StaffTypeEnum>> staffType_ChoiceBox;
    public ChoiceBox<SimpleEnumProperty<SexEnum>> sex_ChoiceBox;
    public TextField personID_Field;
    public TextField love_Field;
    public TextField age_Field;

    private StaffVO staffVO;
    private Stage dialog;
    private ManageblStaffService manageblStaffService = StaffFactory.getManageService();
    private EditType editType;
    private InformController informController;
    
    private FormatCheckQueue  formatCheckQueue;

    public static Stage newDialog(StaffVO staffVO, EditType editType) throws IOException {
        FXMLLoader loader = new FXMLLoader(EditStaffDialogController.class.getResource("editStaffDialog.fxml"));
        Pane pane = loader.load();

        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setTitle(
                editType == EditType.EDIT ? "修改账户" : "新建账户"
        );
        
        dialog.initOwner(Main.primaryStage);

        EditStaffDialogController controller = loader.getController();
        controller.staffVO = staffVO == null ? new StaffVO() : staffVO;
        controller.dialog = dialog;
        controller.editType = editType;
        controller.informController = InformController.newInformController(pane);
        controller.init();

        dialog.setScene(new Scene(controller.informController.stackPane));

        return dialog;
    }

    @FXML
    public void initialize(){
        staffType_ChoiceBox.setItems(Enum2ObservableList.transit(StaffTypeEnum.values()));
        sex_ChoiceBox.setItems(Enum2ObservableList.transit(SexEnum.values()));
    }

    private void init(){
    	
        if(editType == EditType.EDIT){
            name_Field.setText(staffVO.getName());
            institutionID_Field.setText(staffVO.getInstitutionID());
            age_Field.setText(staffVO.getAge()+"");
            personID_Field.setText(staffVO.getPersonID());
            love_Field.setText(staffVO.getLove());

            for (SimpleEnumProperty<StaffTypeEnum> choicItem : staffType_ChoiceBox.getItems()) {
                if(choicItem.getEnum() == staffVO.getStaff()){
                    staffType_ChoiceBox.getSelectionModel().select(choicItem);
                }
            }
            for (SimpleEnumProperty<SexEnum> choiceItem : sex_ChoiceBox.getItems()) {
                if(choiceItem.getEnum() == staffVO.getSex()){
                    sex_ChoiceBox.getSelectionModel().select(choiceItem);
                }
            }
        }

        name_Field.requestFocus();
        
        //init check
        formatCheckQueue=new FormatCheckQueue();
        formatCheckQueue.addTasker(
        		new CheckIsNullTasker(age_Field),
        		new CheckIsNullTasker(institutionID_Field),
        		new CheckIsNullTasker(love_Field),
        		new CheckIsNullTasker(name_Field),
        		new CheckIsNullTasker(personID_Field)
        		);
    }

    public void cancel(ActionEvent actionEvent) {
        dialog.close();
    }

    public void commit(ActionEvent actionEvent) {
    	//check
    	if (!formatCheckQueue.startCheck()) {
			return;
		}
        staffVO.setStaff(staffType_ChoiceBox.getSelectionModel().getSelectedItem().getEnum());
        staffVO.setName(name_Field.getText());
        staffVO.setSex(sex_ChoiceBox.getSelectionModel().getSelectedItem().getEnum());
        staffVO.setInstitutionID(institutionID_Field.getText());
        int age = Integer.parseInt(age_Field.getText());
        staffVO.setAge(age);
        staffVO.setPersonID(personID_Field.getText());
        staffVO.setLove(love_Field.getText());

        OperationMessage msg;
        if(editType == EditType.EDIT){
            msg = manageblStaffService.modifyStaff(staffVO);
        }else{
            msg = manageblStaffService.addStaff(staffVO);
        }

        if(msg.operationResult){
            informController.inform("账户修改成功");
            System.out.println("commit successfully");
        }else{
            informController.inform("账户修改失败: " + msg.getReason());
            System.out.println("fail: " + msg.getReason());
        }

        dialog.close();
    }
}
