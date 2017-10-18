package com.freeweb.data.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserInfoMapper implements RowMapper<UserInfoEntity> {
	public UserInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		UserInfoEntity user = new UserInfoEntity();
		user.set_user_id(rs.getInt("user_id"));
		user.set_login_name(rs.getString("login_name"));
		user.set_login_pwd(rs.getString("login_pwd"));
		user.set_account_status(rs.getInt("account_status"));
		return user;
	}
}
