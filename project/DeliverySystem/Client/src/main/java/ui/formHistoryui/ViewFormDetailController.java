package ui.formHistoryui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
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
import vo.transitvo.TransitVO;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Sissel on 2015/12/30.
 */
public class ViewFormDetailController {
    public AnchorPane contentFather;
    public Stage stage;

    private FormVO formVO;

    public static ViewFormDetailController launch(){
        Stage stage = new Stage();
        stage.initOwner(Main.primaryStage);

        try {
            FXMLLoader loader = new FXMLLoader(ViewFormDetailController.class.getResource("viewFormDetail.fxml"));
            Pane outestPane = loader.load();
            stage.setScene(new Scene(outestPane));
            ViewFormDetailController controller = loader.getController();
            controller.stage = stage;

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Stage launchInHistory(FormVO form){
        ViewFormDetailController controller = launch();
        controller.formVO = form;
        controller.contentFather.getChildren().add(controller.getHistoryByType());

        return controller.stage;
    }

    /**
     *
     * @param form
     * @param formVOs : remove the old form and add back the new one
     * @return
     */
    public static Stage launchInManagerModify(FormVO form, Collection<FormVO> formVOs){
        ViewFormDetailController controller = launch();
        controller.formVO = form;
        controller.contentFather.getChildren().add(controller.getManagerByType(formVOs));

        return controller.stage;
    }

    public Parent getManagerByType(Collection<FormVO> formVOs) {
        Parent result = null;
        switch (formVO.getFormType()){
            case ORDER:
                result = NewOrderController.launchInManagerEdit((OrderVO)formVO, formVOs);
                break;
            case DELIVER:
                break;
            case PAYMENT:
                result = PaymentFormController.launchInManagerEdit((PaymentVO)formVO, formVOs);
                break;
            case REVENUE:
                break;
            case RECEIVE:
                result = ReceiveFormController.launchInManagerEdit((ReceiveVO)formVO, formVOs);
                break;
            case LOAD_CAR:
                result = LoadCarController.launchInManagerEdit((LoadVO)formVO, formVOs);
                break;
            case CENTER_TRANSPORT:
                result = TransitFormController.launchInManagerEdit((CenterOutVO)formVO, formVOs);
                break;
            case STORE_IN:
                result = StoreInFormController.launchInManagerEdit((StoreInVO)formVO, formVOs);
                break;
            case STORE_OUT:
                result = StoreOutFormController.launchInManagerEdit((StoreOutVO)formVO, formVOs);
                break;
        }
        return result;
    }

    public Parent getHistoryByType(){
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
