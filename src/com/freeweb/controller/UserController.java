package com.freeweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freeweb.data.user_detail.UserDetailInfoDaoImpl;
import com.freeweb.data.user_detail.UserDetailInfoEntity;

@Controller
public class UserController {
	@RequestMapping( value="/user.do")
	@ResponseBody
	public String user(HttpServletRequest request) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDetailInfoDaoImpl userdetailinfodao = (UserDetailInfoDaoImpl)context.getBean("UserDetailInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"code\":\"1\",\"desc\":\"请登录\",\"url\":\"login.html\"}";
		}
		
		List<UserDetailInfoEntity> list = null;
		try {
			list = userdetailinfodao.find_by_id((int) session.getAttribute("user_id"));
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据库异常\",\"url\":\"\"}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"code\":\"1\",\"desc\":\"用户不存在\",\"url\":\"\"}";
		}
		System.out.printf("%s\n", list.get(0).toString());
		
		UserDetailInfoEntity user = list.get(0);
		if(user.get_user_status() == 2) {
			return "{\"code\":\"1\",\"desc\":\"用户已删除\",\"url\":\"\"}";
		}
		
		return "{\"code\":\"0\",\"desc\":\"\",\"url\":\"\",\"nick_name\":\"" + user.get_nick_name() + "\",\"real_name_status\":\"" + (user.get_real_name_status() == 0 ? "未认证" : "已认证") + "\",\"user_status\":\"" + (user.get_user_status() == 0 ? "正常" : "冻结") + "\"}";
	}
}
