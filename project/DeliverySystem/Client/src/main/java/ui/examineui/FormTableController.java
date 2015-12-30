package ui.examineui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.Main;
import po.FormEnum;
import tool.ui.AnchorSet;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import factory.ExamineFactory;
import bl.blService.examineblService.ExamineblManageService;
import util.R;
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
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;

/**
 * Client//ui.examineui//FormTableController.java
 *
 * @author CXWorks
 * @date 2015年12月3日 下午7:56:11
 * @version 1.0
 */
public class FormTableController {
	public boolean loaded = false;
	public TableView<FormVO> tableView;
	public TableColumn<FormVO, String> calendarColumn;
	public TableColumn<FormVO, String> typeColumn;
	public TableColumn<FormVO, String> formIDColumn;
	public TableColumn<FormVO, String> creatorIDColumn;
	public TableColumn<FormVO, String> infoColumn;
	public TableColumn check_TableColumn;
	public StackPane fatherPane;
    public AnchorPane anchorFatherPane;

	//
	public List<FormVO> formVOs;
    private ExamineblManageService examineblManageService = ExamineFactory.getExamineblManageService();
	//
	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(FormTableController.class.getResource("FormTableView.fxml"));
		Pane pane = loader.load();
		FormTableController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	private void setColumn(FormEnum formEnum) {
		this.formVOs = examineblManageService.getForms(formEnum);
		this.tableView.setItems(FXCollections.observableList(formVOs));
		if (formVOs == null || formVOs.size() == 0) {
			return;
		}
		if (tableView.getColumns().contains(infoColumn)) {
			return;
		}
		infoColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMainInfo()));
	}

	private ArrayList<FormVO> transObervableList2List(ObservableList<FormVO> observableList) {
		ArrayList<FormVO> ans = new ArrayList<FormVO>(observableList.size());
		for (FormVO formVO : observableList) {
			ans.add(formVO);
		}
		return ans;
	}

	//
	public void initialize() {
		this.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		infoColumn = new TableColumn<FormVO, String>("信息摘要");
		//
		calendarColumn.setCellValueFactory(cell -> {
			FormEnum type = cell.getValue().formType;
			String formID = cell.getValue().formID;
			String ans;
			if (type == FormEnum.ORDER) {
				Calendar now = Calendar.getInstance();
				ans = Integer.toString(now.get(Calendar.YEAR)) + formID.substring(0, 4);
			} else {
				ans = formID.substring(9, 17);
			}
			return new SimpleStringProperty(ans);
		});
		creatorIDColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCreaterID()));
		formIDColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().formID));
		typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().formType.getChinese()));

        AnchorSet.set0(fatherPane);
        fatherPane.prefHeightProperty().bind(Main.primaryStage.heightProperty());
        fatherPane.prefWidthProperty().bind(Main.primaryStage.widthProperty().subtract(R.ui.LeftTabsWidth));
        anchorFatherPane.prefWidthProperty().bind(fatherPane.widthProperty());
        anchorFatherPane.prefHeightProperty().bind(fatherPane.heightProperty());
	}

	//
	public void change(FormEnum formEnum) {
		this.setColumn(formEnum);
	}

	//
	public void selectAll() {
		this.tableView.getSelectionModel().selectAll();
	}

	//
	public ArrayList<FormVO> getSelected() {
		ObservableList<FormVO> selected = this.tableView.getSelectionModel().getSelectedItems();
		return this.transObervableList2List(selected);
	}
}
