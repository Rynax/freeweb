package com.freeweb.data.user_detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
	public List<UserDetailInfoEntity> find_by_nick(String name) {
		String sql = "SELECT * FROM `freeweb`.`user_detail_info` WHERE nick_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new UserDetailInfoMapper());
	}
	
	@Override
	public List<UserDetailInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`user_detail_info`";
		return this.getJdbcTemplate().query(sql, new UserDetailInfoMapper());
	}
	
	@Override
	public int register(String name) {
		String sql = "INSERT INTO `freeweb`.`user_detail_info` (nick_name) VALUES (?)";
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    this.getJdbcTemplate().update(
	    	new PreparedStatementCreator() {
	    		public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
	    			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    			ps.setString(1, name);
	    			return ps;
	    		}
	    	}, keyHolder);
	    return keyHolder.getKey().intValue();
	}
}
