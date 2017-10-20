package com.freeweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.user.UserInfoDaoImpl;
import com.freeweb.data.user.UserInfoEntity;
import com.freeweb.data.user_detail.UserDetailInfoDaoImpl;
import com.freeweb.data.user_detail.UserDetailInfoEntity;
import com.freeweb.global.PublicCrypto;

@Controller
public class LoginController {
	@RequestMapping( value="/login.do")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String info = request.getParameter("data");
		String username = null;
		String password = null;
		if(info == null || info.isEmpty()) {
			return "{\"code\":\"1\",\"desc\":\"输入数据异常\",\"url\":\"\"}";
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserInfoDaoImpl userinfodao = (UserInfoDaoImpl)context.getBean("UserInfoDaoImpl");
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(info);
			username = jsonObj.getString("username");
			password = jsonObj.getString("password");
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"服务器异常\",\"url\":\"\"}";
		}
		if((username == null || username.isEmpty()) ||
		   (password == null || password.isEmpty())) {
			return "{\"code\":\"1\",\"desc\":\"请输入用户名和密码\",\"url\":\"\"}";
		}
		//System.out.printf("info[%s], %s, %s\n", info.toString(), username, password);
		
		List<UserInfoEntity> list = null;
		try {
			list = userinfodao.find_by_name(username);
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"服务器异常\",\"url\":\"\"}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"code\":\"2\",\"desc\":\"用户不存在, 请注册\",\"url\":\"\"}";
		}
		//System.out.printf("%s\n", list.get(0).toString());
		if(!PublicCrypto.getmd5(password).equals(list.get(0).get_login_pwd())) {
			return "{\"code\":\"3\",\"desc\":\"密码错误\",\"url\":\"\"}";
		}
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(10*60);
		session.setAttribute("user_id", list.get(0).get_user_id());
		
		//return "{\"code\":\"0\",\"desc\":\"注册成功\",\"url\":\"" + response.encodeURL("user.html") + "\"}";
		return "{\"code\":\"0\",\"desc\":\"登录成功\",\"url\":\"user.html\"}";
	}
	
	@RequestMapping( value="/regist.do")
	@ResponseBody
	public String regist(HttpServletRequest request, HttpServletResponse response) {
		String info = request.getParameter("data");
		String username = null;
		String password = null;
		if(info == null || info.isEmpty()) {
			return "{\"code\":\"1\",\"desc\":\"输入数据异常\",\"url\":\"\"}";
		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserInfoDaoImpl userinfodao = (UserInfoDaoImpl)context.getBean("UserInfoDaoImpl");
		UserDetailInfoDaoImpl userdetailinfodao = (UserDetailInfoDaoImpl)context.getBean("UserDetailInfoDaoImpl");
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(info);
			username = jsonObj.getString("username");
			password = jsonObj.getString("password");
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"服务器异常\",\"url\":\"\"}";
		}
		if((username == null || username.isEmpty()) ||
		   (password == null || password.isEmpty())) {
			return "{\"code\":\"1\",\"desc\":\"请输入用户名和密码\",\"url\":\"\"}";
		}
		//System.out.printf("info[%s], %s, %s\n", info.toString(), username, password);
		
		try {
			List<UserInfoEntity> list = null;
			list = userinfodao.find_by_name(username);
			if(list != null && list.size() > 0) {
				return "{\"code\":\"2\",\"desc\":\"用户名已存在\",\"url\":\"\"}";
			}
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据库异常\",\"url\":\"\"}";
		}
		
		String pwd = PublicCrypto.getmd5(password); 
		UserInfoEntity user = new UserInfoEntity();
		user.set_user_id(userdetailinfodao.register(username));
		user.set_login_name(username);
		user.set_login_pwd(pwd);
		user.set_account_status(0);
		
		userinfodao.add(user);
		//System.out.printf("%s\n", user.toString());
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(10*60);
		session.setAttribute("user_id", user.get_user_id());
		
		//return "{\"code\":\"0\",\"desc\":\"注册成功\",\"url\":\"" + response.encodeURL("user.html") + "\"}";
		return "{\"code\":\"0\",\"desc\":\"注册成功\",\"url\":\"user.html\"}";
	}
}
