package model.store;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModelOperation {
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
