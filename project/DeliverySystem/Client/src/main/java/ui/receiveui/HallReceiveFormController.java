package ui.receiveui;

import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blService.receiveblService.ReceiveBLService;
import bl.tool.time.TimeConvert;
import factory.BLFactory;
import factory.FormFactory;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import po.receivedata.StateEnum;
import ui.common.BasicFormController;
import vo.receivevo.ReceiveVO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sissel on 2015/11/21.
 */
public class HallReceiveFormController extends BasicFormController{

    //
    private final static String[] states = {"完好", "损坏", "丢失"};
    
    public DatePicker arrive_DatePicker;
    public TextField transitID_Field;
    public TextField departure_Field;
    public ChoiceBox<String> arriveState_Box;
    public TableView order_Table;
    public TextField order_Field;

    private StateEnum stateEnum = StateEnum.Complete;

    ReceiveBLService rbl = new FormFactory().getReceiveBLService();

    @FXML
    public void initialize(){
        // initialize the choice box
        arriveState_Box.setItems(FXCollections.observableArrayList(states));
        arriveState_Box.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue){
                        case "完好": stateEnum = StateEnum.Complete;
                            break;
                        case "损坏": stateEnum = StateEnum.Damage;
                            break;
                        case "丢失": stateEnum = StateEnum.Lose;
                            break;
                    }
                }
        );
    }

    @Override
    public void commit(ActionEvent actionEvent) {
        Calendar calendar = TimeConvert.convertDate(arrive_DatePicker.getValue());
        ReceiveVO rvo = new ReceiveVO(order_Field.getText(), transitID_Field.getText(), calendar, departure_Field.getText(), stateEnum);
    }

    @Override
    public void clear(ActionEvent actionEvent) {
        arrive_DatePicker.setValue(null);
        transitID_Field.clear();
        departure_Field.clear();
        order_Field.clear();
        order_Table.setItems(null);
        arriveState_Box.setValue("完好");
    }

    @Override
    public void saveDraft(ActionEvent actionEvent) {

    }


}
