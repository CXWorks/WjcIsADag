package ui.orderui;

import java.io.IOException;








import java.util.Map;

import tool.ui.DeliverVO2ColumnHelper;
import tool.ui.OrderVO2ColumnHelper;
import ui.loginui.LoginController;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;

public class CheckToSendController {

	
	public TableColumn<Map.Entry<String, String>, String>  time_Column;
	public TableColumn<Map.Entry<String, String>, String> id_Column;
	public TableColumn<Map.Entry<String, String>, String> address_Column;
	public TableColumn<Map.Entry<String, String>, String> people_Column;  //收件人姓名
	
    
    public static Parent launch() throws IOException {
        return FXMLLoader.load(LoginController.class.getResource("checkToSend.fxml"));
        
    }
	
    
    @FXML
    public void initialize(){
       // time_Column.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getDate()));
      
        DeliverVO2ColumnHelper.setKeyColumn(id_Column); 
        DeliverVO2ColumnHelper.setKeyColumn(address_Column); 
        DeliverVO2ColumnHelper.setKeyColumn(people_Column); 
        
    }
    
	public void checkDetails(ActionEvent aciontEvent){
		
	}
	
}
