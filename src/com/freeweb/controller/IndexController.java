package com.freeweb.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.index.IndexInfoDaoImpl;
import com.freeweb.data.index.IndexInfoEntity;
import com.freeweb.data.menu.MenuInfoDaoImpl;
import com.freeweb.data.menu.MenuInfoEntity;
import com.freeweb.data.product.ProductInfoDaoImpl;
import com.freeweb.data.product.ProductInfoEntity;

@Controller
public class IndexController {
	@RequestMapping( value="/{in_html}.html")
	public String all_html(@PathVariable String in_html){
		return "/view/" + in_html + ".html";
	}
	
	@RequestMapping( value="/")
	public String web_root(){
		return "/view/index.html";
	}
	
	@RequestMapping( value="/test.do")
	@ResponseBody
	public String test(HttpServletRequest request) {
		ProductInfoEntity prod = new ProductInfoEntity(1, "新增商品");
		JSONArray resp = new JSONArray();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductInfoDaoImpl prodinfodao = (ProductInfoDaoImpl)context.getBean("ProductInfoDaoImpl");
		
		int id = prodinfodao.add(prod);
		System.out.printf("test id: %d\n", id);
		
		return "";
	}
	
	@RequestMapping( value="/menu.do")
	@ResponseBody
	public String menu(HttpServletRequest request) {
		List<MenuInfoEntity> list = null;
		JSONArray resp = new JSONArray();
		JSONArray classify = null;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MenuInfoDaoImpl menuinfodao = (MenuInfoDaoImpl)context.getBean("MenuInfoDaoImpl");
		
		try {
			list = menuinfodao.find_all();
			if(list == null) {
				return "";
			}
		} catch (DataAccessException e) {
			System.out.printf("menu_init exception: %s\n", e.toString());
			return "";
		}
		
		List<List<MenuInfoEntity>> resp_list = new ArrayList<List<MenuInfoEntity>>();
		List<MenuInfoEntity> classify_list = new ArrayList<MenuInfoEntity>();
		int i = 1;
		
		for(MenuInfoEntity menuinfo : list) {
			if(menuinfo.get_class_id() != i) {
				classify_list.sort(Comparator.comparingInt(MenuInfoEntity::get_priority));
				resp_list.add(classify_list.get(0).get_class_id() - 1, classify_list);
				classify_list = new ArrayList<MenuInfoEntity>();
				i = menuinfo.get_class_id();
			}
			classify_list.add(menuinfo);
		}
		classify_list.sort(Comparator.comparingInt(MenuInfoEntity::get_priority));
		resp_list.add(classify_list.get(0).get_class_id() - 1, classify_list);
		
		for(List<MenuInfoEntity> t : resp_list) {
			classify = new JSONArray();
			
			for(MenuInfoEntity m : t) {
				try {
					classify.put(m.toJson());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.printf("index_init exception: %s\n", e.toString());
				}
				//System.out.printf("%s\n", t.get(i).toJson().toString());
			}
			
			try {
				resp.put(new JSONObject().put("class", classify));
			} catch (JSONException e) {
				System.out.printf("index_init exception: %s\n", e.toString());
				return "";
			}
		}
		
		System.out.printf("%s\n", resp.toString());
		return resp.toString();
	}
	
	@RequestMapping( value="/index.do")
	@ResponseBody
	public String index(HttpServletRequest request) {
		List<IndexInfoEntity> list = null;
		List<ProductInfoEntity> prodlist = null;
		JSONArray resp = new JSONArray();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IndexInfoDaoImpl indexinfodao = (IndexInfoDaoImpl)context.getBean("IndexInfoDaoImpl");
		ProductInfoDaoImpl prodinfodao = (ProductInfoDaoImpl)context.getBean("ProductInfoDaoImpl");
		
		try {
			list = indexinfodao.find_all();
			if(list == null || list.size() == 0) {
				return "";
			}
		} catch (DataAccessException e) {
			System.out.printf("index_init exception: %s\n", e.toString());
			return "";
		}
		list.sort(Comparator.comparingInt(IndexInfoEntity::get_priority).reversed());
		
		for(IndexInfoEntity indexinfo : list) {
			prodlist = prodinfodao.find_by_id(indexinfo.get_prod_id());
			if(prodlist == null || prodlist.size() == 0) {
				return "";
			}
			
			try {
				resp.put(prodlist.get(0).toJson());
			} catch (JSONException e) {
				System.out.printf("index_init exception: %s\n", e.toString());
			}
		}
		
		System.out.printf("%s\n", resp.toString());
		return resp.toString();
	}
}
