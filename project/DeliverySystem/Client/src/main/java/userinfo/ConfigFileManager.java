package userinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** 
 * Client//userinfo//ConfigFileManager.java
 * @author CXWorks
 * @date Dec 29, 2015 5:41:24 PM
 * @version 1.0 
 */
public class ConfigFileManager {

	private static final String PATH=ConfigFileManager.class.getResource("").getPath();
	private static final String FILE_NAME="2333.ini";
	
	
	public String ip;
	public String port;
	public String workpath;
	public ConfigFileManager() throws FileNotFoundException,IOException{
		File file=new File(PATH+FILE_NAME);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		BufferedReader reader=new BufferedReader(new FileReader(file));
		while (reader.ready()) {
			String text=reader.readLine();
			if (text.contains("-IP")) {
				ip=text.substring(4, text.length());
			}else if (text.contains("-PORT")) {
				
			}
			
		}
	}
}
