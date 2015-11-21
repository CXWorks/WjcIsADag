package po;

import java.io.Serializable;

import po.receivedata.StateEnum;
import util.DataType;

public class FormPO extends CommonPO implements Serializable{
	private FormEnum formType;
	private FormStateEnum formState;
	private String formID;
	
	public FormPO(){
		super(DataType.FORM);
	}
	public FormPO(FormEnum formEnum){
		super(DataType.FORM);
		this.formType=formEnum;
	}
	
	public FormEnum getFormType() {
		return this.formType;
	}
	public FormStateEnum getFormState() {
		return formState;
	}
	public void setFormState(String formState) {
		if(formState.equalsIgnoreCase("DRAFT"))
			this.formState = FormStateEnum.DRAFT;
		else if(formState.equalsIgnoreCase("PASS"))
			this.formState = FormStateEnum.PASS;
		else if(formState.equalsIgnoreCase("SUBMIT"))
			this.formState = FormStateEnum.SUBMIT;
	}
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}
	public void setFormType(FormEnum formType) {
		this.formType = formType;
	}
}
