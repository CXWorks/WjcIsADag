package po.systemdata;

import util.EnumObservable;

/**
 * Created by Sissel on 2015/10/25.
 * 系统状态，分为建账中，正常运行，运行不正常
 */
public enum SystemState implements EnumObservable<SystemState>{
    INITIALIZING("建账中"),
    NORMAL("正常运行"),
    ERROR("出现错误");

    SystemState(String c){
        chinese = c;
    }

    private String chinese;

    @Override
    public String getChinese() {
        return chinese;
    }
}
