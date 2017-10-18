package com.freeweb.data.shop;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShopInfoMapper implements RowMapper<ShopInfoEntity> {
	public ShopInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ShopInfoEntity shop = new ShopInfoEntity();
		shop.set_shop_id(rs.getInt("shop_id"));
		shop.set_user_id(rs.getInt("user_id"));
		shop.set_shop_name(rs.getString("shop_name"));
		shop.set_shop_time(rs.getTimestamp("shop_time"));
		shop.set_shop_status(rs.getInt("shop_status"));
		return shop;
	}
}
