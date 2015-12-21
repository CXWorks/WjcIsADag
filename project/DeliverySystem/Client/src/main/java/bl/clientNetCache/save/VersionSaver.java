package bl.clientNetCache.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import message.OperationMessage;

/** 
 * Client//bl.clientNetCache.save//VersionSaver.java
 * @author CXWorks
 * @date 2015年12月21日 上午10:21:39
 * @version 1.0 
 */
public class VersionSaver {
	private final String path="cache/version/";
	private final String tail=".2333";
	//
	public OperationMessage saveVersion(long version,String fileName){
		String filePath=path+fileName+tail;
		try {
			ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(new File(filePath),false));
			writer.writeObject(version);
			writer.close();
			return new OperationMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}
	//
	public long loadVersion(String fileName){
		String filePath=path+fileName+tail;
		try {
			ObjectInputStream reader=new ObjectInputStream(new FileInputStream(new File(filePath)));
			Long ans=(Long)reader.readObject();
			return ans;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0L;
		}
	}
}
