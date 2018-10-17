package com.vs.tools;

import java.io.Serializable;

/**
 * Created by hys on 2018/10/10.
 */
public class ResultData<V> implements Serializable {

	//状态
	private int status;
	//消息
	private String message;
	//数据
	private V data;

	public ResultData(int status, String message) {
		this(status, message, null);
	}

	public ResultData(int status, String message, V data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public V getData() {
		return data;
	}

	public void setData(V data) {
		this.data = data;
	}
}
