package ui.manangeui.organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import factory.ConfigurationFactory;
import factory.InitBLFactory;
import factory.InstitutionFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import javafx.stage.Stage;
import po.InfoEnum;
import ui.common.MainPaneController;
import ui.manangeui.staff.ManageStaffController;
import vo.configurationvo.City2DVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** 
 * Client//ui.manangeui.organization//ManageOrganizationController.java
 * @author CXWorks
 * @date 2015年12月8日 上午8:51:57
 * @version 1.0 
 */
public class ManageOrganizationController {
	public Label type_Label;
	public Label id_Label;
	public Label area_Label;
	public Label nearCenter_Label;
	public TableView<InstitutionVO> tableView;
	public TableColumn<InstitutionVO, String> cityColumn;
	public TableColumn<InstitutionVO, String> typecColumn;
	public TableColumn<InstitutionVO, String> institutionIDColumn;
	public Button back_Btn;
	public Label area_HeadLabel;
	public Label nearCenter_HeadLabel;
	public Label city_Label;
	public HBox buttons_HBox;
	public Button manageStaff_Btn;

	private Pane selfPane;
	private InstitutionVO institutionVO;
	private ArrayList<InstitutionVO> institutionVOs;

	private ManageblHallService manageblHallService;
	private ManageblCenterService manageblCenterService;
    private ConfigurationBLService configurationBLService;

	private MainPaneController mpc;
	private ManageStaffController staffController;

	public static ManageOrganizationController launch() {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(ManageOrganizationController.class.getResource("manageOrganization.fxml"));
		try {
			Pane pane = fxmlLoader.load();
			ManageOrganizationController controller = fxmlLoader.getController();
			controller.selfPane = pane;
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Parent launchInInit(Pane father, Pane before){
		ManageOrganizationController controller = ManageOrganizationController.launch();
		controller.back_Btn.setOnAction(
                o -> {
                    father.getChildren().clear();
                    father.getChildren().add(before);
                }
        );
		controller.buttons_HBox.getChildren().remove(controller.manageStaff_Btn);
		controller.manageStaff_Btn.setVisible(false);

		controller.manageblCenterService = InitBLFactory.getInitializationBLService();
		controller.manageblHallService = InitBLFactory.getInitializationBLService();
        controller.configurationBLService = InitBLFactory.getInitializationBLService();

		controller.continueInit();

		return controller.selfPane;
	}

	public static Parent launchInManager(ManageStaffController staffController, MainPaneController mpc){
		ManageOrganizationController controller = ManageOrganizationController.launch();
		controller.selfPane.getChildren().remove(controller.back_Btn);
		controller.back_Btn.setVisible(false);

		controller.staffController = staffController;
		controller.mpc = mpc;
		controller.manageblCenterService = InstitutionFactory.getManageblCenterService();
		controller.manageblHallService = InstitutionFactory.getManageblHallService();
        controller.configurationBLService = ConfigurationFactory.getConfigurationBLService();

		controller.continueInit();

		return controller.selfPane;
	}
	
	public void continueInit(){
		institutionVOs = this.getInstitutionVOs();
		tableView.setItems(FXCollections.observableList(institutionVOs));

		tableView.getSelectionModel().selectFirst();
		institutionVO = tableView.getSelectionModel().getSelectedItem();
        if(tableView.getItems().size() != 0){
            showDetail();
        }
	}
	
	public void initialize(){
		cityColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCityName()));
		typecColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getInfoEnum().getChinese()));
		institutionIDColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getInstitutionID()));
		tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    institutionVO = newValue;
                    showDetail();
                }
        );
	}

	public void refreshTable(){
		institutionVOs = this.getInstitutionVOs();
		this.tableView.setItems(FXCollections.observableList(institutionVOs));
	}

	public void manageStaff(){
        mpc.jumpTo(staffController.getSelfPane());
        staffController.fillStaffTableWithVO(institutionVO);
	}

	private ArrayList<InstitutionVO> getInstitutionVOs(){
		ArrayList<CenterVO> centerVOs = manageblCenterService.getCenter();
		ArrayList<HallVO> hallVOs = manageblHallService.getHall();
		ArrayList<InstitutionVO> ans=new ArrayList<InstitutionVO>(centerVOs.size()+hallVOs.size());
        for (CenterVO centerVO : centerVOs) {
            ans.add((InstitutionVO) centerVO);
        }
        for (HallVO hallVO : hallVOs) {
            ans.add((InstitutionVO) hallVO);
        }
		return ans;
	}

	private void showDetail(){
		boolean isHall = institutionVO.getInfoEnum() == InfoEnum.HALL;
		area_HeadLabel.setVisible(isHall);
		area_Label.setVisible(isHall);
		nearCenter_HeadLabel.setVisible(isHall);
		nearCenter_Label.setVisible(isHall);

		city_Label.setText(institutionVO.getCityName());
		type_Label.setText(institutionVO.getInfoEnum().getChinese());
		id_Label.setText(institutionVO.getInstitutionID());

		if(isHall){
			area_Label.setText(((HallVO)institutionVO).getArea());
			nearCenter_Label.setText(((HallVO)institutionVO).getNearCenterID());
		}
	}

	public void modifyOrganization(ActionEvent actionEvent) {
        Stage dialog = EditOrganizationDialogController
                .newDialog(institutionVO, EditOrganizationDialogController.EditType.EDIT,
                        configurationBLService, manageblHallService, manageblCenterService);
        dialog.showAndWait();
        refreshTable();
	}

	public void newOrganization(ActionEvent actionEvent) {
        Stage dialog = EditOrganizationDialogController
                .newDialog(null, EditOrganizationDialogController.EditType.NEW,
                        configurationBLService, manageblHallService, manageblCenterService);
        dialog.showAndWait();
        refreshTable();
	}
}
