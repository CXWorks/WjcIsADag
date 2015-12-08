package ui.financeui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STValue;
import po.systemdata.SystemState;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import userinfo.UserInfo;
import util.EnumObservable;
import vo.financevo.BankAccountVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.InstitutionVO;
import vo.managevo.staff.StaffVO;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Sissel on 2015/11/27.
 */

enum  InitType implements EnumObservable<InitType> {

    BANK_ACCOUNT_INIT("账户期初信息"),
    INSTITUTION_INIT("机构期初信息"),
    STAFF_INIT("员工期初信息"),
    STORE_INIT("仓库期初信息"),
    CAR_INIT("车辆期初信息");

    private String chinese;

    InitType(String chinese) {
        this.chinese = chinese;
    }

    @Override
    public String getChinese() {
        return chinese;
    }
}

public class CheckInitInfoController {
    private InitializationBLService initBLService = InitBLFactory.getInitializationBLService();

    public AnchorPane content_Pane;
    public Label systemState_Label;
    public ChoiceBox<SimpleEnumProperty<InitType>> initType_ChoiceBox;

    public TableView info_TableView = new TableView();

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckInitInfoController.class.getResource("checkInitInfo.fxml"));
    }

    @FXML
    public void initialize(){
        // initialize choiceBox
        initType_ChoiceBox.setItems(Enum2ObservableList.transit(InitType.values()));

        initType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue.getEnum()){
                        case BANK_ACCOUNT_INIT:
                            showBankAccounts();break;
                        case INSTITUTION_INIT:
                            showInstitutions();break;
                        case STAFF_INIT:
                            showStaffs();break;
                        case STORE_INIT:
                            showStroes();break;
                        case CAR_INIT:
                            showCars();break;
                    }
                }
        );
        initType_ChoiceBox.setValue(initType_ChoiceBox.getItems().get(0));
        showBankAccounts();

        // TODO test to be removed
        UserInfo.setSystemState(SystemState.NORMAL);
        systemState_Label.setText(UserInfo.getSystemState().getChinese());
    }

    public void applyForInitialization(ActionEvent actionEvent) {
        initBLService.requestInitData();
        // TODO jump
    }

    private void showBankAccounts(){
        TableColumn<BankAccountVO, String> name_TableColumn = new TableColumn("账户名");
        TableColumn<BankAccountVO, String> balance_TableColumn = new TableColumn("余额");

        name_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountName())
        );
        balance_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBalance())
        );


        reconstructColumns(name_TableColumn, balance_TableColumn);
        //TODO test
        //info_TableView.getItems().addAll(initBLService.getAllAccounts());
        info_TableView.getItems().addAll(
                new BankAccountVO("123", "soft", "450"),
                new BankAccountVO("233", "hard", "99999")
        );
    }

    private void showCars(){
        TableColumn<CarVO, String> id_TableColumn = new TableColumn<>("车辆代号");
        TableColumn<CarVO, String> licence_TableColumn = new TableColumn<>("车牌号");
        TableColumn<CarVO, String> time_TableColumn = new TableColumn<>("服役时间");

        id_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCarID())
        );
        licence_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNameID())
        );
        time_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty()
        );

        reconstructColumns(id_TableColumn, licence_TableColumn, time_TableColumn);
        info_TableView.getItems().addAll(initBLService.getAllCars());
    }

    private void showInstitutions(){
        TableColumn<InstitutionVO, String> id_TableColumn = new TableColumn<>("机构编号");
        TableColumn<InstitutionVO, String> type_TableColumn = new TableColumn<>("机构类型");
        TableColumn<InstitutionVO, String> city_TableColumn = new TableColumn<>("所在城市");
        TableColumn<InstitutionVO, String> staff_TableColumn = new TableColumn<>("职工人数");

        id_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getInstitutionID())
        );
        type_TableColumn.setCellValueFactory(
                cell -> {
                    switch (cell.getValue().getInfoEnum()){
                        case HALL:
                            return new SimpleStringProperty("营业厅");
                        case CENTER:
                            return new SimpleStringProperty("中转中心");
                    }
                    return new SimpleStringProperty("???");
                }
        );
        city_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getCity())
        );
        staff_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getStaffCount()+"")
        );

        reconstructColumns(id_TableColumn, type_TableColumn, city_TableColumn, staff_TableColumn);
        info_TableView.getItems().addAll(initBLService.getAllCenters());
        info_TableView.getItems().addAll(initBLService.getAllHalls());
    }

    private void showStaffs(){
        TableColumn<StaffVO, String> id_TableColumn = new TableColumn<>("员工编号");
        TableColumn<StaffVO, String> name_TableColumn = new TableColumn<>("姓名");
        TableColumn<StaffVO, String> type_TableColumn = new TableColumn<>("职务");
        TableColumn<StaffVO, String> institution_TableColumn = new TableColumn<>("单位");

        id_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getID())
        );
        name_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getName())
        );
        type_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getStaff().getChinese())
        );
        institution_TableColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getInstitutionID())
        );

        reconstructColumns(id_TableColumn, name_TableColumn, type_TableColumn, institution_TableColumn);
        info_TableView.getItems().addAll(initBLService.getAllStaffs());
    }

    private void showStroes(){

    }

    private void reconstructColumns(TableColumn...columns){
        info_TableView.getColumns().clear();
        info_TableView.getItems().clear();
        for (TableColumn column : columns) {
            info_TableView.getColumns().add(column);
        }
    }
}
