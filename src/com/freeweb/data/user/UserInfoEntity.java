package com.freeweb.data.user;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoEntity {
	private int user_id;
	private String login_name;
	private String login_pwd;
	private int account_status;
	
	public int get_user_id() {
		return user_id;
	}
	
	public String get_login_name() {
		return login_name;
	}
	
	public String get_login_pwd() {
		return login_pwd;
	}
	
	public int get_account_status() {
		return account_status;
	}
	
	public void set_user_id(int i) {
		this.user_id = i;
	}
	
	public void set_login_name(String s) {
		this.login_name = s;
	}
	
	public void set_login_pwd(String s) {
		this.login_pwd = s;
	}
	
	public void set_account_status(int i) {
		this.account_status = i;
	}
	
	public UserInfoEntity(String login_name, String login_pwd) {
		super();
		this.login_name = login_name;
		this.login_pwd = login_pwd;
		this.account_status = 0;
	}
	
	public UserInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: user_id[" + user_id + "], login_name[" + login_name + "], login_pwd[" + login_pwd + "], account_status[" + account_status + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("user_id", user_id);
		json.put("login_name", login_name);
		json.put("login_pwd", login_pwd);
		json.put("account_status", account_status);
		return json;
	}
}
