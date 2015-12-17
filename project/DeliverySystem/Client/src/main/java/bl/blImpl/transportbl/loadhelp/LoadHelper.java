package bl.blImpl.transportbl.loadhelp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vo.ordervo.OrderVO;

/** 
 * Client//bl.blImpl.transportbl//LoadHelper.java
 * @author CXWorks
 * @date 2015年12月16日 下午4:17:42
 * @version 1.0 
 */
public class LoadHelper {
	
	private LoadService loadService;
	public Map<Boolean, List<Integer>> greedy(List<OrderVO> src){
		int[][] num=new int[src.size()][6];
		int index=0;
		for (OrderVO orderVO:src) {
			orderVO.calculateVolume();
			num[index][0]=orderVO.getLen();
			num[index][1]=orderVO.getWid();
			num[index][2]=orderVO.getHei();
			num[index][3]=orderVO.getV();
			num[index][4]=orderVO.getW();
			num[index][5]=orderVO.getFormIDInt();
			this.weihu(num[index]);
		}
		//bubble sort
		for (int i = 0; i < num.length-1; i++) {
			for (int j = i+1; j < num.length; j++) {
				if (!this.comp(num[i], num[j])) {
					this.swap(num[i], num[j]);
				}
			}
		}
		//
		return loadService.algorithm(num);
	}
	private void weihu(int[] src){
		for (int i = 0; i < 2; i++) {
			for (int j = i+1; j < 3; j++) {
				if (src[i]<src[j]) {
					int te=src[i];
					src[i]=src[j];
					src[j]=te;
				}
			}
		}
	}
	
	private boolean comp(int[] a,int[] b){
		if (a[3]>b[3]) {
			return true;
		}
		else if (a[3]<b[3]) {
			return false;
		}
		else {
			return a[4]>=b[4];
		}
	}
	
	//
	private void swap(int[] a,int[] b){
		int[] tep=new int[a.length];
		for (int i = 0; i < tep.length; i++) {
			tep[i]=a[i];
		}
		for (int i = 0; i < a.length; i++) {
			a[i]=b[i];
		}
		for (int i = 0; i < b.length; i++) {
			b[i]=tep[i];
		}
	}
	
	
	
	//
	
	
	
}
