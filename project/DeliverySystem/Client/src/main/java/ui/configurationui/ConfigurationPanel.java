package ui.configurationui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.selfdefined.MyButton;
import ui.selfdefined.MyPanel;

/** 
 * Client//ui.configurationui//ConfigurationPanel.java
 * @author CXWorks
 * @date 2015年10月26日 上午12:11:35
 * @version 1.0 
 */
public class ConfigurationPanel extends MyPanel implements ActionListener{
	
	public ConfigurationPanel(){
		super();
		//
		JButton b1=new JButton("search");
		b1.setActionCommand("search");
		b1.setBounds(300, 200, 100, 100);
		b1.addActionListener(this);
		this.add(b1);
		//
		JButton b2=new JButton("modify");
		b2.setActionCommand("modify");
		b2.setBounds(300, 350, 100, 100);
		b2.addActionListener(this);
		this.add(b2);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="search"){
			
		}
	}
}
