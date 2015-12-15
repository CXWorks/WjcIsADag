package ui.manangeui.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import po.InfoEnum;
import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import factory.StaffFactory;
import bl.blService.manageblService.ManageblStaffService;
import tool.ui.StaffVO2ColumnHelper;
import userinfo.UserInfo;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;
import vo.managevo.staff.StaffVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class ManageStaffController implements ChangeListener<StaffVO>{
	public TextField staffType;
	public Label ID;
	public TextField name;
	public TextField age;
	public TextField sex;
	public TextField personID;
	public TextField love;
	public TextField institutionID;
	//
	public Label institution;
	public Label city;
	public Label area;

	public TableView<StaffVO> staffTable;
	public TableColumn<StaffVO, String> typeColumn;
    public TableColumn<StaffVO, String> IDColumn;
    public TableColumn<StaffVO, String> nameColumn;
    public TableColumn<StaffVO, String> sexcColumn;
    public TableColumn<StaffVO, String> ageColumn;
    public TableColumn<StaffVO, String> institutionColumn;
    public Button back_Btn;
    //
    private ManageblStaffService manageblStaffService;
    private ArrayList<StaffVO> staffVOs;
    private boolean isNew = false;
    private Pane selfPane;

    @FXML
    public static ManageStaffController launch(Pane father, Pane before, ManageblStaffService service) throws IOException{
    	FXMLLoader fxmlLoader=new FXMLLoader();
    	fxmlLoader.setLocation(ManageStaffController.class.getResource("manageStaff.fxml"));
        Pane selfPane = fxmlLoader.load();

		ManageStaffController controller = fxmlLoader.getController();
		controller.manageblStaffService = service;
        controller.selfPane = selfPane;

		if(father == null){
            selfPane.getChildren().remove(controller.back_Btn);
		}else{
			controller.back_Btn.setOnAction(
					actionEvent -> {
						father.getChildren().clear();
						father.getChildren().add(before);}
			);
		}

		return controller;
    }
	@FXML
	public void initialize(){
		typeColumn.setCellValueFactory(
				cellData->new SimpleStringProperty(cellData.getValue().getStaff().getChinese()));
		IDColumn.setCellValueFactory(
				cellData->new SimpleStringProperty(cellData.getValue().getID()));
		nameColumn.setCellValueFactory(
				cellData->new SimpleStringProperty(cellData.getValue().getName()));

		sexcColumn.setCellValueFactory(
				cellData->new SimpleStringProperty(cellData.getValue().getSex().toString()));
		ageColumn.setCellValueFactory(
				cellData->new SimpleStringProperty(Integer.toString(cellData.getValue().getAge())));
		institutionColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getInstitutionID()));
		//
		staffTable.getSelectionModel().selectedItemProperty().addListener(this);

	}
	//
	public void fillStaffTable(){
		staffVOs=manageblStaffService.getStaffByInstitution(UserInfo.getInstitutionID());
		this.staffTable.setItems(FXCollections.observableList(staffVOs));
	}
	//
	public void submit(){
		StaffVO staffVO=this.makeNewStaff();
		if (staffVO==null) {
			return;
		}
		if (isNew) {
			manageblStaffService.addStaff(staffVO);
		} else {
			manageblStaffService.modifyStaff(staffVO);
		}
		this.fillStaffTable();
	}
	
	
	public void cancel(){
		staffType.clear();
		ID.setText(null);
		name.clear();
		age.clear();
		sex.clear();
		personID.clear();
		love.clear();
		institutionID.clear();

	}
	//
	public void newStaff(){
		this.cancel();
		this.isNew=true;
	}

	public void deleteStaff(){
		StaffVO staffVO=this.makeStaff();
		if (staffVO==null) {
			return;
		}
		this.cancel();
		manageblStaffService.dismissStaff(staffVO);
		this.fillStaffTable();
	}
	private StaffVO makeStaff(){
		String nType=staffType.getText();
		String nID=ID.getText();
		String nName=name.getText();
		String nAge=age.getText();
		String nSex=sex.getText();
		String nPersonID=personID.getText();
		String nLove=love.getText();
		String nInstitutionID=institutionID.getText();
		//
		if (nType.length()*nName.length()*nAge.length()*nSex.length()*nPersonID.length()*nLove.length()*nInstitutionID.length()==0) {
			return null;
		}
		StaffVO ans= new StaffVO(null, nID, nName, Integer.parseInt(nAge), nPersonID, null, nLove, nInstitutionID);
		ans.setStaff(nType);
		ans.setSex(nSex);
		return ans;
	}
	private StaffVO makeNewStaff(){
		String nType=staffType.getText();
		String nID=ID.getText();
		String nName=name.getText();
		String nAge=age.getText();
		String nSex=sex.getText();
		String nPersonID=personID.getText();
		String nLove=love.getText();
		String nInstitutionID=institutionID.getText();
		//
		if (nType.length()*nName.length()*nAge.length()*nSex.length()*nPersonID.length()*nLove.length()*nInstitutionID.length()==0) {
			return null;
		}
		//
		StaffVO staffVO=new StaffVO(StaffTypeEnum.ADMINISTRATOR, null, nName, Integer.parseInt(nAge), nPersonID, SexEnum.MAN, nLove, nInstitutionID);
		staffVO.setStaff(nType);
		staffVO.setSex(nSex);
		staffVO.setID(manageblStaffService.newStaffID(staffVO.getStaff(), staffVO.getInstitutionID()));
		return staffVO;
	}
	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends StaffVO> observable,
			StaffVO oldValue, StaffVO newValue) {
		this.setTextArea(newValue);

	}
	
	private void setTextArea(StaffVO src){
		this.cancel();
		if (src==null) {
			return;
		}
		this.staffType.setText(src.getStaff().getChinese());
		this.age.setText(Integer.toString(src.getAge()));
		this.ID.setText(src.getID());
		this.institutionID.setText(src.getInstitutionID());
		this.love.setText(src.getLove());
		this.name.setText(src.getName());
		this.personID.setText(src.getPersonID());
		this.sex.setText(src.getSex().getChinese());
	}
	
	public void initLabel(InstitutionVO institutionVO){
		if (institutionVO.getInfoEnum()==InfoEnum.CENTER) {
			CenterVO centerVO=(CenterVO)institutionVO;
			this.setLabel(centerVO.getCenterID(), centerVO.getCity(), null);
		}
		else {
			HallVO hallVO=(HallVO)institutionVO;
			this.setLabel(hallVO.getHallID(), hallVO.getCity(), hallVO.getArea());
		}
	}
	
	private void setLabel(String ID,String city,String area){
		institution.setText(ID);
		this.city.setText(city);
		if (area!=null) {
			this.area.setText(area);
		}
	}

    public Pane getSelfPane() {
        return selfPane;
    }
}
