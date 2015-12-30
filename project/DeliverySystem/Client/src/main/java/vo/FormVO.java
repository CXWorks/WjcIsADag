package vo;

import java.util.Calendar;
import java.util.function.Predicate;

import po.FormEnum;
import po.FormStateEnum;
import util.DataType;

/**
 * Created by Sissel on 2015/10/24.
 */
public abstract class FormVO extends CommonVO {
	public Calendar date;
    public FormEnum formType;
    public FormStateEnum state;
    public String formID;
    /*表单创建者的ID*/
	protected String createrID;
    protected FormVO(FormEnum type,FormStateEnum state,String formID,String createrID){
    	super(DataType.FORM);
    	this.formType=type;
    	this.state=state;
    	this.formID=formID;
    	this.createrID=createrID;
    }
	public String getCreaterID() {
		return createrID;
	}
	public void setCreaterID(String createrID) {
		this.createrID = createrID;
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
    public abstract String getMainInfo();
}
