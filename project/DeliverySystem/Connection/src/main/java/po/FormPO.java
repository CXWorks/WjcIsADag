package po;

import java.io.Serializable;

import po.receivedata.StateEnum;
import util.DataType;

public class FormPO extends CommonPO implements Serializable{
	protected FormEnum formType;
	protected  FormStateEnum formState;
	protected  String formID;
	
	/*表单创建者的ID*/
	protected String createrID;
	
	public FormPO(FormEnum formEnum,String formID){
		super(DataType.FORM);
		this.formType=formEnum;
		this.formState=FormStateEnum.CONSTRUCTED;
		this.formID=formID;
	}
	
	protected FormPO(FormEnum formEnum,String formID,String createrID){
		super(DataType.FORM);
		this.formType=formEnum;
		this.formState=FormStateEnum.CONSTRUCTED;
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
		case "CONSTRUCTED":
			this.formState = FormStateEnum.CONSTRUCTED;
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
