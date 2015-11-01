package po;

import java.io.Serializable;

public class FormPO extends CommonPO implements Serializable{
	private FormEnum formType;
	private FormStateEnum state;
	private String formID;
}
