package factory;

import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.FormatCheckService.FormatCheckService;

/**
 * Created by Sissel on 2015/12/12.
 */
public class FormatCheckFactory {

    private static FormatCheckService formatCheckService;

    public static FormatCheckService getFormatCheckService() {
        if(formatCheckService == null){
            formatCheckService = new FormatCheckImpl();
        }

        return formatCheckService;
    }
}
