package ui.formHistoryui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import vo.FormVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/12/30.
 */
public class ViewFormDetailController {
    public AnchorPane contentFather;
    public AnchorPane blockPane;

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
        switch (formVO.getFormType()){
            case ORDER:
                break;
            case DELIVER:
                break;
            case PAYMENT:
                break;
            case REVENUE:
                break;
            case RECEIVE:
                break;
            case TRANSPORT_HALL:
                break;
            case TRANSPORT_CENTER:
                break;
            case STORE_IN:
                break;
            case STORE_OUT:
                break;
        }
        return null;
    }
}
