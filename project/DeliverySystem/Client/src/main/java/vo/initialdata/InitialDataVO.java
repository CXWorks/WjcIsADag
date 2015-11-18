package vo.initialdata;

import model.store.StoreModel;
import vo.InfoVO;
import vo.financevo.BankAccountVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;

import java.util.ArrayList;
import java.util.List;

import po.InfoEnum;
import po.initialdata.InitialDataPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitialDataVO extends InfoVO{
    String version;
    String dbName;

    ArrayList<StoreModel> storeModels;
    ArrayList<BankAccountVO> bankAccounts;
    ArrayList<CarVO> cars;
    ArrayList<StaffVO> staffs;
    ArrayList<HallVO> halls;
    ArrayList<CenterVO> centers;
    public InitialDataVO(){
    	super(InfoEnum.INITIAL_DATA);
    }
    public InitialDataVO(InitialDataPO po){
    	this();
    }
}
