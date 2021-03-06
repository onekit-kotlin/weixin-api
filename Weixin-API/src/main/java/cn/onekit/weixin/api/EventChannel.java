package cn.onekit.weixin.api;

import java.util.HashMap;
import java.util.Map;

import cn.onekit.js.JsString;
import cn.onekit.js.core.function;
import cn.onekit.js.JsObject_;

public class EventChannel implements JsObject_ {
    public static Map<Integer,EventChannel> eventChannels=new HashMap();
    /////////////////////
    Map<String, function> onHandlers = new HashMap();
    Map<String, function> onceHandlers = new HashMap();
    public EventChannel(int channelID,int otherID){
        this.otherID=otherID;
        eventChannels.put(channelID,this);
    }
    private final int otherID;
    public void emit(String eventName, JsObject_ args) {
        EventChannel other = eventChannels.get(otherID);
        if (other.onHandlers.containsKey(eventName)) {
            other.onHandlers.get(eventName).invoke(args);
        }else  if (other.onceHandlers.containsKey(eventName)) {
            other.onceHandlers.get(eventName).invoke(args);
            other.onceHandlers.remove(eventName);
        }
    }

    public void on(String eventName, function fn) {
        onHandlers.put(eventName, fn);
    }

    public void once(String eventName, function fn) {
        onceHandlers.put(eventName, fn);
    }

    public void off(String eventName, function fn) {
        onHandlers.remove(eventName);
    }

    @Override
    public JsString ToString() {
        return null;
    }

    @Override
    public String toLocaleString(JsString locales, JsObject_ options) {
        return null;
    }

    @Override
    public JsObject_ invoke(JsObject_... params) {
        return null;
    }
}
