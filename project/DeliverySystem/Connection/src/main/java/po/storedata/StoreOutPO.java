package po.storedata;

import po.FormPO;
import po.transportdata.TransportationEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutPO extends FormPO {
    private String	orderID;
    private String	date;
    private String	destination;
    TransportationEnum transportation;
    private String	transID;

}
