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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.store.StoreAreaCode;
import tool.time.TimeConvert;
import tool.ui.StoreAreaVO2ColumnHelper;
import userinfo.UserInfo;
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
	public RadioButton button_air;
	public RadioButton button_rail;
	public RadioButton button_road;
	public RadioButton button_flex;

	private StoreModelBLService storeModelBLService = FormFactory.getStoreModelBLService();

	private StoreAreaCode area;

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
		button_rail.setSelected(false);
		button_road.setSelected(false);
		button_flex.setSelected(false);
		this.setView();
	}

	public void selectRail(ActionEvent actionEvent) {
		area = StoreAreaCode.RAIL;
		button_air.setSelected(false);
		button_road.setSelected(false);
		button_flex.setSelected(false);
		this.setView();
	}

	public void selectRoad(ActionEvent actionEvent) {
		area = StoreAreaCode.ROAD;
		button_air.setSelected(false);
		button_rail.setSelected(false);
		button_flex.setSelected(false);
		this.setView();
	}

	public void selectFlex(ActionEvent actionEvent) {
		area = StoreAreaCode.FLEX;
		button_air.setSelected(false);
		button_rail.setSelected(false);
		button_road.setSelected(false);
		this.setView();
	}

	private void setView() {
//		System.out.println(storeModelBLService.getShelfInfo(area)==null);
		rows_TableView.setItems(FXCollections.observableArrayList(storeModelBLService.getShelfInfo(UserInfo.getInstitutionID(),area)));

		area_TableView.setItems(FXCollections.observableArrayList(
				new StoreAreaVO2ColumnHelper().VO2Entries(storeModelBLService.getStoreAreaInfo(UserInfo.getInstitutionID(),area))));
	}

	public void expandArea(ActionEvent actionEvent) {
        Stage dialogStage = new Stage();
        try {
            EditShelfDialogController dialog = EditShelfDialogController.newDialog(dialogStage);
            dialogStage.showAndWait();
            int num = dialog.getShelfNum();
            if(num != 0){
                storeModelBLService.expandPartition(UserInfo.getInstitutionID(),area, num);
            }
            this.setView();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void reduceArea(ActionEvent actionEvent) {
        Stage dialogStage = new Stage();
        try {
            EditShelfDialogController dialog = EditShelfDialogController.newDialog(dialogStage);
            dialogStage.showAndWait();
            int num = dialog.getShelfNum();
            if(num != 0){
                storeModelBLService.reducePartition(UserInfo.getInstitutionID(),area, num);
            }
            this.setView();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void reajust(ActionEvent actionEvent) {
		storeModelBLService.setWarningLine(Double.parseDouble(warnLine_Field.getText()));
		this.setView();
	}
}
