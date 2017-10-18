package com.freeweb.data.user_detail;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDetailInfoDaoImpl extends JdbcDaoSupport implements UserDetailInfoDao {
	@Override
	public void add(UserDetailInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`user_detail_info` (nick_name, real_name, real_name_status, idcard_type, idcard_number, user_status) VALUES (?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_nick_name(), p.get_real_name(), p.get_real_name_status(), p.get_idcard_type(), p.get_idcard_number(), p.get_user_status());
	}
	
	@Override
	public void update(UserDetailInfoEntity p) {
		String sql = "UPDATE `freeweb`.`user_detail_info` SET nick_name=?, real_name=?, real_name_status=?, idcard_type=?, idcard_number=?, user_status=? WHERE user_id=?";
		this.getJdbcTemplate().update(sql, p.get_nick_name(), p.get_real_name(), p.get_real_name_status(), p.get_idcard_type(), p.get_idcard_number(), p.get_user_status(), p.get_user_id());
	}
	
	@Override
	public void delete(UserDetailInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`user_detail_info` WHERE user_id=?";
		this.getJdbcTemplate().update(sql, p.get_user_id());
	}
	
	@Override
	public List<UserDetailInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`user_detail_info` WHERE user_id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new UserDetailInfoMapper());
	}
	
	@Override
	public List<UserDetailInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`user_detail_info`";
		return this.getJdbcTemplate().query(sql, new UserDetailInfoMapper());
	}
	
	@Override
	public void register(String name) {
		String sql = "INSERT INTO `freeweb`.`user_detail_info` (nick_name, user_status) VALUES (?, 3)";
		this.getJdbcTemplate().update(sql, name);
	}
}
