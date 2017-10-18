package com.freeweb.data.cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CartInfoMapper implements RowMapper<CartInfoEntity> {
	public CartInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		CartInfoEntity cart = new CartInfoEntity();
		cart.set_cart_id(rs.getInt("cart_id"));
		cart.set_prod_id(rs.getInt("prod_id"));
		cart.set_shop_id(rs.getInt("shop_id"));
		cart.set_user_id(rs.getInt("user_id"));
		cart.set_prod_cart_time(rs.getTimestamp("prod_cart_time"));
		cart.set_prod_cart_num(rs.getInt("prod_cart_num"));
		return cart;
	}
}
