package com.freeweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.cart.CartInfoDaoImpl;
import com.freeweb.data.cart.CartInfoEntity;
import com.freeweb.data.product.ProductInfoDaoImpl;
import com.freeweb.data.product.ProductInfoEntity;
import com.freeweb.data.shop.ShopInfoDaoImpl;
import com.freeweb.data.shop.ShopInfoEntity;
import com.freeweb.data.user_detail.UserDetailInfoDaoImpl;
import com.freeweb.data.user_detail.UserDetailInfoEntity;

@Controller
public class CartController {
	@RequestMapping( value="/cart.do")
	@ResponseBody
	public String cart(HttpServletRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartInfoDaoImpl cartinfodao = (CartInfoDaoImpl)context.getBean("CartInfoDaoImpl");
		ShopInfoDaoImpl shopinfodao = (ShopInfoDaoImpl)context.getBean("ShopInfoDaoImpl");
		ProductInfoDaoImpl prodinfodao = (ProductInfoDaoImpl)context.getBean("ProductInfoDaoImpl");
		UserDetailInfoDaoImpl userdetailinfodao = (UserDetailInfoDaoImpl)context.getBean("UserDetailInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"code\":\"1\",\"desc\":\"请登录\",\"url\":\"login.html\"}";
		}
		
		List<CartInfoEntity> list = null;
		try {
			list = cartinfodao.find_by_user_id((int) session.getAttribute("user_id"));
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据库异常\",\"url\":\"\"}";
		}
		if(list == null || list.size() == 0) {
			return "{\"code\":\"2\",\"desc\":\"用户不存在\",\"url\":\"register.html\"}";
		}
		System.out.printf("%s\n", list.get(0).toString());
		
		JSONArray resp = new JSONArray();
		try {
			JSONArray products = new JSONArray();
			int shop_id = list.get(0).get_shop_id();
			for(CartInfoEntity t : list) {
				if(t.get_shop_id() == shop_id) {
					List<ProductInfoEntity> prodlist = prodinfodao.find_by_id(t.get_prod_id());
					if(prodlist == null || prodlist.size() == 0) {
						System.out.printf("购物车id: %d, 找不到对应商品\n", t.get_cart_id());
						continue;
					}
					JSONObject json1 = new JSONObject();
					json1.put("prod_id", prodlist.get(0).get_prod_id());
					json1.put("prod_name", prodlist.get(0).get_prod_name());
					json1.put("sp", prodlist.get(0).get_prod_src_price());
					json1.put("cp", prodlist.get(0).get_prod_cur_price());
					json1.put("prod_num", t.get_prod_cart_num());
					json1.put("prod_save", prodlist.get(0).get_prod_save());
					json1.put("prod_status", prodlist.get(0).get_prod_status() == 0 ? "正常" : "下架");
					products.put(json1);
				}else {
					List<ShopInfoEntity> shoplist = shopinfodao.find_by_shop_id(shop_id);
					if(shoplist == null || shoplist.size() == 0) {
						System.out.printf("商铺id: %d, 找不到对应商铺\n", shop_id);
						products = new JSONArray();
						continue;
					}
					List<UserDetailInfoEntity> userlist = userdetailinfodao.find_by_id(shoplist.get(0).get_user_id());
					if(userlist == null || userlist.size() == 0) {
						System.out.printf("商铺id: %d, 找不到对应用户\n", shop_id);
						products = new JSONArray();
						continue;
					}
					JSONObject json2 = new JSONObject();
					json2.put("shop_id", shop_id);
					json2.put("shop_name", shoplist.get(0).get_shop_name());
					json2.put("user_nick", userlist.get(0).get_nick_name());
					json2.put("shop_status", shoplist.get(0).get_shop_status() == 0 ? "正常" : "冻结");
					json2.put("products", products);
					resp.put(json2);
					shop_id = t.get_shop_id();
					products = new JSONArray();
				}
			}
			List<ShopInfoEntity> shoplist = shopinfodao.find_by_shop_id(shop_id);
			if(shoplist == null || shoplist.size() == 0) {
				System.out.printf("商铺id: %d, 找不到对应商铺\n", shop_id);
				products = new JSONArray();
				return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\", " + resp + "}";
			}
			List<UserDetailInfoEntity> userlist = userdetailinfodao.find_by_id(shoplist.get(0).get_user_id());
			if(userlist == null || userlist.size() == 0) {
				System.out.printf("商铺id: %d, 找不到对应用户\n", shop_id);
				products = new JSONArray();
				return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\", " + resp + "}";
			}
			JSONObject json2 = new JSONObject();
			json2.put("shop_id", shop_id);
			json2.put("shop_name", shoplist.get(0).get_shop_name());
			json2.put("user_nick", userlist.get(0).get_nick_name());
			json2.put("shop_status", shoplist.get(0).get_shop_status() == 0 ? "正常" : "冻结");
			json2.put("products", products);
			resp.put(json2);
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\", " + resp + "}";
		}
		
		return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\", " + resp + "}";
	}
}
