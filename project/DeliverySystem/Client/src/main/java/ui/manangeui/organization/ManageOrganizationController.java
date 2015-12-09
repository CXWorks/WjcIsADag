package ui.manangeui.organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import po.InfoEnum;
import factory.InstitutionFactory;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.institution.InstitutionVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/** 
 * Client//ui.manangeui.organization//ManageOrganizationController.java
 * @author CXWorks
 * @date 2015年12月8日 上午8:51:57
 * @version 1.0 
 */
public class ManageOrganizationController implements ChangeListener<InstitutionVO>{
	public TextField city;
	public Label institutionType;
	public Label ID;
	public TextField area;
	public TextField nearCenter;
	
	public InstitutionVO now;
	
	public ArrayList<InstitutionVO> institutionVOs;
	
	public TableView<InstitutionVO> tableView;
	public TableColumn<InstitutionVO, String> cityColumn;
	public TableColumn<InstitutionVO, String> typecColumn;
	public TableColumn<InstitutionVO, String> institutionIDColumn;
	public boolean isNew=false;
	public Button back_Btn;
	private ManageblHallService manageblHallService;
	private ManageblCenterService manageblCenterService;
	
	public static Parent launch
			(Pane father, Pane before,
			 ManageblHallService hallService, ManageblCenterService centerService) throws IOException
    {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(ManageOrganizationController.class.getResource("manageOrganization.fxml"));
		Pane pane = fxmlLoader.load();
        ManageOrganizationController controller = fxmlLoader.getController();
        controller.manageblHallService = hallService;
        controller.manageblCenterService = centerService;

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
	
	public void initialize(){
        // TODO test jump
//		institutionVOs=this.getInstitutionVOs();
//		cityColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getCity()));
//		typecColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getInfoEnum().name()));
//		institutionIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getInstitutionID()));
//		tableView.setItems(FXCollections.observableList(institutionVOs));
//		tableView.getSelectionModel().selectedItemProperty().addListener(this);
//		tableView.getSelectionModel().selectFirst();
//		now=tableView.getSelectionModel().getSelectedItem();
	}
	private InstitutionVO makeInstitutionVO(){
		if (institutionType.getText()=="CENTER") {
			return new CenterVO(manageblCenterService.newCenterID(city.getText()), city.getText());
		}
		else {
			return new HallVO(manageblHallService.newHallID(nearCenter.getText()), city.getText(), area.getText(), nearCenter.getText());
		}
	}
	
	public void newCenter(){
		this.clear();
		this.institutionType.setText(InfoEnum.CENTER.name());
		isNew=true;
	}
	public void newHall(){
		this.clear();
		this.institutionType.setText(InfoEnum.HALL.name());
		isNew=true;
	}
	public void clear(){
		this.ID.setText(null);
		this.institutionType.setText(null);
		this.area.clear();
		this.city.clear();
		this.nearCenter.clear();
	}
	public void sure(){
		if (isNew) {
			now=this.makeInstitutionVO();
			if (now.getInfoEnum()==InfoEnum.HALL) {
				manageblHallService.addHall((HallVO)now);
			}
			else {
				manageblCenterService.addCenter((CenterVO)now);
			}
			isNew=false;
		}
		else {
			now.setCity(city.getText());
			if (now.getInfoEnum()==InfoEnum.HALL) {
				((HallVO)now).setArea(area.getText());
			}
			if (now.getInfoEnum()==InfoEnum.HALL) {
				manageblHallService.modifyHall((HallVO)now);
			}
			else {
				manageblCenterService.modifyCenter((CenterVO)now);
			}
		}
		this.clear();
		this.refreshTable();
	}
	public void refreshTable(){
		institutionVOs=this.getInstitutionVOs();
		this.tableView.setItems(FXCollections.observableList(institutionVOs));
	}
	public void manageStaff(){}
	
	
	
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
		// TODO Auto-generated method stub
		now=newValue;
		this.setText(now);
	}
	private void setText(InstitutionVO src){
		this.clear();
		if (src==null) {
			return;
		}
		ID.setText(src.getInstitutionID());
		institutionType.setText(src.getInfoEnum().name());
		city.setText(src.getCity());
		if (src.getInfoEnum()==InfoEnum.HALL) {
			area.setText(((HallVO)src).getArea());
		}
	}
}
