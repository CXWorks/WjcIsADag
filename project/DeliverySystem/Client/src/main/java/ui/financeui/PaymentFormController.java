package ui.financeui;

import bl.blService.financeblService.PaymentBLService;
import factory.FinanceBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import po.financedata.FinancePayEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.financevo.PaymentVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by Sissel on 2015/11/24.
 */
public class PaymentFormController {

	private PaymentBLService paymentBLService = FinanceBLFactory.getPaymentBLService();

	public TextField payerName_Field;
	public TextField payerAccount_Field;
	public TextField receiverAccount_Field;
	public TextField receiverName_Field;
	public TextField money_Field;
	public DatePicker payment_DatePicker;
	public TextArea note_TextArea;
	public ChoiceBox<SimpleEnumProperty<FinancePayEnum>> item_ChoiceBox;

	private InformController informController;

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(PaymentFormController.class.getResource("paymentForm.fxml"));
		Pane pane = loader.load();
		PaymentFormController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	@FXML
	public void initialize() {
		item_ChoiceBox.setItems(Enum2ObservableList.transit(FinancePayEnum.values()));

		clear(null);
	}

	public void saveDraft(ActionEvent actionEvent) {
		paymentBLService.saveDraft(generatePaymentVO());
	}

	public void clear(ActionEvent actionEvent) {
		payerAccount_Field.clear();
		payerName_Field.clear();
		receiverAccount_Field.clear();
		receiverName_Field.clear();
		money_Field.clear();
		note_TextArea.clear();
		payment_DatePicker.setValue(LocalDate.now());
		item_ChoiceBox.setValue(item_ChoiceBox.getItems().get(0));

		// TODO test
		payerAccount_Field.setText("bill_gates");
		payerName_Field.setText("biergaici");
		receiverName_Field.setText("吴君如");
		receiverAccount_Field.setText("wjr");
		money_Field.setText("99");
		payment_DatePicker.setValue(LocalDate.of(2015, 12, 9));
		item_ChoiceBox.getSelectionModel().select(3);
		note_TextArea.setText("I have too much money");
	}

	public void commit(ActionEvent actionEvent) {
		OperationMessage msg = paymentBLService.submit(generatePaymentVO());
		if (msg.operationResult) {
			System.out.println("add successfully");
		} else {
			System.out.println("fail: " + msg.getReason());
		}
	}

	private PaymentVO generatePaymentVO() {
		return new PaymentVO(paymentBLService.getNewPaymentID(TimeConvert.getDisplayDate(Calendar.getInstance())),
				TimeConvert.convertDate(payment_DatePicker.getValue()), money_Field.getText(), null,
				payerName_Field.getText(), payerAccount_Field.getText(), null, receiverName_Field.getText(),
				receiverAccount_Field.getText(), item_ChoiceBox.getValue().getEnum(), note_TextArea.getText(),
				UserInfo.getUserID());
	}
}
