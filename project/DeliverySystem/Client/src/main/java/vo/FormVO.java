package vo;

import po.FormEnum;
import po.FormStateEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class FormVO {
    private FormEnum formType;
    private FormStateEnum state;
    private String formID;
    public FormVO(){}
    public FormVO(FormEnum type,FormStateEnum state,String formID){
    	this.formType=type;
    	this.state=state;
    	this.formID=formID;
    }
}
