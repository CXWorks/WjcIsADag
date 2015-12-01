package ui.storeui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bl.blService.storeblService.StoreIOBLService;
import factory.FormFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.store.StoreAreaCode;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import vo.financevo.BankAccountVO;
import vo.storevo.GoodsVO;
import vo.storevo.StoreFormVO;

/**
 * Created by Sissel on 2015/11/26.
 */
public class StoreSummaryController {
	public Label outNum_Label;
	public Label outMoney_Label;
	public Label InNum_Label;
	public Label InMoney_Label;
	public DatePicker begin_DatePicker;
	public DatePicker end_DatePicker;
	public TableView<StoreFormVO> summary_TableView;
	public TableColumn<StoreFormVO, String> time_Column;
	public TableColumn<StoreFormVO, String> orderID_Column;
	public TableColumn<StoreFormVO, String> location_Column;
	public TableColumn<StoreFormVO, String> io_Column;
	public TableColumn<StoreFormVO, String> money_Column;

	private StoreIOBLService storeIOBLService = FormFactory.getStoreIOBLService();

	public static Parent launch() throws IOException {
		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(StoreInFormController.class.getResource("storeSum.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize() {
		time_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getDate().toString())
        );
		orderID_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getOrderID())
        );
		location_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getLocationForLog())
        );
		io_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getIO())
        );
		money_Column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getMoney())
        );
	}

	public void search(ActionEvent actionEvent) {
//		List<GoodsVO> list = storeIOBLService.getGoodsInfo(TimeConvert.convertDate(begin_DatePicker.getValue()),
//				TimeConvert.convertDate(end_DatePicker.getValue()));
		summary_TableView.setItems(
                FXCollections.observableArrayList(
                        new ArrayList<StoreFormVO>()
                )
        );
	}
}