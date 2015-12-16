package ui.transportui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tool.time.TimeConvert;
import tool.ui.SimpleEnumProperty;
import userinfo.UserInfo;
import vo.managevo.car.CarVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import po.transportdata.TransportationEnum;
import message.OperationMessage;
import bl.blService.transportblService.TransportCenterBLService;
import bl.blService.transportblService.TransportHallBLService;
import factory.FormFactory;

/**
 * Created by Sissel on 2015/11/23.
 * 这个类负责中转中心和营业厅的装车单，虽然文档上有一个区别，但是多的东西并无卵用，所以我合起来了
 */
public class LoadCarController {

    public TextField transitCarID_Field;
    public DatePicker date_Picker;
    public TextField monitor_Field;
    public TextField guard_Field;
    public Label date_errLabel;
    public Label fee_Label;
    public ListView<String> orders_ListView;
    public TextField id_Field;
    public ChoiceBox<String> arrival_ChoiceBox;
    public ChoiceBox<String> carID_ChoiceBox;

    ArrayList<String> ids = new ArrayList<String>();

    TransportHallBLService transportHallBLService = FormFactory.getTransportHallBLService();

    ArrayList<String> arrivals = transportHallBLService.getLocation(UserInfo.getInstitutionID());
    ArrayList<String> cars = transportHallBLService.getCars(UserInfo.getInstitutionID());

    public static Parent launch() throws IOException {
        return FXMLLoader.load(LoadCarController.class.getResource("loadCarForm.fxml"));
    }

    @FXML
    public void initialize(){
    	arrival_ChoiceBox.setItems(FXCollections.observableArrayList(arrivals));
    	carID_ChoiceBox.setItems(FXCollections.observableArrayList(cars));
    	transitCarID_Field.setText(transportHallBLService.newID());
    	System.out.println(transportHallBLService.newID());
    	 clear(null);
    }

    public void add(ActionEvent actionEvent){
    	String orderID = id_Field.getText();
    	System.out.println("add" + orderID);
     	ids.add(orderID);
     	orders_ListView.getItems().add(orderID);
    	id_Field.clear();
    }

    public void saveDraft(ActionEvent actionEvent) {
    	transportHallBLService.saveDraft(generateVO(null));
    }

    public void clear(ActionEvent actionEvent) {
    	date_Picker.setValue(LocalDate.now());
    	arrival_ChoiceBox.setValue(arrival_ChoiceBox.getItems().get(0));
    	transitCarID_Field.clear();
    	monitor_Field.clear();
    	guard_Field.clear();
    	orders_ListView.getItems().clear();
    }

    private LoadVO generateVO(String formID){
        Calendar calendar = TimeConvert.convertDate(date_Picker.getValue());
        return new LoadVO(
                formID, carID_ChoiceBox.getValue().toString(), guard_Field.getText(), fee_Label.getText(),
        		calendar, transitCarID_Field.getText(),
        		arrival_ChoiceBox.getValue().toString(), monitor_Field.getText(), ids,UserInfo.getUserID());
    }


    public void commit(ActionEvent actionEvent) {

    	 OperationMessage msg = transportHallBLService.submit(generateVO(transportHallBLService.newID()));

         if(msg.operationResult){
             System.out.println("commit successfully");
             clear(null);
         }else{
             System.out.println("commit fail: " + msg.getReason());
         }
    }
}

