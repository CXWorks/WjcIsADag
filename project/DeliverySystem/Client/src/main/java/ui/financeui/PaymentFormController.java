package ui.financeui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/24.
 */
public class PaymentFormController {
    public TextField payerName_Field;
    public TextField payerAccount_Field;
    public TextField receiverAccount_Field;
    public TextField receiverName_Field;
    public TextField money_Field;
    public DatePicker payment_DatePicker;
    public TextArea note_TextArea;
    public ChoiceBox item_ChoiceBox;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(PaymentFormController.class.getResource("paymentForm.fxml"));
    }

    public void saveDraft(ActionEvent actionEvent) {
    }

    public void clear(ActionEvent actionEvent) {
    }

    public void sure(ActionEvent actionEvent) {
    }
}
