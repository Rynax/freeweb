package com.freeweb.data.menu;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuInfoEntity {
	private int id;
	private int class_id;
	private int priority;
	private String menu_name;
	private String link_url;
	
	public int get_id() {
		return id;
	}
	
	public int get_class_id() {
		return class_id;
	}
	
	public int get_priority() {
		return priority;
	}
	
	public String get_menu_name() {
		return menu_name;
	}
	
	public String get_link_url() {
		return link_url;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_class_id(int i) {
		this.class_id = i;
	}
	
	public void set_priority(int i) {
		this.priority = i;
	}
	
	public void set_menu_name(String s) {
		this.menu_name = s;
	}
	
	public void set_link_url(String s) {
		this.link_url = s;
	}
	
	public MenuInfoEntity(int class_id, int priority, String menu_name, String link_url) {
		super();
		this.class_id = class_id;
		this.priority = priority;
		this.menu_name = menu_name;
		this.link_url = link_url;
	}
	
	public MenuInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "MenuInfo: id[" + id + "], class_id[" + class_id + "], priority[" + priority + "], menu_name[" + menu_name + "], link_url[" + link_url + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("class_id", class_id);
		json.put("priority", priority);
		json.put("menu_name", menu_name);
		json.put("link_url", link_url);
		return json;
	}
}
