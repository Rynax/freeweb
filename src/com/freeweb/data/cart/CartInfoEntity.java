package com.freeweb.data.cart;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class CartInfoEntity {
	private int cart_id;
	private int prod_id;
	private int shop_id;
	private int user_id;
	private Timestamp prod_cart_time;
	private int prod_cart_num;
	
	public int get_cart_id() {
		return cart_id;
	}
	
	public int get_prod_id() {
		return prod_id;
	}
	
	public int get_shop_id() {
		return shop_id;
	}
	
	public int get_user_id() {
		return user_id;
	}
	
	public Timestamp get_prod_cart_time() {
		return prod_cart_time;
	}
	
	public int get_prod_cart_num() {
		return prod_cart_num;
	}
	
	public void set_cart_id(int i) {
		this.cart_id = i;
	}
	
	public void set_prod_id(int i) {
		this.prod_id = i;
	}
	
	public void set_shop_id(int i) {
		this.shop_id = i;
	}
	
	public void set_user_id(int i) {
		this.user_id = i;
	}
	
	public void set_prod_cart_time(Timestamp t) {
		this.prod_cart_time = t;
	}
	
	public void set_prod_cart_num(int i) {
		this.prod_cart_num = i;
	}
	
	public CartInfoEntity(int prod_id, int shop_id, int user_id, int prod_cart_num) {
		super();
		this.prod_id = prod_id;
		this.shop_id = shop_id;
		this.user_id = user_id;
		this.prod_cart_num = prod_cart_num;
	}
	
	public CartInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "CartInfo: cart_id[" + cart_id + "], prod_id[" + prod_id + "], shop_id[" + shop_id + "], user_id[" + user_id + "], prod_cart_time[" + prod_cart_time + "], prod_cart_num[" + prod_cart_num + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("cart_id", cart_id);
		json.put("prod_id", prod_id);
		json.put("shop_id", shop_id);
		json.put("user_id", user_id);
		json.put("prod_cart_time", prod_cart_time);
		json.put("prod_cart_num", prod_cart_num);
		return json;
	}
}
