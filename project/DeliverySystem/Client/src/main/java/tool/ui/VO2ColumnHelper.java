package tool.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import vo.CommonVO;
import vo.FormVO;
import vo.ordervo.OrderVO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sissel on 2015/11/24.
 */
public abstract class VO2ColumnHelper<T extends CommonVO> {

    public abstract Set<Map.Entry<String, String >> VO2Entries(T vo);

    public static void setKeyColumn(TableColumn<Map.Entry<String, String>, String> column){
        column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getKey())
        );
    }

    public static void setValueColumn(TableColumn<Map.Entry<String, String>, String> column){
        column.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getValue())
        );
    }
}
