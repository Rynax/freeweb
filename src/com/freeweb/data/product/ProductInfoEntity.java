package com.freeweb.data.product;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductInfoEntity {
	private int prod_id;
	private int shop_id;
	private int prod_type_id;
	private String prod_name;
	private int prod_src_price;
	private int prod_cur_price;
	private int prod_save;
	private String prod_desc;
	private String prod_spict;
	private String prod_mpict;
	private String prod_lpict;
	private int prod_status;
	
	public int get_prod_id() {
		return prod_id;
	}
    
	public int get_shop_id() {
		return shop_id;
	}
    
	public int get_prod_type_id() {
		return prod_type_id;
	}
	
	public String get_prod_name() {
		return prod_name;
	}
	
	public int get_prod_src_price() {
		return prod_src_price;
	}
	
	public int get_prod_cur_price() {
		return prod_cur_price;
	}
	
	public int get_prod_save() {
		return prod_save;
	}
	
	public String get_prod_desc() {
		return prod_desc;
	}
	
	public String get_prod_spict() {
		return prod_spict;
	}
	
	public String get_prod_mpict() {
		return prod_mpict;
	}
	
	public String get_prod_lpict() {
		return prod_lpict;
	}
	
	public int get_prod_status() {
		return prod_status;
	}
	
	public void set_prod_id(int i) {
		this.prod_id = i;
	}
    
	public void set_shop_id(int i) {
		this.shop_id = i;
	}
	
	public void set_prod_type_id(int i) {
		this.prod_type_id = i;
	}
	
	public void set_prod_name(String s) {
		this.prod_name = s;
	}
	
	public void set_prod_src_price(int i) {
		this.prod_src_price = i;
	}
	
	public void set_prod_cur_price(int i) {
		this.prod_cur_price = i;
	}
	
	public void set_prod_save(int i) {
		this.prod_save = i;
	}
	
	public void set_prod_desc(String s) {
		this.prod_desc = s;
	}
	
	public void set_prod_spict(String s) {
		this.prod_spict = s;
	}
	
	public void set_prod_mpict(String s) {
		this.prod_mpict = s;
	}
	
	public void set_prod_lpict(String s) {
		this.prod_lpict = s;
	}
	
	public void set_prod_status(int i) {
		this.prod_status = i;
	}
	
	public ProductInfoEntity(int shop_id, String prod_name) {
		super();
        this.shop_id = shop_id;
		this.prod_name = prod_name;
	}
	
	public ProductInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "ProductInfo: prod_id[" + prod_id + "], shop_id[" + shop_id + "], prod_type_id[" + prod_type_id + "], prod_name[" + prod_name + "], prod_src_price[" + prod_src_price +
				"], prod_cur_price[" + prod_cur_price + "], prod_save[" + prod_save + "], prod_desc[" + prod_desc + "], prod_spict[" + prod_spict + "], prod_mpict[" + prod_mpict +
				"], prod_lpict[" + prod_lpict + "], prod_status[" + prod_status + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("prod_id", prod_id);
		json.put("shop_id", shop_id);
		json.put("prod_type_id", prod_type_id);
		json.put("prod_name", prod_name);
		json.put("prod_src_price", prod_src_price);
		json.put("prod_cur_price", prod_cur_price);
		json.put("prod_save", prod_save);
		json.put("prod_desc", prod_desc);
		json.put("prod_spict", prod_spict);
		json.put("prod_mpict", prod_mpict);
		json.put("prod_lpict", prod_lpict);
		json.put("prod_status", prod_status);
		return json;
	}
}
