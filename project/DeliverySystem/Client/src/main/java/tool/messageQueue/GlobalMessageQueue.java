package tool.messageQueue;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sissel on 2015/12/31.
 */
public class GlobalMessageQueue {
    private static GlobalMessageQueue instance;
    Map<String, String> messages;

    public static GlobalMessageQueue getInstance(){
        if(instance == null){
            instance = new GlobalMessageQueue();
        }
        return instance;
    }

    private GlobalMessageQueue() {
        messages = new HashMap<>();
    }

    public synchronized void addMessage(String key, String value){
        messages.put(key, value);
    }

    public synchronized String readMessage(String key){
        String value = messages.get(key);
        // clear when read
        messages.put(key, null);
        return value;
    }
}
