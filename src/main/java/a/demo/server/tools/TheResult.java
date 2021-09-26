package a.demo.server.tools;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class TheResult {
    private String code;
    private String message;
    private Object data;
    public TheResult success(){
        return success(null);
    }
    public TheResult success(Object data){
        return success(data,"操作成功");
    }
    public TheResult success(Object data,String message){
        return success(data,message,"200");
    }
    public TheResult success(Object data,String message,String code){
        this.data=data;
        this.message=message;
        this.code=code;
        return this;
    }
    public String successString(){
        return successString(null);
    }
    public String successString(Object data){
        return successString(data,"操作成功");
    }
    public String successString(Object data,String message){
        return successString(data,message,"200");
    }
    public String successString(Object data,String message,String code){
        return JSONObject.toJSONString(success(data,message,code));
    }

    public TheResult failure(){
        return failure("操作失败");
    }
    public TheResult failure(String message){
        return failure(message,"500");
    }
    public TheResult failure(String message,String code){
        return failure(message,code,null);
    }
    public TheResult failure(String message,String code,Object data){
        this.message=message;
        this.code=code;
        this.data=data;
        return this;
    }
    public String failureString(){
        return failureString("操作失败");
    }
    public String failureString(String message){
        return failureString(message,"500");
    }
    public String failureString(String message,String code){
        return failureString(message,code,null);
    }
    public String failureString(String message,String code,Object data){
        return JSONObject.toJSONString(failure(message,code,data));
    }
}
