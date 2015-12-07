package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;

import po.FormEnum;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	public ArrayList<FormVO> formVOs;
	private ExamineblManageService examineblManageService=ExamineFactory.getExamineblManageService();
	//
	public static Parent launch() throws IOException{
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(FormTableController.class.getResource("FormTableView.fxml"));
		return fxmlLoader.load();
	}
	private  void setColumn(FormEnum formEnum){
		this.formVOs=examineblManageService.getForms(formEnum);
		this.tableView.setItems(FXCollections.observableList(formVOs));
		infoColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getMainInfo()));
		tableView.getColumns().add(infoColumn);
	}
	private ArrayList<FormVO> transObervableList2List(ObservableList<FormVO> observableList){
		ArrayList<FormVO> ans=new ArrayList<FormVO>(observableList.size());
		for (FormVO formVO : observableList) {
			ans.add(formVO);
		}
		return ans;
	}
	//
	public void initialize(){
		this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//
		calendarColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().formID));
		creatorIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getCreaterID()));
		formIDColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().formID));
		typeColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().formType.getChinese()));
	}
	//
	public void change(FormEnum formEnum){
		this.setColumn(formEnum);
	}
	//
	public void selectAll(){
		this.tableView.getSelectionModel().selectAll();
	}
	//
	public ArrayList<FormVO> getSelected(){
		ObservableList<FormVO> selected= this.tableView.getSelectionModel().getSelectedItems();
		return this.transObervableList2List(selected);
	}
}
