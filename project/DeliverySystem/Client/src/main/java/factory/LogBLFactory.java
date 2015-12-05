package factory;


import tool.vopo.VOPOFactory;
import bl.blImpl.logbl.LogblImpl;
import bl.blService.logblService.LogblService;

/**
 * Created by Sissel on 2015/11/29.
 */
public class LogBLFactory extends BLFactory {

    private static LogblService logblService;

    public static LogblService getLogblService(){
        if(logblService == null){
            logblService = new LogblImpl(new VOPOFactory());
        }
        return logblService;
    }
}
