package com.example.uibestpractice;

public class Msg {
	public static final int TYPE_RECEIVED = 0;
	public static final int TYPE_SEND =1;
	private String contend;
	private int type;
	public Msg(String contend, int type) {
		super();
		this.contend = contend;
		this.type = type;
	}
	public String getContend() {
		return contend;
	}
	public int getType() {
		return type;
	}
}
