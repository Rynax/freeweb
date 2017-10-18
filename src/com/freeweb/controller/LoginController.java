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

@Controller
public class LoginController {
	@RequestMapping( value="/login.do")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String info = request.getParameter("data");
		String username = null;
		String password = null;
		String checkcode = null;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserInfoDaoImpl userinfodao = (UserInfoDaoImpl)context.getBean("UserInfoDaoImpl");
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(info);
			username = jsonObj.getString("username");
			password = jsonObj.getString("password");
			checkcode = jsonObj.getString("checkcode");
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据异常\",\"url\":\"\"}";
		}
		if((username == null || username.isEmpty()) ||
		   (password == null || password.isEmpty()) ||
		   (checkcode == null || checkcode.isEmpty())) {
			return "{\"code\":\"1\",\"desc\":\"数据异常\",\"url\":\"\"}";
		}
		System.out.printf("info[%s], %s, %s, %s\n", info.toString(), username, password, checkcode);
		
		List<UserInfoEntity> list = null;
		try {
			list = userinfodao.find_by_name(username);
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据库异常\",\"url\":\"\"}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"code\":\"2\",\"desc\":\"用户不存在\",\"url\":\"register.html\"}";
		}
		System.out.printf("%s\n", list.get(0).toString());
		if(!password.equals(list.get(0).get_login_pwd())) {
			return "{\"code\":\"3\",\"desc\":\"密码错误\",\"url\":\"\"}";
		}
		
		HttpSession session = request.getSession();
		
		session.setMaxInactiveInterval(10*60);
		session.setAttribute("user_id", list.get(0).get_user_id());
		
		return "{\"code\":\"0\",\"desc\":\"登录成功\",\"url\":\"" + response.encodeURL("user.html") + "\"}";
	}
}
