package com.freeweb.data.user_detail;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDetailInfoEntity {
	private int user_id;
	private String nick_name;
	private String real_name;
	private int real_name_status;
	private int idcard_type;
	private String idcard_number;
	private Timestamp register_time;
	private int user_status;
	
	public int get_user_id() {
		return user_id;
	}
	
	public String get_nick_name() {
		return nick_name;
	}
	
	public String get_real_name() {
		return real_name;
	}
	
	public int get_real_name_status() {
		return real_name_status;
	}
	
	public int get_idcard_type() {
		return idcard_type;
	}
	
	public String get_idcard_number() {
		return idcard_number;
	}
	
	public Timestamp get_register_time() {
		return register_time;
	}
	
	public int get_user_status() {
		return user_status;
	}
	
	public void set_user_id(int i) {
		this.user_id = i;
	}
	
	public void set_nick_name(String s) {
		this.nick_name = s;
	}
	
	public void set_real_name(String s) {
		this.real_name = s;
	}
	
	public void set_real_name_status(int i) {
		this.real_name_status = i;
	}
	
	public void set_idcard_type(int i) {
		this.idcard_type = i;
	}
	
	public void set_idcard_number(String s) {
		this.idcard_number = s;
	}
	
	public void set_register_time(Timestamp t) {
		this.register_time = t;
	}
	
	public void set_user_status(int i) {
		this.user_status = i;
	}
	
	public UserDetailInfoEntity(String nick_name) {
		super();
		this.nick_name = nick_name;
		this.user_status = 3;
	}
	
	public UserDetailInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserDetailInfo: user_id[" + user_id + "], nick_name[" + nick_name + "], real_name[" + real_name + "], real_name_status[" + real_name_status + "], idcard_type[" + idcard_type + "], idcard_number[" + idcard_number + "], register_time[" + register_time + "], user_status[" + user_status + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("user_id", user_id);
		json.put("nick_name", nick_name);
		json.put("real_name", real_name);
		json.put("real_name_status", real_name_status);
		json.put("idcard_type", idcard_type);
		json.put("idcard_number", idcard_number);
		json.put("register_time", register_time);
		json.put("user_status", user_status);
		return json;
	}
}
