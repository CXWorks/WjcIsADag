package po.memberdata;

import po.CommonPO;
import util.EnumObservable;

import java.io.Serializable;

public enum StaffTypeEnum implements Serializable, EnumObservable<StaffTypeEnum>{

    DRIVER("司机"),
    MANAGER("经理"),
    ADMINISTRATOR("系统管理员"),
    DELIVER("快递员"),
    HALL_COUNTERMAN("营业厅业务员"),
    CENTER_COUNTERMAN("中转中心业务员"),
    STOREMAN("仓库管理员"),
    BURSAR("财务人员");

    private String chinese;

    StaffTypeEnum(String c){this.chinese = c;}

    public String getChinese(){
        return this.chinese;
    }

}
