package model.store;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModelOperation implements Serializable{
    public enum Type {
        ADD_ROW,
        DELETE_ROW,
        EDIT_ROW,
        REDUCE_PART,
        EXPAND_PART
    }

    Type operationType;
    StoreLocation	location;
    int	param_size;
}
