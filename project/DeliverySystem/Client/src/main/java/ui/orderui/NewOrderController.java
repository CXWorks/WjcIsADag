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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.PackingEnum;
import tool.time.TimeConvert;
import ui.common.BasicFormController;
import ui.common.FormBridge;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Charles_M on 2015/11/22.
 */
public class NewOrderController extends BasicFormController {

	private final static String[] type ={"经济快递","标准快递","特快专递"};
	private final static String[] pack={"纸箱","木箱","快递袋"};
	
	

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
	public TextField goods_Volume;
	public TextField goods_Name;
	public TextField goods_Type;
	public ChoiceBox<String> type_Box;
	public ChoiceBox<String> pack_Box;
	
	public Label predict_Expense;
	public Label predict_Date;
	
	private DeliverTypeEnum deliverType = DeliverTypeEnum.NORMAL;
	private PackingEnum packing = PackingEnum.PAPER;
	
	int money=0;//预计运费
	int day=0;
	String predictDate;//预计送达日期
	
	OrderBLService obl = FormFactory.getOrderBLService();
	
	 public static Parent launch() throws IOException {

	        FXMLLoader btnsloader = new FXMLLoader();
	        btnsloader.setLocation(FormBridge.class.getResource("baseForm.fxml"));
	        BorderPane borderPane = btnsloader.load();
	        FormBridge bridge = btnsloader.getController();

	        FXMLLoader contentLoader = new FXMLLoader();
	        contentLoader.setLocation(NewOrderController.class.getResource("NewOrder.fxml"));
	        Pane pane = contentLoader.load();
	        NewOrderController controller = contentLoader.getController();

	        bridge.setController(controller);

	        borderPane.setCenter(pane);

	        return borderPane;
	 }

	@FXML
	public void initialize(){
		// initialize the choice box
		type_Box.setItems(FXCollections.observableArrayList(type));
		type_Box.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					switch (newValue) {
						case "标准快递":
							deliverType = deliverType.NORMAL;
							break;
						case "经济快递":
							deliverType = deliverType.SLOW;
							break;
						case "特快专递":
							deliverType = deliverType.FAST;
							break;
					}
				}
		);

		pack_Box.setItems(FXCollections.observableArrayList(pack));
		pack_Box.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					switch (newValue) {
						case "快递袋":
							packing = packing.BAG;
							break;
						case "纸箱":
							packing = packing.PAPER;
							break;
						case "木箱":
							packing = packing.WOOD;
							break;
						case"其他":
							packing = packing.OTHER;   //这个要手动填写包装费用还没有解决
							break;
					}
				}
		);

		clear(null);

	}

	public void commit(ActionEvent actionEvent) {

		OperationMessage msg = obl.submit(generateVO(obl.newID()));

        if(msg.operationResult){
            System.out.println("commit successfully");
            clear(null);
        }else{
            System.out.println("commit fail: " + msg.getReason());
        }

	}
	
	private OrderVO generateVO(String FormID){
		return new OrderVO
				(FormID,name_From.getText(),name_To.getText() ,unit_From.getText(),unit_To.getText(),
						address_From.getText(),address_To.getText(),
						tel_From.getText(),tel_To.getText(),phone_From.getText(),phone_To.getText(),
						goods_Number.getText(),goods_Name.getText(),goods_Weight.getText(),goods_Volume.getText(),predict_Expense.getText(),
						goods_Type.getText(),deliverType,packing);
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
		goods_Volume.clear();
		goods_Name.clear();
		pack_Box.setValue("纸箱");
		type_Box.setValue("标准快递");
	}

	@Override
	public void saveDraft(ActionEvent actionEvent) {

		OrderVO ovo = generateVO(null);
		obl.saveDraft(ovo);
	}

}
