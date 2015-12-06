package ui.searchui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import po.financedata.FinancePayEnum;
import ui.receiveui.ReceiveFormController;
import vo.financevo.PaymentVO;
import vo.logisticsvo.LogisticsVO;
import bl.blService.searchblService.SearchBLService;
import factory.SearchFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CheckLogisticsController {

	public TextField id_Field;

	public TableView<LogisticsVO> logistics_TableView;
	public TableColumn<LogisticsVO, String> time_Column;
	public TableColumn<LogisticsVO, String> address_Column;

	SearchBLService searchblService = SearchFactory.getSearchBLService();

	private LogisticsVO logisticsvo=null;
	public static Parent launch() throws IOException {

		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(CheckLogisticsController.class.getResource("checkLogistics.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize(){
		
	}


	public void search(ActionEvent atcionEvent){
		logisticsvo=searchblService.searchOrder(id_Field.getText());
        if(this.logisticsvo == null){
            return;
        }
        logistics_TableView.getItems().clear();
        // TODO test
        time_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getTime().toString())
        );
       address_Column.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLocation().toString())
        );
	}
	
}
