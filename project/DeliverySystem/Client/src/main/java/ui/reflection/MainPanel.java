package ui.reflection;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.*;

import vo.FormVO;

/** 
 * Client//ui.reflection//a.java
 * @author CXWorks
 * @date 2015年10月26日 上午10:30:10
 * @version 1.0 
 */
public class MainPanel extends JPanel{

	/**
	 * default serial UID
	 */
	private static final long serialVersionUID = 1L;
	//Buttons,text of buttons and the methods which buttons call
	/**
	 * All Buttons
	 */
	JButton buttons[];
	/**
	 * Text of buttons
	 */
	ArrayList<String> buttonString=new ArrayList<String>();
	/**
	 * Methods' names which buttons call
	 */
	ArrayList<String> allMethodNames=new ArrayList<String>();
	//
	ArrayList<Class<?>> myClass;
	ArrayList<Object> myGod;
	ArrayList<Method[]> methods;
	int size=0;
	public MainPanel(){
		//Initiate panel
		initPanel();
	}
	//
	public void addClass(ArrayList<Class<?>> temp,ArrayList<Object> myGod){
		this.myClass=temp;
		this.myGod=myGod;
		//
		methods=new ArrayList<Method[]>();
		for (int i = 0; i < myClass.size(); i++) {
			methods.add(myClass.get(i).getDeclaredMethods());
			size+=myClass.get(i).getMethods().length;
		}
		initButtons();
		//Set panel visible
		setVisible(true);
	}
	//
	private void initPanel() {
		//Set layout to FlowLayout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
	}
	
	private void initButtons() {
		//获得按钮的个数
		buttons = new JButton[size];
		int index=0;
		Method[] temp;
		for (int i = 0; i < methods.size(); i++) {
			temp=methods.get(i);
			for (int j = 0; j < temp.length; j++) {
				buttons[index] = new JButton();
				buttons[index].setText(myClass.get(i).getName()+" "+temp[j].getName());
				// 添加监听类
				buttons[index]
						.addMouseListener((MouseListener) new TestMethodAdapter(
								temp[j],myClass.get(i),myGod.get(i)));
				buttons[index].setVisible(true);
				this.add(buttons[index]);
				index++;
			}
		}
	}
	
	
	
	class TestMethodAdapter extends MouseAdapter{
		//方法名
		private Method methodName;
		private Class<?> src;
		private Object shit;
		
		public TestMethodAdapter(Method methodName,Class<?> src,Object shit){
			this.methodName=methodName;
			this.src=src;
			this.shit=shit;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				src
				//根据方法名和参数列表得到相应方法（测试时参数为空）
				.getMethod(methodName.getName(), methodName.getParameterTypes())
				//调用方法
				.invoke(shit, (Object)null);
				System.out.println("run "+methodName.getName());
			} catch (IllegalArgumentException e1) {
				try {
					src
					//根据方法名和参数列表得到相应方法（测试时参数为空）
					.getMethod(methodName.getName(), methodName.getParameterTypes())
					//调用方法
					.invoke(shit);
					System.out.println("run "+methodName.getName());
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					
				} catch (IllegalArgumentException e2) {
					// TODO Auto-generated catch block
					System.out.println("test");
					try {
						src
						//根据方法名和参数列表得到相应方法（测试时参数为空）
						.getMethod(methodName.getName(), methodName.getParameterTypes())
						//调用方法
						.invoke(shit,new Object[]{null,new Boolean(true)});
						System.out.println("run "+methodName.getName());
					} catch (IllegalAccessException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (IllegalArgumentException e3) {
						// TODO Auto-generated catch block
						try {
							src
							//根据方法名和参数列表得到相应方法（测试时参数为空）
							.getMethod(methodName.getName(), methodName.getParameterTypes())
							//调用方法
							.invoke(shit,new Object[]{null,null,null,null});
							System.out.println("run "+methodName.getName());
						} catch (IllegalAccessException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						} catch (IllegalArgumentException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						} catch (InvocationTargetException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						} catch (NoSuchMethodException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						} catch (SecurityException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
						System.out.println("run "+methodName.getName());
					} catch (InvocationTargetException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (NoSuchMethodException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (SecurityException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				} catch (InvocationTargetException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NoSuchMethodException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SecurityException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

