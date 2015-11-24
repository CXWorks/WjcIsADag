package ui.orderui;

import java.io.IOException;







import java.util.Date;

import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;

public class CheckToSendController {

	
	public TableColumn<DeliverVO, Date> time_Column;
	public TableColumn<OrderVO,String> ID_Column;
	public TableColumn<OrderVO,String> address_Column;
	
    private ObservableList<DeliverVO> deliverVOs ;
    
    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckToSendController.class.getResource("CheckToSend.fxml"));
    }
	
    
    @FXML
    public void initialize(){
       // time_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getDate()));
        ID_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFormID()));
        address_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAddressTo()));

    }
    
	public void checkDetails(ActionEvent aciontEvent){
		
	}
	
}
