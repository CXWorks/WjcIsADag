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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import message.CheckFormMessage;
import message.OperationMessage;
import po.receivedata.StateEnum;
import ui.common.BasicFormController;
import ui.common.FormBridge;
import vo.receivevo.ReceiveVO;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public Label date_ErrLabel;
    public Label transit_errLabel;
    public Label departure_errLabel;

    private StateEnum stateEnum = StateEnum.Complete;

    ReceiveBLService rbl = FormFactory.getReceiveBLService();

    public static Parent launch() throws IOException {

        FXMLLoader btnsloader = new FXMLLoader();
        btnsloader.setLocation(FormBridge.class.getResource("baseForm.fxml"));
        BorderPane borderPane = btnsloader.load();
        FormBridge bridge = btnsloader.getController();

        FXMLLoader contentLoader = new FXMLLoader();
        contentLoader.setLocation(HallReceiveFormController.class.getResource("hallReceiveForm.fxml"));
        Pane pane = contentLoader.load();
        HallReceiveFormController controller = contentLoader.getController();

        bridge.setController(controller);

        borderPane.setCenter(pane);

        return borderPane;
    }

    @FXML
    public void initialize(){
        // initialize the choice box
        arriveState_Box.setItems(FXCollections.observableArrayList(states));
        arriveState_Box.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case "完好":
                            stateEnum = StateEnum.Complete;
                            break;
                        case "损坏":
                            stateEnum = StateEnum.Damage;
                            break;
                        case "丢失":
                            stateEnum = StateEnum.Lose;
                            break;
                    }
                }
        );
        clear(null);

    }

    @Override
    public void commit(ActionEvent actionEvent) {
        Calendar calendar = TimeConvert.convertDate(arrive_DatePicker.getValue());


        //OperationMessage msg = rbl.submit(rvo);

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
        Calendar calendar = TimeConvert.convertDate(arrive_DatePicker.getValue());

        //rbl.saveDraft(rvo);
    }


}
