package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormEnum;
import po.FormPO;
import po.deliverdata.DeliverPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import rmi.chatRemindService.ChatNewService;
import rmi.examineService.ExamineManageService;
import rmiImpl.chatRemindImpl.Reminder;

public class ExamineManageImpl extends UnicastRemoteObject implements
		ExamineManageService {
	private static final long serialVersionUID = 1L;
	/* 表单队列在此类中产生，将引用赋给ExamineSubmitImpl */
	private volatile ExamineQueue queue;
	/*负责新建消息*/
	private ChatNewService addMessage;
	
	private PassHelper pass_helperHelper;

	public ExamineManageImpl() throws RemoteException, MalformedURLException {
		super();
		this.queue = new ExamineQueue();
		addMessage = new Reminder();
		pass_helperHelper = new PassHelper();
	}

	public ExamineQueue getQueue() {
		return queue;
	}

	public ArrayList<FormPO> getForms() {
		// TODO Auto-generated method stub
		return queue.removeForms();
	}

	public OperationMessage modifyForm(FormPO form) throws RemoteException {
		// TODO Auto-generated method stub
		FormEnum type = form.getFormType();
		OperationMessage result;
		switch (type) {
		case DELIVER:
			result = PassHelper.getDeliverDataService()
					.update((DeliverPO) form);
			break;
		case ORDER:
			result = PassHelper.getOrderDataService().update((OrderPO) form);
			break;
		case PAYMENT:
			result = PassHelper.getPaymentDataService()
					.update((PaymentPO) form);
			break;
		case RECEIVE:
			result = PassHelper.getReceiveDataService()
					.update((ReceivePO) form);
			break;
		case REVENUE:
			result = PassHelper.getRevenueDataService()
					.update((RevenuePO) form);
			break;
		case STORE_IN:
			result = PassHelper.getStoreFormDataService().updateStoreInPO(
					(StoreInPO) form);
			break;
		case STORE_OUT:
			result = PassHelper.getStoreFormDataService().updateStoreOutPO(
					(StoreOutPO) form);
			break;
		case TRANSPORT_CENTER:
			result = PassHelper.getTransportDataService().update(
					(CenterOutPO) form);
			break;
		case TRANSPORT_HALL:
			result = PassHelper.getLoadDataService().update((LoadPO) form);
			break;
		default:
			return new OperationMessage(false, "表单隐藏信息有问题");
		}
		return result;
	}

	public OperationMessage passForm(ArrayList<FormPO> forms)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage mes = new OperationMessage();
		for (FormPO tmp : forms) {
			OperationMessage result = new OperationMessage();
			FormEnum type = tmp.getFormType();
			switch (type) {
			case DELIVER:
				result = PassHelper.getDeliverDataService().insert(
						(DeliverPO) tmp);
				break;
			case ORDER:
				result = PassHelper.getOrderDataService().insert((OrderPO) tmp);
				break;
			case PAYMENT:
				result = PassHelper.getPaymentDataService().insert(
						(PaymentPO) tmp);
				break;
			case RECEIVE:
				result = PassHelper.getReceiveDataService().insert(
						(ReceivePO) tmp);
				break;
			case REVENUE:
				result = PassHelper.getRevenueDataService().insert(
						(RevenuePO) tmp);
				break;
			case STORE_IN:
				result = PassHelper.getStoreFormDataService().insertStoreInPO(
						(StoreInPO) tmp);
				break;
			case STORE_OUT:
				result = PassHelper.getStoreFormDataService().insertStoreOutPO(
						(StoreOutPO) tmp);
				break;
			case TRANSPORT_CENTER:
				result = PassHelper.getTransportDataService().insert(
						(CenterOutPO) tmp);
				break;
			case TRANSPORT_HALL:
				result = PassHelper.getLoadDataService().insert((LoadPO) tmp);
				break;
			default:
				result = new OperationMessage(false, "表单隐藏信息有问题");
			}
			if (!result.operationResult) {
				mes.operationResult = false;
				mes.addReason(tmp.getFormID());
			}
		}
		return mes;
	}

	public OperationMessage deleteForm(ArrayList<FormPO> forms)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage mes = new OperationMessage();
		for (FormPO tmp : forms) {
			OperationMessage result = new OperationMessage();
			FormEnum type = tmp.getFormType();
			switch (type) {
			case DELIVER:
				result = PassHelper.getDeliverDataService().delete(
						tmp.getFormID());
				break;
			case ORDER:
				result = PassHelper.getOrderDataService().delete(
						tmp.getFormID());
				break;
			case PAYMENT:
				result = PassHelper.getPaymentDataService().delete(
						tmp.getFormID());
				break;
			case RECEIVE:
				result = PassHelper.getReceiveDataService().delete(
						tmp.getFormID());
				break;
			case REVENUE:
				result = PassHelper.getRevenueDataService().delete(
						tmp.getFormID());
				break;
			case STORE_IN:
				result = PassHelper.getStoreFormDataService().deleteStoreInPO(
						tmp.getFormID());
				break;
			case STORE_OUT:
				result = PassHelper.getStoreFormDataService().deleteStoreOutPO(
						tmp.getFormID());
				break;
			case TRANSPORT_CENTER:
				result = PassHelper.getTransportDataService().delete(
						tmp.getFormID());
				break;
			case TRANSPORT_HALL:
				result = PassHelper.getLoadDataService()
						.delete(tmp.getFormID());
				break;
			default:
				result = new OperationMessage(false, "表单隐藏信息有问题");
			}
			if (!result.operationResult) {
				mes.operationResult = false;
				mes.addReason(tmp.getFormID());
			}
		}
		return mes;
	}

}
