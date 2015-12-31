package ui.financeui;

import bl.blService.financeblService.PaymentBLService;
import factory.FinanceBLFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import message.OperationMessage;
import po.financedata.FinancePayEnum;
import tool.time.TimeConvert;
import tool.ui.Enum2ObservableList;
import tool.ui.SimpleEnumProperty;
import ui.common.checkFormat.FormatCheckQueue;
import ui.common.checkFormat.field.CheckIsNullTasker;
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

    public Button save_Btn;
    public Button clear_Btn;
    public Button commit_Btn;
	public TextField payerName_Field;
	public TextField payerAccount_Field;
	public TextField receiverAccount_Field;
	public TextField receiverName_Field;
	public TextField money_Field;
	public DatePicker payment_DatePicker;
	public TextArea note_TextArea;
	public ChoiceBox<SimpleEnumProperty<FinancePayEnum>> item_ChoiceBox;
	
	private FormatCheckQueue formatCheckQueueNotNull;

	private InformController informController;
    private PaymentBLService paymentBLService = FinanceBLFactory.getPaymentBLService();

	public static PaymentFormController launch() {
        try {
            FXMLLoader loader = new FXMLLoader(PaymentFormController.class.getResource("paymentForm.fxml"));
            Pane pane = loader.load();
            PaymentFormController controller = loader.getController();
            controller.informController = InformController.newInformController(pane);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }

		return null;
	}

    public static Parent launchInNew() {
        PaymentFormController controller = launch();
        return controller.informController.stackPane;
    }

    public static Parent launchInHistory(PaymentVO paymentVO) {
        PaymentFormController controller = launch();
        controller.save_Btn.setVisible(false);
        controller.clear_Btn.setVisible(false);
        controller.commit_Btn.setVisible(false);
        controller.showDetail(paymentVO);
        return controller.informController.stackPane;
    }

	@FXML
	public void initialize() {
		item_ChoiceBox.setItems(Enum2ObservableList.transit(FinancePayEnum.values()));
		
		clear(null);
		
		//init check
		formatCheckQueueNotNull=new FormatCheckQueue();
		formatCheckQueueNotNull.addTasker(
				new CheckIsNullTasker(money_Field),
				new CheckIsNullTasker(payerAccount_Field),
				new CheckIsNullTasker(payerName_Field),
				new CheckIsNullTasker(receiverAccount_Field),
				new CheckIsNullTasker(receiverName_Field));
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
	}

    public void showDetail(PaymentVO paymentVO){
        payerAccount_Field.setText(paymentVO.payerAccID);
        payerName_Field.setText(paymentVO.payerName);
        receiverAccount_Field.setText(paymentVO.receiverAccID);
        receiverName_Field.setText(paymentVO.receiverName);
        money_Field.setText(paymentVO.amount);
        note_TextArea.setText(paymentVO.note);
        payment_DatePicker.setValue(TimeConvert.convertCalendar(paymentVO.date));
        item_ChoiceBox.setValue(new SimpleEnumProperty<>(paymentVO.item));
    }

	public void commit(ActionEvent actionEvent) {
		//check first
		if (!this.formatCheckQueueNotNull.startCheck()) {
			return ;
		}
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
