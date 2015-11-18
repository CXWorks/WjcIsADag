package vo;

import po.FormEnum;
import po.FormStateEnum;
import util.DataType;

/**
 * Created by Sissel on 2015/10/24.
 */
public class FormVO extends CommonVO{
    protected FormEnum formType;
    protected FormStateEnum state;
    protected String formID;
    public FormVO(){
    	super(DataType.FORM);
    }
    public FormVO(FormEnum type){
    	super(DataType.FORM);
    	this.formType=type;
    }
    public FormVO(FormEnum type,FormStateEnum state,String formID){
    	this(type);
    	this.state=state;
    	this.formID=formID;
    }
}
