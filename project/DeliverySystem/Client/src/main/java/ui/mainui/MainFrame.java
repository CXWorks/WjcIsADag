package ui.mainui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.configurationui.ConfigurationPanel;
import ui.selfdefined.MyFrame;
import ui.selfdefined.MyPanel;

/** 
 * Client//ui.mainui//MainFrame.java
 * @author CXWorks
 * @date 2015年10月25日 下午11:57:23
 * @version 1.0 
 */
public class MainFrame extends MyFrame {
	public MainFrame(JPanel jPanel){
		super();
		//
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setBounds(width/2-400, height/2-500, 800, 1000);
		//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		this.add(jPanel);
		//
		this.setVisible(true);
	}
	//
	public static void main(String[] args){
		new MainFrame(new ConfigurationPanel());
	}
}
