package com.freeweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.cart.CartInfoDaoImpl;
import com.freeweb.data.cart.CartInfoEntity;

@Controller
public class ProductController {
	@RequestMapping( value="/addcart.do")
	@ResponseBody
	public String addcart(HttpServletRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartInfoDaoImpl cartinfodao = (CartInfoDaoImpl)context.getBean("CartInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"code\":\"1\",\"desc\":\"请登录\",\"url\":\"login.html\"}";
		}
		
		String info = request.getParameter("data");
		String prod_id;
		String shop_id;
		String prod_num;
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(info);
			prod_id = jsonObj.getString("prod_id");
			shop_id = jsonObj.getString("shop_id");
			prod_num = jsonObj.getString("prod_num");
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据异常\",\"url\":\"\"}";
		}
		
		CartInfoEntity cart = new CartInfoEntity();
		cart.set_prod_id(Integer.parseInt(prod_id));
		cart.set_shop_id(Integer.parseInt(shop_id));
		cart.set_prod_cart_num(Integer.parseInt(prod_num));
		cart.set_user_id((int) session.getAttribute("user_id"));
		cartinfodao.add(cart);
		
		return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\"}";
	}
}
