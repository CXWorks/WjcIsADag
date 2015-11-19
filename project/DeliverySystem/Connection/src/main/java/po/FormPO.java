package po;

import java.io.Serializable;

public class FormPO extends CommonPO implements Serializable{
	protected FormEnum formType;
	protected FormStateEnum state;
	protected String formID;
	public FormEnum getFormType() {
		return this.formType;
	}
}
