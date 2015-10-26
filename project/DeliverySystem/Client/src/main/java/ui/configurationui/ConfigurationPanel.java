package ui.configurationui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;

import po.configurationdata.enums.DeliveryTypeEnum;
import po.configurationdata.enums.PackEnum;
import blImpl.configurationbl.ConfigurationblImpl;
import blService.configurationblService.ConfigurationblService;
import ui.selfdefined.MyButton;
import ui.selfdefined.MyPanel;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;

/** 
 * Client//ui.configurationui//ConfigurationPanel.java
 * @author CXWorks
 * @date 2015年10月26日 上午12:11:35
 * @version 1.0 
 */
public class ConfigurationPanel extends MyPanel implements ActionListener{
	
	private int state = 0;
	
	private JButton b1;
	private JButton b2;
	
	private PackVO packVO;
	private PriceVO priceVO;
	private ProportionVO proportionVO;
	private ArrayList<CityDistanceVO> cityDistanceVO;
	private ArrayList<SalaryStrategyVO> salaryStrategyVO;
	
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
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		if(state==1){
			g.drawString("快递类型",400,100);
			g.drawString("价格",500,100);
			
			g.drawString("经济",400,130);
			g.drawString("标准",400,160);
			g.drawString("特快",400,190);
			
			g.drawString(priceVO.getByType(DeliveryTypeEnum.ECONOMIC)+"",500,130);
			g.drawString(priceVO.getByType(DeliveryTypeEnum.USUAL)+"",500,160);
			g.drawString(priceVO.getByType(DeliveryTypeEnum.FAST)+"",500,190);
			
			g.drawString("包装类型",100,100);
			g.drawString("价格",200,100);
			
			g.drawString("快递袋",100,130);
			g.drawString("纸箱",100,160);
			g.drawString("木箱",100,190);
			
			g.drawString(packVO.getByType(PackEnum.PACKAGE)+"",200,130);
			g.drawString(packVO.getByType(PackEnum.PAPER)+"",200,160);
			g.drawString(packVO.getByType(PackEnum.WOOD)+"",200,190);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="search"){
			this.removeAll();
			state = 1;
			packVO = cbls.getPack();
			priceVO = cbls.getPrice();
//			cbls.getProportion();
//			cbls.getCityDistance();
//			cbls.getSalaryStrategy();
			this.repaint();
		}
	}
}
