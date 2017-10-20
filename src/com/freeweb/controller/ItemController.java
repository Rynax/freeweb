package com.freeweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.product.ProductInfoDaoImpl;
import com.freeweb.data.product.ProductInfoEntity;

@Controller
public class ItemController {
	@RequestMapping( value="/item.do")
	@ResponseBody
	public String addcart(HttpServletRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductInfoDaoImpl productinfodao = (ProductInfoDaoImpl)context.getBean("ProductInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"code\":\"1\",\"desc\":\"请登录\",\"url\":\"login.html\"}";
		}
		
		String info = request.getParameter("item");
		if(info == null || info.isEmpty()) {
			return "{\"code\":\"1\",\"desc\":\"数据异常\",\"url\":\"\"}";
		}
		String prod_id;
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(info);
			prod_id = jsonObj.getString("item");
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据异常\",\"url\":\"\"}";
		}
		
		List<ProductInfoEntity> productlist = productinfodao.find_by_id(Integer.parseInt(prod_id));
		if(productlist == null || productlist.size() == 0) {
			System.out.printf("商品id: %s, 找不到对应商品\n", prod_id);
			return "{\"code\":\"1\",\"desc\":\"找不到商品\",\"url\":\"\"}";
		}
		
		try {
			return "{\"code\":\"0\",\"desc\":\"" + productlist.get(0).toJson().toString() + "\",\"url\":\"\"}";
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"服务器异常\",\"url\":\"\"}";
		}
	}
}
