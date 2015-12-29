package ui.manangeui.staff;

import java.io.IOException;
import java.util.ArrayList;

import bl.blService.manageblService.ManageblStaffService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
	//
	private ManageblStaffService manageblStaffService;
	private ArrayList<StaffVO> staffVOs;
	private Pane selfPane;

	private InstitutionVO institutionVO;

	private InformController informController;

	@FXML
	public static ManageStaffController launch(Pane father, Pane before, ManageblStaffService service)
			throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(ManageStaffController.class.getResource("manageStaff.fxml"));
		Pane selfPane = fxmlLoader.load();

		ManageStaffController controller = fxmlLoader.getController();
		controller.manageblStaffService = service;

		controller.informController = InformController.newInformController(selfPane);
		controller.selfPane = controller.informController.stackPane;

		if (father == null) {
			selfPane.getChildren().remove(controller.back_Btn);
			controller.back_Btn.setVisible(false);
		} else {
			controller.back_Btn.setOnAction(actionEvent -> {
                father.getChildren().clear();
                father.getChildren().add(before);
            });
		}

		return controller;
	}

	@FXML
	public void initialize() {
		type_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaff().getChinese()));
		ID_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
		name_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        gender_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSex().toString()));
		age_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getAge())));
		institution_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstitutionID()));

	}

	//
	public void fillStaffTable() {
		if (this.institutionVO!=null) {
			staffVOs = manageblStaffService.getStaffByInstitution(this.institutionVO.getInstitutionID());
			this.staff_TableView.setItems(FXCollections.observableList(staffVOs));
		}

	}

	public void initLabel(InstitutionVO institutionVO) {
		this.institutionVO = institutionVO;
		if (institutionVO.getInfoEnum() == InfoEnum.CENTER) {
			CenterVO centerVO = (CenterVO) institutionVO;
			this.setLabel(centerVO.getCenterID(), centerVO.getCity(), null);
		} else {
			HallVO hallVO = (HallVO) institutionVO;
			this.setLabel(hallVO.getHallID(), hallVO.getCity(), hallVO.getArea());
		}
		this.fillStaffTable();
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
}