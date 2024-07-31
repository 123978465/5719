package com.keyware.MR.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AjaxMessage<T> implements Serializable {
	private static final long serialVersionUID = 1680802699076731536L;
	private String code;//约定零为假，非零为真。
	private String message;
	private String msg;
	private T data;
	private String token;

	public AjaxMessage() {
	}

	public AjaxMessage(String code, String message, T data, String token) {
		this.code = code;
		this.message = message;
		this.msg = message;
		this.data = data;
		this.token = token;
	}

//	public Integer getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = Integer.valueOf(code);
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		setMsg(message);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
/**
 * 将状态码从 1 成功 0 失败 改为 200 成功 400 失败*/
	public static Map<Object,Object> codeTran(AjaxMessage message){
		HashMap<Object, Object> map = new HashMap<>();
		if (1==Integer.valueOf(message.getCode())){
			map.put("code",200);
		}else {
			map.put("code",400);
		}
		map.put("msg",message.getMsg());
		map.put("message",message.getMessage());
		map.put("data",message.getData());
		map.put("token",message.getToken());
		return map;
	}
}