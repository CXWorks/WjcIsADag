package ui.configurationui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;

import blImpl.configurationbl.ConfigurationblImpl;
import blService.configurationblService.ConfigurationblService;
import ui.selfdefined.MyButton;
import ui.selfdefined.MyPanel;

/** 
 * Client//ui.configurationui//ConfigurationPanel.java
 * @author CXWorks
 * @date 2015年10月26日 上午12:11:35
 * @version 1.0 
 */
public class ConfigurationPanel extends MyPanel implements ActionListener{
	
	private JButton b1;
	private JButton b2;
	
	private JButton search_pack;
	private JButton search_price;
	
	private ConfigurationblService cbls;
	
	public ConfigurationPanel(){
		super();
		//
		try {
			cbls = new ConfigurationblImpl();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		b1=new JButton("search");
		b1.setActionCommand("search");
		b1.setBounds(300, 200, 100, 100);
		b1.addActionListener(this);
		this.add(b1);
		//
		b2=new JButton("modify");
		b2.setActionCommand("modify");
		b2.setBounds(300, 350, 100, 100);
		b2.addActionListener(this);
		this.add(b2);
		
		//
		search_pack=new JButton("pack");
		search_pack.setActionCommand("search_pack");
		search_pack.setBounds(200, 50, 100, 50);
		search_pack.addActionListener(this);
		//
		search_price=new JButton("price");
		search_price.setActionCommand("search_price");
		search_price.setBounds(300, 50, 100, 50);
		search_price.addActionListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="search"){
			this.removeAll();
			this.add(search_pack);
			this.add(search_price);
			this.repaint();
		}else if(e.getActionCommand()=="search_pack"){
			this.removeAll();
			cbls.getPack();
			
			this.repaint();
		}
	}
}
