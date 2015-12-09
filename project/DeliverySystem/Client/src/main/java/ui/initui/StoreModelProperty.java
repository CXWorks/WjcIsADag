package ui.initui;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import model.store.StoreModel;

/**
 * Created by Sissel on 2015/12/9.
 */
public class StoreModelProperty implements ObservableValue<String> {
    private StoreModel storeModel;

    public StoreModelProperty(StoreModel storeModel) {
        this.storeModel = storeModel;
    }

    public StoreModel getStoreModel() {
        return storeModel;
    }

    @Override
    public void addListener(ChangeListener<? super String> listener) {

    }

    @Override
    public void removeListener(ChangeListener<? super String> listener) {

    }

    @Override
    public String getValue() {
        return storeModel.getCenterID();
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    @Override
    public String toString() {
        return storeModel.getCenterID();
    }
}
