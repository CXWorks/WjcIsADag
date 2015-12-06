package ui.transportui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import factory.FormFactory;
import bl.blService.transportblService.TransportCenterBLService;
import po.receivedata.StateEnum;
import po.transportdata.TransportationEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.receiveui.ReceiveFormController;
import userinfo.UserInfo;
import vo.managevo.institution.InstitutionVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

/**
 * Created by Sissel on 2015/11/27.
 */
public class TransitFormController {
    public Label transNumber_Label;
    public Label cargo_Label;
    public ChoiceBox<SimpleEnumProperty<TransportationEnum>> transitType_ChoiceBox;
    public DatePicker transit_DatePicker;
    public TextField departure_Field;
    public ChoiceBox<String> arrival_Box;
    public TextField supervisor_Field;
    public TextField transNumber_Field;
    public TextField cargo_Field;
    public ListView<String> orders;
//    public TableView<String> orders_TableView;
//    public TableColumn<String,String> order_TableColumn;
    public Label fee_Label;
    public TextField id_Field;
    
    ArrayList<String> ids;
    
    TransportationEnum transitEnum =TransportationEnum.TRAIN;
    TransportCenterBLService transportCenterBLService = FormFactory.getTransportCenterBLService();
    
//    InstitutionVO ivo= transportCenterBLService.getLocation(UserInfo.getInstitutionID());
//    ObservableList<String> arrivals 

    
    public static Parent launch() throws IOException {

        FXMLLoader contentLoader = new FXMLLoader();
        contentLoader.setLocation(TransitFormController.class.getResource("transitForm.fxml"));
        return contentLoader.load();
    }

    @FXML
    public void initialize(){

        // initialize the choice box
    	transitType_ChoiceBox.setItems(Enum2ObservableList.transit(TransportationEnum.values()));
    	transitType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                	transitEnum = newValue.getEnum();
                }
        );
    	
//    	arrival_Box.setItems(arrivals);
//    	
    	
        clear(null);
    }
    
    
    public void add(ActionEvent actionEvent){
    	String a= id_Field.getText();
    	System.out.println("aaaa"+a);
//    	ids.add(a);
    	orders.getItems().add(a);
    	id_Field.clear();
    	
    }
    
    public void saveDraft(ActionEvent actionEvent) {
    	transportCenterBLService.saveDraft(generateVO(null));
    	
    }

    public void clear(ActionEvent actionEvent) {
    	
    	transit_DatePicker.setValue(LocalDate.now());
    	transitType_ChoiceBox.setValue(transitType_ChoiceBox.getItems().get(0));
    	departure_Field.clear();
    	arrival_Box.setValue(arrival_Box.getItems().get(0));;
    	supervisor_Field.clear();
    	transNumber_Field.clear();
    	cargo_Field.clear();
    	id_Field.clear();
    	orders.setItems(null);
    	
    }

    private CenterOutVO generateVO(String formID){
        Calendar calendar = TimeConvert.convertDate(transit_DatePicker.getValue());
        return new CenterOutVO(formID,departure_Field.getText(),transNumber_Field.getText(),cargo_Field.getText(),
        		calendar,fee_Label.getText(),arrival_Box.getValue(),supervisor_Field.getText(),ids,
        		transitEnum);
    }

    
    public void commit(ActionEvent actionEvent) {
    	 OperationMessage msg = transportCenterBLService.submit(generateVO(transportCenterBLService.newID()));

         if(msg.operationResult){
             System.out.println("commit successfully");
             clear(null);
         }else{
             System.out.println("commit fail: " + msg.getReason());
         }
    }
}
