package model.store;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreLocation implements Serializable{
    StoreAreaCode area;
    int row;
    int shelf;
    int position;
    String orderID;
}
