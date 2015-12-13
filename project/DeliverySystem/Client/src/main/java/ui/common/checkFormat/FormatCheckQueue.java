package ui.common.checkFormat;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/12/12.
 */
public class FormatCheckQueue {

    private List<Checkable> checkableList = new LinkedList<>();

    public void addTasker(Checkable...checkable){
        for (Checkable checkable1 : checkable) {
            checkableList.add(checkable1);
        }
    }

    public boolean startCheck(){
        boolean success = true;
        for (Checkable checkable : checkableList) {
            success = checkable.check() && success;
        }
        return success;
    }

}
