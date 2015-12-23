package cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import message.OperationMessage;

/** 
 * Server//cache//VersionSaver.java
 * @author CXWorks
 * @date 2015年12月22日 下午11:42:52
 * @version 1.0 
 */
public class VersionSaver {
	private static final String PATH = VersionSaver.class.getResource("/cache/").getPath();
	private static final String TAIL = ".2333";
	public OperationMessage saveVersion(long version,String fileName){
		String filePath = PATH+fileName+TAIL;
		File file=new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return new OperationMessage(false, e.getMessage());
			}
		}
		try {
			PrintWriter writer=new PrintWriter(new FileOutputStream(file, false));
			writer.println(version);
			writer.close();
			return new OperationMessage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}
	
	//
	public long loadVersion(String fileName){
		String filePath=PATH+fileName+TAIL;
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
			String re=reader.readLine();
			long ans=Long.parseLong(re);
			return ans;
		} catch (IOException e) {
			e.printStackTrace();
			return 0L;
		}
	}
}
