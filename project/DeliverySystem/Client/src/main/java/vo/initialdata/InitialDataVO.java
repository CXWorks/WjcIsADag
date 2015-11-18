package vo.initialdata;

import model.store.StoreModel;
import vo.InfoVO;
import vo.financevo.BankAccountVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;

import java.util.List;

import po.InfoEnum;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitialDataVO extends InfoVO{
    String version;
    String dbName;

    List<StoreModel> storeModels;
    List<BankAccountVO> bankAccounts;
    List<CarVO> cars;
    List<StaffVO> staffs;
    List<HallVO> halls;
    List<CenterVO> centers;
    public InitialDataVO(){
    	super(InfoEnum.INITIAL_DATA);
    }
}
