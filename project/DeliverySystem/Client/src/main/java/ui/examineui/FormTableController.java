package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;

import po.FormEnum;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

/** 
 * Client//ui.examineui//FormTableController.java
 * @author CXWorks
 * @date 2015年12月3日 下午7:56:11
 * @version 1.0 
 */
public class FormTableController {
	public TableView<FormVO> tableView;
	public TableColumn<FormVO, String> calendarColumn;
	public TableColumn<FormVO, String> typeColumn;
	public TableColumn<FormVO, String> formIDColumn;
	public TableColumn<FormVO, String> creatorIDColumn;
	public TableColumn<FormVO, String> infoColumn;
	//
	private ArrayList<FormVO> formVOs;
	private ExamineblManageService examineblManageService=ExamineFactory.getExamineblManageService();
	//
	public static Parent launch() throws IOException{
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(FormTableController.class.getResource("FormTableView.fxml"));
		return fxmlLoader.load();
	}
	private  void setColumn(FormEnum formEnum){
		this.formVOs=examineblManageService.getForms(null);
		this.tableView.setItems(FXCollections.observableList(formVOs));
	}
	//
	public void initialize(){
		this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.setColumn(null);
		//
		calendarColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().formID.substring(9, 17)));
		creatorIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getCreaterID()));
		formIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().formID));
		infoColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getMainInfo()));
	}
	//
	public void change(FormEnum formEnum){
		this.setColumn(formEnum);
		//
	}
	//
	public void selectAll(){
		//TODO waitting for solutions
	}
	//
	public ArrayList<FormVO> getSelected(){
		//TODO the same reason
		return null;
	}
}
