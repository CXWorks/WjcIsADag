package ui.formHistoryui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import ui.common.checkFormat.field.FloatOnlyField;
import ui.financeui.PaymentFormController;
import ui.orderui.NewOrderController;
import ui.receiveui.ReceiveFormController;
import ui.storeui.StoreInFormController;
import ui.storeui.StoreOutFormController;
import ui.transportui.LoadCarController;
import ui.transportui.TransitFormController;
import vo.FormVO;
import vo.financevo.PaymentVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/30.
 */
public class ViewFormDetailController {
    public AnchorPane contentFather;

    private FormVO formVO;

    public static Stage launch(FormVO form){
        Stage stage = new Stage();
        stage.initOwner(Main.primaryStage);

        try {
            FXMLLoader loader = new FXMLLoader(ViewFormDetailController.class.getResource("viewFormDetail.fxml"));
            Pane outerPane = loader.load();
            ViewFormDetailController controller = loader.getController();
            controller.formVO = form;
            controller.contentFather.getChildren().add(controller.getContentByType());
            stage.setScene(new Scene(outerPane));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;
    }

    public Parent getContentByType(){
        Parent result = null;
        switch (formVO.getFormType()){
            case ORDER:
                result = NewOrderController.launchInHistory((OrderVO)formVO);
                break;
            case DELIVER:
                break;
            case PAYMENT:
                result = PaymentFormController.launchInHistory((PaymentVO)formVO);
                break;
            case REVENUE:
                break;
            case RECEIVE:
                result = ReceiveFormController.launchInHistory((ReceiveVO)formVO);
                break;
            case LOAD_CAR:
                result = LoadCarController.launchInHistory((LoadVO)formVO);
                break;
            case CENTER_TRANSPORT:
                result = TransitFormController.launchInHistory((CenterOutVO)formVO);
                break;
            case STORE_IN:
                result = StoreInFormController.launchInHistory((StoreInVO)formVO);
                break;
            case STORE_OUT:
                result = StoreOutFormController.launchInHistory((StoreOutVO)formVO);
                break;
        }
        return result;
    }
}
