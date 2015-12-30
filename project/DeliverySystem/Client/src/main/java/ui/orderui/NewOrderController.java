package ui.orderui;

import bl.blImpl.orderbl.OrderBLController;
import bl.blService.orderblService.OrderBLService;
import factory.FormFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.PackingEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Charles_M on 2015/11/22.
 */
public class NewOrderController {
	public TextField name_From;
	public TextField address_From;
	public TextField unit_From;
	public TextField tel_From;
	public TextField phone_From;

	public TextField name_To;
	public TextField address_To;
	public TextField unit_To;
	public TextField tel_To;
	public TextField phone_To;

	public TextField goods_Number;
	public TextField goods_Weight;
	public TextField goods_V1;
	public TextField goods_V2;
	public TextField goods_V3;
	public TextField goods_Name;
	public TextField goods_Type;

	public ChoiceBox<SimpleEnumProperty<DeliverTypeEnum>> type_Box;
	public ChoiceBox<SimpleEnumProperty<PackingEnum>> pack_Box;
	public ChoiceBox<String> city_Box;
	public ArrayList citys;
	
	public Label predict_Expense;
	public Label predict_Date;
	
	public Label city_Label;
	
	
	public Label date_ErrLabel;
	public Label transit_errLabel;
	public Label departure_errLabel;
	public Label date_ErrLabel1;
	public Label transit_errLabel1;
	public Label departure_errLabel1;
	public Label date_ErrLabel2;
	public Label transit_errLabel2;
	public Label departure_errLabel2;
    public AnchorPane outerPane;
	public Button commit_Btn;
	public Button save_Btn;
	public Button clear_Btn;
	public Button calculate_Btn;
    public Label predictHead_Date;

    private DeliverTypeEnum deliverType = DeliverTypeEnum.NORMAL;
	private PackingEnum packing = PackingEnum.PAPER;

    // 总经理修改时用，其他时候不用
    private OrderVO editVO;

	int money = 0;// 预计运费
	int day = 0;
	String predictDate;// 预计送达日期

	private OrderBLService obl = FormFactory.getOrderBLService();
	private InformController informController;

	public static NewOrderController launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(NewOrderController.class.getResource("NewOrder.fxml"));
        Pane pane = loader.load();
        NewOrderController controller = loader.getController();
        controller.informController = InformController.newInformController(pane);

