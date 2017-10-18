package com.freeweb.data.user_detail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserDetailInfoMapper implements RowMapper<UserDetailInfoEntity> {
	public UserDetailInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		UserDetailInfoEntity user = new UserDetailInfoEntity();
		user.set_user_id(rs.getInt("user_id"));
		user.set_nick_name(rs.getString("nick_name"));
		user.set_real_name(rs.getString("real_name"));
		user.set_real_name_status(rs.getInt("real_name_status"));
		user.set_idcard_type(rs.getInt("idcard_type"));
		user.set_idcard_number(rs.getString("idcard_number"));
		user.set_register_time(rs.getTimestamp("register_time"));
		user.set_user_status(rs.getInt("user_status"));
		return user;
	}
}
