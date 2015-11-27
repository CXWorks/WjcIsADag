package vo;

import po.FormEnum;
import po.FormStateEnum;
import util.DataType;

/**
 * Created by Sissel on 2015/10/24.
 */
public class FormVO extends CommonVO{
    public FormEnum formType;
    public FormStateEnum state;
    public String formID;
    public FormVO(FormEnum type,FormStateEnum state,String formID){
    	super(DataType.FORM);
    	this.formType=type;
    	this.state=state;
    	this.formID=formID;
    }
	public FormEnum getFormType() {
		return formType;
	}
	public FormStateEnum getState() {
		return state;
	}
	public String getFormID() {
		return formID;
	}
    
}
