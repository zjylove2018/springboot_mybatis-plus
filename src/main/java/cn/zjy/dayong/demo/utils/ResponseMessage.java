package cn.zjy.dayong.demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/8/28
 * Time:13:59
 */
public class ResponseMessage extends HashMap<String, Object> {
    public ResponseMessage() {
        put("code", 0);
        put("msg", "success");
    }

    public static ResponseMessage error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResponseMessage error(String msg) {
        return error(500, msg);
    }

    public static ResponseMessage error(int code, String msg) {
        ResponseMessage r = new ResponseMessage();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResponseMessage ok(String msg) {
        ResponseMessage r = new ResponseMessage();
        r.put("msg", msg);
        return r;
    }

    public static ResponseMessage ok(Map<String, Object> map) {
        ResponseMessage r = new ResponseMessage();
        r.putAll(map);
        return r;
    }

    public static ResponseMessage ok() {
        return new ResponseMessage();
    }

    @Override
    public ResponseMessage put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
