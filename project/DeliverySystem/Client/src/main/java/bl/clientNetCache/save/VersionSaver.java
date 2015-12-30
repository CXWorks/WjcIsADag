package bl.clientNetCache.save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import userinfo.UserInfo;
import message.OperationMessage;

/** 
 * Client//bl.clientNetCache.save//VersionSaver.java
 * @author CXWorks
 * @date 2015年12月21日 上午10:21:39
 * @version 1.0 
 */
public class VersionSaver {
	
	private final String tail=".2333";
	//
	public OperationMessage saveVersion(long version,String fileName){
		String path=UserInfo.getWorkPath();
		String filePath=path+fileName+tail;
		
		try {
			File file=new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter writer=new PrintWriter(new FileOutputStream(file,false));
			writer.println(version);
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
		String path=UserInfo.getWorkPath()+"cache/";
		String filePath=path+fileName+tail;
		try {
			File file=new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			BufferedReader reader=new BufferedReader(new FileReader(file));
			long ans=Long.parseLong(reader.readLine());
			return ans;
		}catch(FileNotFoundException e){
			return 0L;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0L;
		}
		
	}
}
