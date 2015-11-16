package message;

import java.io.Serializable;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class CheckFormMessage implements Serializable{

    enum Type implements  Serializable{
        SUCCESS,
        BAD_TIME_FORMAT,
        BAD_DATE_FORMAT,
        ORDER_ID_NOT_FOUND,
        TRANSIT_ID_NOT_FOUND,
        BAD_PHONE_FORMAT,
        NULL_STRING
    }

    private Type type;
    private String detail;
}
