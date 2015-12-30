package ui.manangeui.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import bl.blService.manageblService.ManageblStaffService;
import factory.InitBLFactory;
import factory.InstitutionFactory;
import factory.StaffFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import po.InfoEnum;
import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;
import vo.managevo.staff.StaffVO;

public class ManageStaffController  {
	public TextField institution_Field;
	public Label city_Label;
	public Label area_Label;

	public TableView<StaffVO> staff_TableView;
	public TableColumn<StaffVO, String> type_TableColumn;
	public TableColumn<StaffVO, String> ID_TableColumn;
	public TableColumn<StaffVO, String> name_TableColumn;
	public TableColumn<StaffVO, String> gender_TableColumn;
	public TableColumn<StaffVO, String> age_TableColumn;
	public TableColumn<StaffVO, String> institution_TableColumn;
	public Button back_Btn;
	public TableColumn<StaffVO, String> personID_TableColumn;
	public TableColumn<StaffVO, String> love_TableColumn;
	//
    private ManageblHallService manageblHallService;
    private ManageblCenterService manageblCenterService;
	private ManageblStaffService manageblStaffService;
	private Pane selfPane;
	private InformController informController;
    private String institutionID;

    private static ManageStaffController launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ManageStaffController.class.getResource("manageStaff.fxml"));
        Pane selfPane = fxmlLoader.load();

        ManageStaffController controller = fxmlLoader.getController();
        controller.informController = InformController.newInformController(selfPane);
        controller.selfPane = controller.informController.stackPane;

        return controller;
    }

	public static ManageStaffController launchInInit(Pane father, Pane before)
			throws IOException {
        ManageStaffController controller = ManageStaffController.launch();
        controller.back_Btn.setOnAction(actionEvent -> {
            father.getChildren().clear();
            father.getChildren().add(before);
        });
        controller.manageblCenterService = InitBLFactory.getInitializationBLService();
        controller.manageblStaffService = InitBLFactory.getInitializationBLService();
        controller.manageblHallService = InitBLFactory.getInitializationBLService();

		return controller;
	}

    public static ManageStaffController launchInManage() throws IOException {
        ManageStaffController controller = ManageStaffController.launch();
        controller.selfPane.getChildren().remove(controller.back_Btn);
        controller.back_Btn.setVisible(false);
        controller.manageblStaffService = StaffFactory.getManageService();
        controller.manageblHallService = InstitutionFactory.getManageblHallService();
        controller.manageblCenterService = InstitutionFactory.getManageblCenterService();

        return controller;
    }

	@FXML
	public void initialize() {
		type_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getStaff().getChinese())
		);
		ID_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getID())
		);
		name_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getName())
		);
        gender_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getSex().toString())
        );
		age_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getAge()))
        );
		institution_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getInstitutionID())
		);
		personID_TableColumn.setCellValueFactory(
				cell -> new SimpleStringProperty(cell.getValue().getPersonID())
		);
		love_TableColumn.setCellValueFactory(
				cell -> new SimpleStringProperty(cell.getValue().getLove())
		);

	}

	@FXML
	public void fillStaffTable() {
        fillStaffTable(institution_Field.getText());
	}

    public void fillStaffTable(String institutionID){
        InstitutionVO vo = manageblCenterService.searchCenter(institutionID);
        if(vo == null){ // not in center
            vo = manageblHallService.searchHall(institution_Field.getText());
        }
        if(vo == null){ // neither in center nor in hall
            informController.inform("请输入正确的机构编号");
            return;
        }else{
            this.institutionID = vo.getInstitutionID();
            initLabel(vo);
            List<StaffVO> staffVOs = manageblStaffService.getStaffByInstitution(vo.getInstitutionID());
            this.staff_TableView.setItems(FXCollections.observableList(staffVOs));
        }
    }

    public void fillStaffTableWithVO(InstitutionVO vo){
        if(vo != null){
            initLabel(vo);
            List<StaffVO> staffVOs = manageblStaffService.getStaffByInstitution(vo.getInstitutionID());
            this.staff_TableView.setItems(FXCollections.observableList(staffVOs));
        }else{
            System.err.println("wrong in fillStaffTableWithVO : input vo is null");
        }
    }

	private void initLabel(InstitutionVO institutionVO) {
		if (institutionVO.getInfoEnum() == InfoEnum.CENTER) {
			CenterVO centerVO = (CenterVO) institutionVO;
			this.setLabel(centerVO.getCenterID(), centerVO.getCityID(), null);
		} else {
			HallVO hallVO = (HallVO) institutionVO;
			this.setLabel(hallVO.getHallID(), hallVO.getCityID(), hallVO.getArea());
		}
	}

	private void setLabel(String ID, String city, String area) {
        institution_Field.setText(ID);
		this.city_Label.setText(city);
		if (area != null) {
			this.area_Label.setText(area);
		}else{
            this.area_Label.setText("XX");
        }
	}

	public Pane getSelfPane() {
		return selfPane;
	}

    public void newStaff(ActionEvent actionEvent) {
        try {
            Stage dialog = EditStaffDialogController.newDialog(null, EditStaffDialogController.EditType.NEW);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillStaffTable(institutionID);
    }

    public void editStaff(ActionEvent actionEvent) {
        StaffVO staffVO = staff_TableView.getSelectionModel().getSelectedItem();
        try {
            Stage dialog = EditStaffDialogController.newDialog(staffVO, EditStaffDialogController.EditType.EDIT);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillStaffTable(institutionID);
    }

    public void deleteStaff(ActionEvent actionEvent) {
        StaffVO staffVO = staff_TableView.getSelectionModel().getSelectedItem();
        OperationMessage msg = manageblStaffService.dismissStaff(staffVO);
        informController.inform(msg, "删除成功");
    }
}