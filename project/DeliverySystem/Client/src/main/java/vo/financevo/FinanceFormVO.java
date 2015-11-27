package vo.financevo;

import po.FormEnum;
import po.FormStateEnum;
import vo.FormVO;

import java.util.Calendar;

/**
 * Created by Sissel on 2015/11/27.
 */
public abstract class FinanceFormVO extends FormVO {
    public FinanceFormVO(FormEnum type, FormStateEnum state, String formID) {
        super(type, state, formID);
    }

    public Calendar date;
    public String amount;
}
