package ui.manangeui.organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import po.InfoEnum;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import ui.manangeui.staff.ManageStaffController;
import vo.configurationvo.City2DVO;
import vo.configurationvo.ConfigurationVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** 
 * Client//ui.manangeui.organization//ManageOrganizationController.java
 * @author CXWorks
 * @date 2015年12月8日 上午8:51:57
 * @version 1.0 
 */
public class ManageOrganizationController implements ChangeListener<InstitutionVO>{
	public Label institutionType;
	public Label ID;
	public TextField area;
	public TextField nearCenter;
	public Label areaLabel;
	public Label nearCenterLabel;
	
	public ChoiceBox<String> cityChoiceBox;
	public Label cityID;
	
	public InstitutionVO institutionVO;
	
	public ArrayList<InstitutionVO> institutionVOs;
	
	public TableView<InstitutionVO> tableView;
	public TableColumn<InstitutionVO, String> cityColumn;
	public TableColumn<InstitutionVO, String> typecColumn;
	public TableColumn<InstitutionVO, String> institutionIDColumn;
	public boolean isNew=false;
	public Button back_Btn;
	private ManageblHallService manageblHallService;
	private ManageblCenterService manageblCenterService;
	private ConfigurationBLService configurationBLService;
    private TabPane outsideTabPane;
    private Tab staffTab;
    private ManageStaffController staffController;
    private ArrayList<City2DVO> cities;
    private String selectedCityID;
	
	public static Parent launch
			(Pane father, Pane before, TabPane outsideTabPane, Tab staffTab, ManageStaffController staffController,
			 ManageblHallService hallService, ManageblCenterService centerService,ConfigurationBLService configurationBLService) throws IOException
    {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(ManageOrganizationController.class.getResource("manageOrganization.fxml"));
		Pane pane = fxmlLoader.load();

        ManageOrganizationController controller = fxmlLoader.getController();
        controller.manageblHallService = hallService;
        controller.manageblCenterService = centerService;
        controller.configurationBLService = configurationBLService;
        controller.outsideTabPane = outsideTabPane;
        controller.staffTab = staffTab;
        controller.staffController = staffController;
        controller.continueInit();

		if(father == null){
			pane.getChildren().remove(controller.back_Btn);
		}else{
			controller.back_Btn.setOnAction(
					o -> {
						father.getChildren().clear();
						father.getChildren().add(before);}
			);
		}
		
		return pane;
	}
	
	public void continueInit(){
		this.initCityChoice();
		institutionVOs=this.getInstitutionVOs();
		tableView.setItems(FXCollections.observableList(institutionVOs));
		tableView.getSelectionModel().selectedItemProperty().addListener(this);
		tableView.getSelectionModel().selectFirst();
		institutionVO=tableView.getSelectionModel().getSelectedItem();
		
	}
	
