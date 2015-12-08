package ui.manangeui.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import po.InfoEnum;
import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import factory.StaffFactory;
import bl.blService.manageblService.ManageblStaffService;
import tool.ui.StaffVO2ColumnHelper;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;

public class ManageStaffController implements ChangeListener<StaffVO>{
	public TextField staffType;
	public TextField ID;
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
    //
    private ManageblStaffService manageblStaffService=StaffFactory.getManageService();
    private ArrayList<StaffVO> staffVOs;
    private boolean isNew=false;
    @FXML
    public static Parent launch() throws IOException{
    	FXMLLoader fxmlLoader=new FXMLLoader();
    	fxmlLoader.setLocation(ManageStaffController.class.getResource("manageStaff.fxml"));
    	return fxmlLoader.load();
    }
	@FXML
	public void initialize(){
		staffVOs=manageblStaffService.getStaffByInstitution();

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
				cellData->new SimpleStringProperty(cellData.getValue().getInstitutionID()));
		staffTable.setItems(FXCollections.observableList(staffVOs));
		//
		staffTable.getSelectionModel().selectedItemProperty().addListener(this);

	}
	//
	public void fillStaffTable(){
		staffVOs=manageblStaffService.getStaffByInstitution();
		this.staffTable.setItems(FXCollections.observableList(staffVOs));
	}
	//
	public void submit(){
		StaffVO staffVO=this.makeStaff();
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
		ID.clear();
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
		if (nType.length()*nID.length()*nName.length()*nAge.length()*nSex.length()*nPersonID.length()*nLove.length()*nInstitutionID.length()==0) {
			return null;
		}
		//
		StaffVO staffVO=new StaffVO(StaffTypeEnum.ADMINISTRATOR, nID, nName, Integer.parseInt(nAge), nPersonID, SexEnum.MAN, nLove, nInstitutionID);
		staffVO.setStaff(nType);
		staffVO.setSex(nSex);
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
}
