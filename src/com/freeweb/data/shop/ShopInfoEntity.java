package com.freeweb.data.shop;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class ShopInfoEntity {
	private int shop_id;
	private int user_id;
	private String shop_name;
	private Timestamp shop_time;
	private int shop_status;
	
	public int get_shop_id() {
		return shop_id;
	}
	
	public int get_user_id() {
		return user_id;
	}
	
	public String get_shop_name() {
		return shop_name;
	}
	
	public Timestamp get_shop_time() {
		return shop_time;
	}
	
	public int get_shop_status() {
		return shop_status;
	}
	
	public void set_shop_id(int i) {
		this.shop_id = i;
	}
	
	public void set_user_id(int i) {
		this.user_id = i;
	}
	
	public void set_shop_name(String s) {
		this.shop_name = s;
	}
	
	public void set_shop_time(Timestamp t) {
		this.shop_time = t;
	}
	
	public void set_shop_status(int i) {
		this.shop_status = i;
	}
	
	public ShopInfoEntity(int user_id, String shop_name) {
		super();
		this.user_id = user_id;
		this.shop_name = shop_name;
	}
	
	public ShopInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "ShopInfo: shop_id[" + shop_id + "], user_id[" + user_id + "], shop_name[" + shop_name + "], shop_time[" + shop_time + "], shop_status[" + shop_status + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("shop_id", shop_id);
		json.put("user_id", user_id);
		json.put("shop_name", shop_name);
		json.put("shop_time", shop_time);
		json.put("shop_status", shop_status);
		return json;
	}
}
