package model.examine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import message.OperationMessage;
import po.FormPO;

public  class ExamineQueue {
	private Queue<FormPO> queue;

	public ExamineQueue() {
		queue = new LinkedList<FormPO>();
	}

	public synchronized OperationMessage addForm(FormPO po) {
		this.queue.add(po);
		return new OperationMessage();
	}

	public synchronized FormPO removeForm() {
		return this.queue.remove();
	}

	public synchronized ArrayList<FormPO> removeForms() {
		ArrayList<FormPO> pos = new ArrayList<FormPO>();
		while(!this.queue.isEmpty())
			pos.add(this.queue.remove());
		return pos;
	}
}
