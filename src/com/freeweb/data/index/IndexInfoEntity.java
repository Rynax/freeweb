package com.freeweb.data.index;

import org.json.JSONException;
import org.json.JSONObject;

public class IndexInfoEntity {
	private int id;
	private int prod_id;
	private int priority;
	
	public int get_id() {
		return id;
	}

	public int get_priority() {
		return priority;
	}
	
	public int get_prod_id() {
		return prod_id;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_priority(int i) {
		this.priority = i;
	}
	
	public void set_prod_id(int i) {
		this.prod_id = i;
	}
	
	public IndexInfoEntity(int prod_id, int priority) {
		super();
		this.prod_id = prod_id;
		this.priority = priority;
	}
	
	public IndexInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "IndexInfo: id[" + id + "], prod_id[" + prod_id + "], priority[" + priority + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("prod_id", prod_id);
		json.put("priority", priority);
		return json;
	}
}
