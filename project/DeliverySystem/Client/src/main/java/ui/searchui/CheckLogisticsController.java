package ui.searchui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import po.financedata.FinancePayEnum;
import tool.ui.LogisticsVO2ColumnHelper;
import ui.receiveui.ReceiveFormController;
import vo.financevo.PaymentVO;
import vo.logisticsvo.LogisticsVO;
import bl.blService.searchblService.SearchBLService;
import factory.SearchFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CheckLogisticsController {

	public TextField search_Field;

	public TableView<Map.Entry<String, String>> logistics_TableView;
	public TableColumn<Map.Entry<String, String>, String> time_Column;
	public TableColumn<Map.Entry<String, String>, String> address_Column;

	SearchBLService searchblService = SearchFactory.getSearchBLService();

	private LogisticsVO logisticsvo=new LogisticsVO();
	public static Parent launch() throws IOException {

		FXMLLoader contentLoader = new FXMLLoader();
		contentLoader.setLocation(CheckLogisticsController.class.getResource("checkLogistics.fxml"));
		return contentLoader.load();
	}

	@FXML
	public void initialize(){
		
	}


	public void search(ActionEvent atcionEvent){
		logistics_TableView.getItems().clear();
		System.out.println("hhhhhhhhhhhh"+search_Field.getText());
		logisticsvo=searchblService.searchOrder(
				search_Field.getText());
		System.out.println("logistics=="+logisticsvo);
        if(this.logisticsvo == null){
            return;
        }
        
        LogisticsVO2ColumnHelper.setKeyColumn(time_Column);
        LogisticsVO2ColumnHelper.setValueColumn(address_Column);
        logistics_TableView.setItems(FXCollections.observableArrayList(new LogisticsVO2ColumnHelper().VO2Entries(logisticsvo)));
       
	}
	
	public void toLogIn(ActionEvent actionEvent){
		
	}
	
	
}
