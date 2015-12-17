package bl.blImpl.transportbl.loadhelp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vo.ordervo.OrderVO;

/** 
 * Client//bl.blImpl.transportbl.loadhelp//Greedy.java
 * @author CXWorks
 * @date 2015年12月17日 下午5:09:19
 * @version 1.0 
 */
public class Greedy implements LoadService {

	/* (non-Javadoc)
	 * @see bl.blImpl.transportbl.loadhelp.LoadService#algorithm()
	 */
	@Override
	/**
	 * 贪心实现
	 * @param src 第一行为商品，接着为长、宽、高、体积\重量\ID
	 * @return 返回车辆编号与商品顺序的map
	 */
	public Map<Boolean, List<Integer>> algorithm(int src[][]){
		Map<Boolean, List<Integer>> ans=new HashMap<Boolean, List<Integer>>();
		LinkedList<int[]> avaliable=new LinkedList<int[]>();
		int w=0;//质量已占用
		List<Integer> in=new LinkedList<Integer>();
		List<Integer> out=new LinkedList<Integer>();
		//
		int i=0;
		for (; i < src.length; i++) {
			boolean flag=false;
			if (this.checkWeight(w, src[i][4])) {
				for (int j = 0; j < avaliable.size(); j++) {
					int[] po=avaliable.get(j);
					if (this.checkCanIn(po, src[i])) {
						flag=true;
						if (po[0]+src[i][0]<CAR_LEN) {
							int[] te={po[0]+src[i][0],po[1],po[2]};
							avaliable.add(te);
						}
						//
						if (po[1]+src[i][1]<CAR_WIDTH) {
							int[] te={po[0],po[1]+src[i][1],po[2]};
							avaliable.add(te);
						}
						//
						if (po[2]+src[i][2]<CAR_H) {
							int[] te={po[0],po[1],po[2]+src[i][2]};
							avaliable.add(te);
						}
						//
						avaliable.remove(po);
					}
				}
			}
			//
			if (flag) {
				in.add(i);
				w+=src[i][4];
			}
			else {
				out.add(i);
			}
		}
		//
		avaliable.clear();
		//
		ans.put(true, in);
		ans.put(false, out);
		return ans;
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
	private boolean checkWeight(int now,int tar){
		return now+tar<=MAX_LOAD;
	}
	private boolean checkCanIn(int position[],int tar[]){
		return position[0]+tar[0]<=CAR_LEN&&position[1]+tar[1]<=CAR_WIDTH&&position[2]+tar[2]<=CAR_H;
	}
}
