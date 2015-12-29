package tool.draft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import po.FormEnum;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.FormVO;

/** 
 * Client//tool.draft//DraftController.java
 * @author CXWorks
 * @date 2015年11月19日 下午7:51:25
 * @version 1.0 
 */
public class DraftController implements DraftService {
	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	/* (non-Javadoc)
	 * @see tool.draft.DraftService#saveDraft(vo.FormVO)
	 */
	@Override
	public OperationMessage saveDraft(FormVO vo) {
		try {
			String ROOT=UserInfo.getWorkPath();
			File file=new File(ROOT+vo.getFormType().toString()+".2333");
			
			if (!file.exists()) {
				System.out.println("saving draft");
				System.out.println(file.createNewFile());
			}
			writer=new ObjectOutputStream(new FileOutputStream(file,false));
			writer.writeObject(vo);
			writer.close();
			return new OperationMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return new OperationMessage(false,e.getMessage());
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see tool.draft.DraftService#getDraft(po.FormEnum)
	 */
	@Override
	public FormVO getDraft(FormEnum formEnum) {
		try {
			String ROOT=UserInfo.getWorkPath();
			File file=new File(ROOT+formEnum.toString()+".2333");
			if (!file.exists()) {
				return null;
			}
			reader=new ObjectInputStream(new FileInputStream(file));
			FormVO ans=(FormVO)reader.readObject();
			reader.close();
			return ans;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
