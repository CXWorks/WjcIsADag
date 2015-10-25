package database;


import message.OperationMessage;
import po.financedata.BankAccountPO;

/**
 * Created by Sissel on 2015/10/23.
 */
public class SqlHelper {

    private final static SqlHelper instance = new SqlHelper();

    static String DRIVER;
	String username;
	String password;

    /*
     * singleton mode
     */
    private SqlHelper(){}
    public SqlHelper getInstance(){
        return instance;
    }

    OperationMessage setUpSystem(String username, char[] password, String dbName){
        return null;
    }

    OperationMessage recoverSystem(String username, char[] password, String dbName){
        return null;
    }

}
