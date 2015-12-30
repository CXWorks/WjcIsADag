package bl.blService;

import message.CheckFormMessage;
import message.OperationMessage;
import vo.FormVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface FormBLService<T extends FormVO> {
	public String newID();

    public T loadDraft();

    public OperationMessage saveDraft(T form);

    public OperationMessage submit(T form);
    
    public ArrayList<CheckFormMessage> checkFormat(T form,boolean isFinal);
    
    public List<T> getHistory(String creatorID);

}
