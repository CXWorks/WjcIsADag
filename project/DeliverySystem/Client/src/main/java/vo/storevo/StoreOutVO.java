package vo.storevo;

import po.transportdata.TransportationEnum;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutVO extends FormVO {
    private String	orderID;
    private String	date;
    private String	destination;
    TransportationEnum transportation;
    private String	transID;
}