        return controller;
	}

    public static Parent launchInNew(){
        try {
            return launch().informController.stackPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Parent launchInManagerEdit(OrderVO orderVO){
        try {
            NewOrderController controller = launch();
            controller.commit_Btn.setVisible(false);
            controller.commit_Btn.setOnAction(
                    actionEvent  -> {
                        // TODO managerEdit
                    }
            );
            controller.predictHead_Date.setVisible(false);
            controller.showDetail(orderVO);
            return controller.informController.stackPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	public static Parent launchInHistory(OrderVO orderVO){
        try {
            NewOrderController controller = launch();
            controller.calculate_Btn.setVisible(false);
            controller.clear_Btn.setVisible(false);
            controller.save_Btn.setVisible(false);
            controller.commit_Btn.setVisible(false);
            controller.predictHead_Date.setVisible(false);
            controller.showDetail(orderVO);
            return controller.informController.stackPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	@FXML
	public void initialize() {
        outerPane.setOnKeyTyped(
                event -> {
                    if(event.getCharacter().equals("\r")){
                        System.out.println("key press ENTER");
                    }
                    if(event.isControlDown() && event.getCharacter().equals("k")){
                        System.out.println("key press K");
                    }
                }
        );
        
        city_Label.setText(obl.localCity());
		// initialize the choice box
        citys=(ArrayList) obl.getAvaliableCity();
        city_Box.setItems(FXCollections.observableArrayList(citys));
        city_Box.setValue(city_Box.getItems().get(0));
		type_Box.setItems(Enum2ObservableList.transit(DeliverTypeEnum.values()));
		type_Box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deliverType = newValue.getEnum();
        });

		pack_Box.setItems(Enum2ObservableList.transit(PackingEnum.values()));
		pack_Box.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            packing = newValue.getEnum();
//            fillPrediction(null);
        });

		clear(null);

	}

	public void commit(ActionEvent actionEvent) {

		OperationMessage msg = obl.submit(generateVO(obl.newID()));

		if (msg.operationResult) {
			System.out.println("commit successfully");
			clear(null);
		} else {
			System.out.println("commit fail: " + msg.getReason());
		}

	}

	private OrderVO generateVO(String FormID) {
		return new OrderVO(FormID, name_From.getText(), name_To.getText(), unit_From.getText(), unit_To.getText(),
				city_Label.getText()+address_From.getText(), city_Box.getValue()+address_To.getText(), tel_From.getText(), tel_To.getText(),
				phone_From.getText(), phone_To.getText(), goods_Number.getText(), goods_Name.getText(),
				goods_Weight.getText(), goods_V1.getText()+"*"+goods_V2.getText()+"*"+goods_V3.getText(), predict_Expense.getText(), goods_Type.getText(),
				deliverType, packing, UserInfo.getUserID());
	}

	@FXML
	private void fillPrediction(ActionEvent actionEvent) {

		PredictVO predictVO = obl.predict(generateVO(null));
		predict_Date.setText(TimeConvert.getDisplayDate(predictVO.getPredictDate()));
		predict_Expense.setText(predictVO.getExpense());
	}

    private void showDetail(OrderVO orderVO){
        name_From.setText(orderVO.getNameFrom());
        name_To.setText(orderVO.getNameTo());
        // TODO city how to split ?
        address_From.setText(orderVO.getAddressFrom());
        address_To.setText(orderVO.getAddressTo());
        unit_From.setText(orderVO.getUnitFrom());
        unit_To.setText(orderVO.getUnitTo());
        phone_From.setText(orderVO.getPhoneNumFrom());
        phone_To.setText(orderVO.getPhoneNumTo());
        tel_From.setText(orderVO.getTelNumFrom());
        tel_To.setText(orderVO.getTelNumTo());
        goods_Name.setText(orderVO.getGoodsName());
        goods_Type.setText(orderVO.getGoodsType());
        goods_Number.setText(orderVO.getGoodsNum());
        goods_Weight.setText(orderVO.getWeight());
        goods_V1.setText(orderVO.getLen() + "");
        goods_V2.setText(orderVO.getWid() + "");
        goods_V3.setText(orderVO.getHei() + "");

        type_Box.getSelectionModel().select(new SimpleEnumProperty<DeliverTypeEnum>(orderVO.getType()));
        pack_Box.getSelectionModel().select(new SimpleEnumProperty<PackingEnum>(orderVO.getPack()));
        predict_Expense.setText(orderVO.getMoney());
    }

	public void clear(ActionEvent actionEvent) {
		name_From.clear();
		address_From.clear();
		unit_From.clear();
		tel_From.clear();
		phone_From.clear();

		name_To.clear();
		address_To.clear();
		unit_To.clear();
		tel_To.clear();
		phone_To.clear();

		goods_Number.clear();
		goods_Weight.clear();
		goods_V1.clear();
		goods_V2.clear();
		goods_V3.clear();
		goods_Name.clear();
		goods_Type.clear();

		predict_Expense.setText("");
		predict_Date.setText("");
		SimpleEnumProperty<PackingEnum> pe = pack_Box.getItems().get(0);
		pack_Box.setValue(pe);
		packing = pe.getEnum();

		SimpleEnumProperty<DeliverTypeEnum> dte = type_Box.getItems().get(0);
		type_Box.setValue(dte);
		deliverType = dte.getEnum();
	}

	public void saveDraft(ActionEvent actionEvent) {

		OrderVO ovo = generateVO(null);
		obl.saveDraft(ovo);
	}

}
