package tool.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import po.memberdata.StaffTypeEnum;
import util.EnumObservable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sissel on 2015/11/25.
 */
public class Enum2ObservableList {

    public static <E extends Enum<E> & EnumObservable<E>>
    ObservableList<SimpleEnumProperty<E>> transit(E[] enums){
        ObservableList<SimpleEnumProperty<E>> list = FXCollections.observableArrayList();
        for (E anEnum : enums) {
            list.add(new SimpleEnumProperty<E>(anEnum));
        }
        return list;
    }

}
