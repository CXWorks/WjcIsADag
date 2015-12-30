package userinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/** 
 * Client//userinfo//ConfigFileManager.java
 * @author CXWorks
 * @date Dec 29, 2015 5:41:24 PM
 * @version 1.0 
 */
public class ConfigFileManager {

	private static final String PATH=ConfigFileManager.class.getResource("").getPath();
	private static final String FILE_NAME="2333.ini";
	private String ip;
	private String port;
	private String workpath;
	public ConfigFileManager() throws FileNotFoundException,IOException{
		File file=new File(PATH+FILE_NAME);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		BufferedReader reader=new BufferedReader(new FileReader(file));
		ip=reader.readLine();
		port=reader.readLine();
		workpath=reader.readLine();
		reader.close();
		
	}
	//
	public String getIp() {
		return ip;
	}
	public String getPort() {
		return port;
	}
	public String getWorkpath() {
		return workpath;
	}
	public void setIp(String ip) {
		this.ip = ip;
		write();
	}
	public void setPort(String port) {
		this.port = port;
		write();
	}
	public void setWorkpath(String workpath) {
		this.workpath = workpath;
		write();
	}
	private void write() {
		File file=new File(PATH+FILE_NAME);
		try{
		PrintWriter writer=new PrintWriter(new FileOutputStream(file,false));
		writer.println(ip);
		writer.println(port);
		writer.println(workpath);
		writer.close();
		}catch(IOException e){
			
		}
	}
}
