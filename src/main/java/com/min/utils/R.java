package com.min.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.http.HttpStatus;

/**
 * 返回数据
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2016年10月27日 下午9:59:27
 */
@ApiModel("公共响应数据")
public class R<T> {

    @ApiModelProperty("自定义状态码，0-成功，其他失败")
    private int code;

    @ApiModelProperty("自定义消息，可用于前端显示")
    private String message;

    @ApiModelProperty("接口数据，如果没有则为false或者null")
    private T data;
    
    
    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    /**
     * 带数据的成功信息
     *
     * @param message
     * @param data
     */
    public R(String message, T data) {
        this(0, message, data);
    }
    
    /**
     * 只有数据，没有消息
     *
     * @param data
     */
    public R(T data) {
        this("", data);
    }
    /**
     * 只有消息，没有数据
     *
     * @param message
     */
    public R(String message) {
        this(message, null);
    }
    
    public static <T> R ok() {
        return new R(0);
    }

    public static <T> R ok(T data) {
        return new R(data);
    }
    
    /**
     * add by Jiamin
     * @param message 消息响应
     * @param <T>
     * @return
     */
    public static <T> R ok(String message) {
        return new R(message);
    }
    /**
     * add by Jiamin
     * @param result 受影响的行数
     * @param <T>
     * @return
     */
    public static <T> R ok(int result) {
        return result > 0 ? new R("操作成功") : error(HttpStatus.SC_FORBIDDEN, "系统繁忙");
    }
    
    public R(int code) {
        this.code = code;
    }

    public static R<Boolean> error(String message) {
        return error(HttpStatus.SC_FORBIDDEN, message);
    }

    public static R<Boolean> error(int code) {
        return error(code, "");
    }

    public static R<Boolean> error(int code, String message) {
        return new R<>(code, message, false);
    }

    /**
     * 返回成功，只是通知用
     */
    public static R<Boolean> success() {
        return success("");
    }

    /**
     * 返回成功，带有通知内容
     *
     * @param message 通知内容
     */
    public static R<Boolean> success(String message) {
        return new R<>(0, message, true);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
