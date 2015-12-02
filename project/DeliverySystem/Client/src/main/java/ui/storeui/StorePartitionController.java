package ui.storeui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import bl.blService.storeblService.StoreModelBLService;
import factory.FormFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.store.StoreAreaCode;
import tool.time.TimeConvert;
import tool.ui.StoreAreaVO2ColumnHelper;
import vo.storevo.StoreAreaInfoVO;
import vo.storevo.StoreFormVO;
import vo.storevo.StoreShelfVO;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StorePartitionController {
	public TableView<StoreShelfVO> rows_TableView;
	public TableColumn<StoreShelfVO, String> rowNum_TableColumn;
	public TableColumn<StoreShelfVO, String> shelfNum_TableColumn;
	public TableColumn<StoreShelfVO, String> rate_TableColumn;
	public TableView<Map.Entry<String, String>> area_TableView;
	public TableColumn<Map.Entry<String, String>, String> key_TableColumn;
	public TableColumn<Map.Entry<String, String>, String> value_TableColumn;
	public TextField warnLine_Field;

	private StoreModelBLService storeModelBLService = FormFactory.getStoreModelBLService();

	StoreAreaCode area;

	public static Parent launch() throws IOException {
		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(StoreInFormController.class.getResource("storePartition.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize() {
		rowNum_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRowID() + ""));
		shelfNum_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShelfID() + ""));
		rate_TableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getUsedProportion() + ""));

		StoreAreaVO2ColumnHelper.setKeyColumn(key_TableColumn);
		StoreAreaVO2ColumnHelper.setValueColumn(value_TableColumn);
	}

	public void selectAir(ActionEvent actionEvent) {
		area = StoreAreaCode.AIR;
		this.setView();
	}

	public void selectRail(ActionEvent actionEvent) {
		area = StoreAreaCode.RAIL;
		this.setView();
	}

	public void selectRoad(ActionEvent actionEvent) {
		area = StoreAreaCode.ROAD;
		this.setView();
	}

	public void selectFlex(ActionEvent actionEvent) {
		area = StoreAreaCode.FLEX;
		this.setView();
	}

	private void setView() {
		rows_TableView.setItems(FXCollections.observableArrayList(storeModelBLService.getShelfInfo(area)));

		area_TableView.setItems(FXCollections.observableArrayList(
				new StoreAreaVO2ColumnHelper().VO2Entries(storeModelBLService.getStoreAreaInfo(area))));
	}

	public void expandArea(ActionEvent actionEvent) {
		storeModelBLService.expandPartition(area, 1);
		this.setView();
	}

	public void reduceArea(ActionEvent actionEvent) {
		storeModelBLService.reducePartition(area, 1);
		this.setView();
	}

	public void reajust(ActionEvent actionEvent) {
		storeModelBLService.setWarningLine(Double.parseDouble(warnLine_Field.getText()));
		this.setView();
	}
}
