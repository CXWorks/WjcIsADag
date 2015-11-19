package rmiImpl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import rmi.CommonDataService;

/**
 * 数据层表单常用操作，到达单、派件单、订单、中转单（装车）有关数据层服务的父类
 * @author wjc
 * @version 2015.10.31
 */
public abstract class CommonData<PO extends FormPO> extends UnicastRemoteObject implements CommonDataService<PO> {
	
	public CommonData() throws RemoteException {
		
	}
	
}
