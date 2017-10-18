package com.freeweb.data.user;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserInfoDaoImpl extends JdbcDaoSupport implements UserInfoDao {
	@Override
	public void add(UserInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`user_info` VALUES(null, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_login_name(), p.get_login_pwd(), p.get_account_status());
	}
	
	@Override
	public void update(UserInfoEntity p) {
		String sql = "UPDATE `freeweb`.`user_info` SET login_name=?, login_pwd=?, user_status=? WHERE user_id=?";
		this.getJdbcTemplate().update(sql, p.get_login_name(), p.get_login_pwd(), p.get_account_status(), p.get_user_id());
	}
	
	@Override
	public void delete(UserInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`user_info` WHERE user_id=?";
		this.getJdbcTemplate().update(sql, p.get_user_id());
	}
	
	@Override
	public List<UserInfoEntity> find_by_name(String name) {
		String sql = "SELECT * FROM `freeweb`.`user_info` WHERE login_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new UserInfoMapper());
	}
	
	@Override
	public List<UserInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`user_info`";
		return this.getJdbcTemplate().query(sql, new UserInfoMapper());
	}
}
