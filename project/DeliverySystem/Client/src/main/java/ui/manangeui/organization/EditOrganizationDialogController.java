package ui.manangeui.organization;

import bl.blService.configurationblService.ConfigurationBLService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import factory.ConfigurationFactory;
import factory.InitBLFactory;
import factory.InstitutionFactory;
import javafx.collections.FXCollections;
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
import po.InfoEnum;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
import ui.financeui.AccountEditDialogController;
import ui.informui.InformController;
import vo.configurationvo.City2DVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/30.
 */
public class EditOrganizationDialogController {
    public enum EditType{
        NEW,
        EDIT
    }

    private static final String HallChoice = "营业厅";
    private static final String CenterChoice = "中转中心";

    public TextField area_Field;
    public ChoiceBox<String> type_ChoiceBox;
    public ChoiceBox<City2DVO> city_ChoiceBox;

    private ConfigurationBLService configurationBLService;
    private ManageblHallService manageblHallService;
    private ManageblCenterService manageblCenterService;
    private InstitutionVO institutionVO;
    private Stage dialog;
    private EditType editType;
    private InformController informController;
    
    private FormatCheckQueue formatCheckQueue;

    public static Stage newDialog
            (InstitutionVO institutionVO, EditType editType,
             ConfigurationBLService configurationBLService, ManageblHallService manageblHallService, ManageblCenterService manageblCenterService){
        FXMLLoader loader = new FXMLLoader(EditOrganizationDialogController.class.getResource("editOrganizationDialog.fxml"));
        try {
            Pane pane = loader.load();

            Stage stage = new Stage();
            stage.initOwner(Main.primaryStage);
            stage.initStyle(StageStyle.UNDECORATED);
            
            EditOrganizationDialogController controller = loader.getController();
            controller.dialog = stage;
            controller.institutionVO = institutionVO;
            controller.editType = editType;
            controller.informController = InformController.newInformController(pane);
            stage.setScene(new Scene(controller.informController.stackPane));
            controller.configurationBLService = configurationBLService;
            controller.manageblCenterService = manageblCenterService;
            controller.manageblHallService = manageblHallService;
            controller.init();

            return stage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    public void initialize(){
        type_ChoiceBox.setItems(FXCollections.observableArrayList(
                new String[] {HallChoice, CenterChoice}
        ));
        type_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(HallChoice.equals(newValue)){
                        area_Field.setEditable(true);
                    }else if(CenterChoice.equals(newValue)){
                        area_Field.clear();
                        area_Field.setEditable(false);
                    }
                }
        );
        
        //init check
        formatCheckQueue=new FormatCheckQueue();
        formatCheckQueue.addTasker(new CheckIsNullTasker(area_Field));
    }

    public void init(){
        city_ChoiceBox.setItems(FXCollections.observableArrayList(configurationBLService.getCity()));
        if(editType == EditType.EDIT){
            // set city choiceBox
            for (City2DVO city2DVO : city_ChoiceBox.getItems()) {
                if(city2DVO.getName().equals(institutionVO.getCityID())){
                    city_ChoiceBox.getSelectionModel().select(city2DVO);
                }
            }
            // set type choiceBox
            if(institutionVO.getInfoEnum() == InfoEnum.CENTER){
                type_ChoiceBox.getSelectionModel().select(CenterChoice);
            }else {
                type_ChoiceBox.getSelectionModel().select(HallChoice);
                area_Field.setText(((HallVO)institutionVO).getArea());
            }
            // don't let user choose
            type_ChoiceBox.setDisable(true);
        }else {
            city_ChoiceBox.getSelectionModel().select(0);
            type_ChoiceBox.getSelectionModel().select(0);
        }
    }

    public void cancel(ActionEvent actionEvent) {
        dialog.close();
    }

    public void commit(ActionEvent actionEvent) {
    	//check
    	if (!formatCheckQueue.startCheck()) {
			return;
		}
        OperationMessage msg;
        if(type_ChoiceBox.getSelectionModel().getSelectedItem().equals(HallChoice)){
            if(editType == EditType.EDIT){
                ((HallVO)institutionVO).setArea(area_Field.getText());
                institutionVO.setCityID(city_ChoiceBox.getSelectionModel().getSelectedItem().getID());
                msg = manageblHallService.modifyHall((HallVO)institutionVO);
            }else {
                HallVO hallVO = new HallVO();
                hallVO.setArea(area_Field.getText());
                hallVO.setCityID(city_ChoiceBox.getSelectionModel().getSelectedItem().getID());
                hallVO.setNearCenterID(manageblHallService.nearCenterID(hallVO.getCityID()));
                hallVO.setCityName(city_ChoiceBox.getSelectionModel().getSelectedItem().getName());
                msg = manageblHallService.addHall(hallVO);
            }
        }else{
            if(editType == EditType.EDIT){
                institutionVO.setCityID(city_ChoiceBox.getSelectionModel().getSelectedItem().getID());
                msg = manageblCenterService.addCenter((CenterVO)institutionVO);
            }else {
                CenterVO centerVO = new CenterVO();
                centerVO.setCityID(city_ChoiceBox.getSelectionModel().getSelectedItem().getID());
                centerVO.setCityName(city_ChoiceBox.getSelectionModel().getSelectedItem().getName());
                msg = manageblCenterService.addCenter(centerVO);
            }
        }

        informController.inform(msg, "修改机构成功");

        dialog.close();
    }
}
