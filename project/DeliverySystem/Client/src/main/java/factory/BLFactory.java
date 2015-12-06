package factory;

import tool.draft.DraftController;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

/** 
 * Client//factory//BLFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:20:27
 * @version 1.0 
 */
public abstract class BLFactory {
	protected static VOPOFactory vopoFactory=new VOPOFactory();
	protected static DraftService draftService=new DraftController();
}
