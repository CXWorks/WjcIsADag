package po;

import java.io.Serializable;

import po.receivedata.StateEnum;
import util.DataType;

public class FormPO extends CommonPO implements Serializable{
	protected FormEnum formType;
	private FormStateEnum formState;
	private String formID;
	
	public FormPO(){
		super(DataType.FORM);
		this.formState=FormStateEnum.SUBMIT;
		formID="555555555";
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
		switch (formState) {
		case "DRAFT":
			this.formState = FormStateEnum.DRAFT;
			break;
		case "PASS":
			this.formState = FormStateEnum.PASS;
			break;
		case "SUBMIT":
			this.formState = FormStateEnum.SUBMIT;
			break;
		}
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
