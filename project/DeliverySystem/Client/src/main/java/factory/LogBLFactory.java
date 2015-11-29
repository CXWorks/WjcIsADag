package factory;

import bl.blImpl.logbl.LogBLImpl;
import bl.blService.logblService.LogblService;

/**
 * Created by Sissel on 2015/11/29.
 */
public class LogBLFactory extends BLFactory {

    private static LogblService logblService;

    public static LogblService getLogblService(){
        if(logblService == null){
            logblService = new LogBLImpl();
        }
        return logblService;
    }
}
