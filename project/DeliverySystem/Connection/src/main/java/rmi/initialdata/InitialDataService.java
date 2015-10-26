package rmi.initialdata;

import message.OperationMessage;
import po.initialdata.InitialDataPO;

import java.rmi.Remote;

/**
 * Created by Sissel on 2015/10/26.
 */
public interface InitialDataService extends Remote {

    public InitialDataPO getInitialDataPO(String version);

    /**
     * 期初建账时先调用这个方法，系统进入建账中状态，不回应期初建账外其他的操作
     * @param staffID 进行建账的人员，只有他能继续操作
     * @return
     */
    public OperationMessage requestInitData(String staffID);

    /**
     * 调用过前面方法后，将新的期初修改过的期初建账上传
     * @param staffID 调用requestInitData的人员
     * @param newData 修改过的期初信息，将这个作为新的期初建账
     * @return
     */
    public OperationMessage uploadInitialData(String staffID, InitialDataPO newData);

    /**
     * 终止系统建账中的状态，用之前的帐继续运行
     * @param staffID 有相应权限的人员
     * @return
     */
    public OperationMessage abortInitData(String staffID);

}