	public void initialize(){
        // TODO test jump
		
		cityColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getCity()));
		typecColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getInfoEnum().getChinese()));
		institutionIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getInstitutionID()));
		//
		this.center_textfield();
	}
	private InstitutionVO makeInstitutionVO(){
		if (institutionType.getText()=="中转中心") {
			return new CenterVO(manageblCenterService.newCenterID(cityID.getText()), cityID.getText());
		}
		else {
			return new HallVO(manageblHallService.newHallID(nearCenter.getText()), cityID.getText(), area.getText(), nearCenter.getText());
		}
	}
	private void initCityChoice(){
		this.refreshCityData();
		cityChoiceBox.setItems(FXCollections
				.observableList(cities.stream()
						.map(city->city.getName()).collect(Collectors.toList())));
		cityChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(obser,old,New)->{
					selectedCityID=cities.stream()
							.filter(city->{return city.getName().equalsIgnoreCase(New);})
							.findFirst().get().getID();
				cityID.setText(selectedCityID);
				});
		//
		
	}
	
	private void refreshCityData(){
		ArrayList<ConfigurationVO> temp=configurationBLService.get(InfoEnum.CITY_2D);
		cities=new ArrayList<City2DVO>(temp.size());
		for (ConfigurationVO configurationVO : temp) {
			cities.add((City2DVO)configurationVO);
		}
	}
	
	public void newCenter(){
		this.clear();
		this.institutionType.setText(InfoEnum.CENTER.getChinese());
		this.center_textfield();
		isNew=true;
	}
	public void newHall(){
		this.clear();
		this.institutionType.setText(InfoEnum.HALL.getChinese());
		this.hall_textfield();
		isNew=true;
	}
	public void clear(){
		this.ID.setText(null);
		this.institutionType.setText(null);
		this.area.clear();
		this.cityChoiceBox.getSelectionModel().clearAndSelect(0);
		this.nearCenter.clear();
	}
	public void sure(){
		if (isNew) {
			institutionVO =this.makeInstitutionVO();
			if (institutionVO.getInfoEnum()==InfoEnum.HALL) {
				manageblHallService.addHall((HallVO) institutionVO);
			}
			else {
				manageblCenterService.addCenter((CenterVO) institutionVO);
			}
			isNew=false;
		}
		else {
			institutionVO.setCity(cityID.getText());
			if (institutionVO.getInfoEnum()==InfoEnum.HALL) {
				((HallVO) institutionVO).setArea(area.getText());
			}
			if (institutionVO.getInfoEnum()==InfoEnum.HALL) {
				manageblHallService.modifyHall((HallVO) institutionVO);
			}
			else {
				manageblCenterService.modifyCenter((CenterVO) institutionVO);
			}
		}
		this.clear();
		this.refreshTable();
	}

	public void refreshTable(){
		institutionVOs=this.getInstitutionVOs();
		this.tableView.setItems(FXCollections.observableList(institutionVOs));
	}

	public void manageStaff(){
        outsideTabPane.getSelectionModel().select(staffTab);
        staffController.initLabel(institutionVO);
	}
	
	private ArrayList<InstitutionVO> getInstitutionVOs(){
		
		ArrayList<CenterVO> centerVOs=manageblCenterService.getCenter();
		ArrayList<HallVO> hallVOs=manageblHallService.getHall();
		ArrayList<InstitutionVO> ans=new ArrayList<InstitutionVO>(centerVOs.size()+hallVOs.size());
		for (int i = 0; i < centerVOs.size(); i++) {
			ans.add((InstitutionVO)centerVOs.get(i));
		}
		for (int i = 0; i < hallVOs.size(); i++) {
			ans.add((InstitutionVO)hallVOs.get(i));
		}
		return ans;
	}


	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends InstitutionVO> observable,
			InstitutionVO oldValue, InstitutionVO newValue) {
		institutionVO =newValue;
		this.setText(institutionVO);
	}
	private void setText(InstitutionVO src){
		this.clear();
		if (src==null) {
			return;
		}
		ID.setText(src.getInstitutionID());
		institutionType.setText(src.getInfoEnum().getChinese());
		int index=0;
		while (index<cities.size()) {
			if (cities.get(index).getName().equalsIgnoreCase(src.getCity())) {
				break;
			}
			index++;
		}
		cityChoiceBox.getSelectionModel().clearAndSelect(index);
		if (src.getInfoEnum()==InfoEnum.HALL) {
			this.hall_textfield();
			area.setText(((HallVO)src).getArea());
		}
		else {
			this.center_textfield();
		}
	}
	//
	private void hall_textfield(){
		areaLabel.setVisible(true);
		nearCenterLabel.setVisible(true);
		area.setVisible(true);
		nearCenter.setVisible(true);
	}
	private void center_textfield(){
		areaLabel.setVisible(false);
		nearCenterLabel.setVisible(false);
		area.setVisible(false);
		nearCenter.setVisible(false);
	}
}
