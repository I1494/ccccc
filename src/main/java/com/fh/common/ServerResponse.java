package com.fh.common;

public class ServerResponse {

    private  int code;

    private  String msg;

    private Object data;

    public static ServerResponse success(Object data) {
        return new ServerResponse(ServerEnum.SUCCESS.getCode(),ServerEnum.SUCCESS.getMsg(),data);
    }

    public static ServerResponse success() {
        return new ServerResponse(ServerEnum.SUCCESS.getCode(),ServerEnum.SUCCESS.getMsg());
    }

    public static ServerResponse error() {
        return new ServerResponse(ServerEnum.ERROR.getCode(),ServerEnum.ERROR.getMsg());
    }

    public static ServerResponse error(Object data) {
        return new ServerResponse(ServerEnum.ERROR.getCode(),ServerEnum.ERROR.getMsg(),data );
    }

public  static  ServerResponse  loginERROR(){
        return  new  ServerResponse(ServerEnum.LOGINERROR.getCode(),ServerEnum.LOGINERROR.getMsg());
}


    public static ServerResponse notExistError(){

        return new ServerResponse(ServerEnum.NOTEXISTERROR.getCode(), ServerEnum.NOTEXISTERROR.getMsg());
    }

    public static ServerResponse notExistError(Object data){

        return new ServerResponse(ServerEnum.NOTEXISTERROR.getCode(), ServerEnum.NOTEXISTERROR.getMsg(), data);
    }
    public static ServerResponse notOnError(){
        return new ServerResponse(ServerEnum.NOTONERROR.getCode(), ServerEnum.NOTONERROR.getMsg());
    }
    public ServerResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServerResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
