package com.freeweb.data.cart;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CartInfoDaoImpl extends JdbcDaoSupport implements CartInfoDao {
	@Override
	public void add(CartInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`cart_info` (prod_id, shop_id, user_id, prod_cart_num) VALUES (?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_prod_id(), p.get_shop_id(), p.get_user_id(), p.get_prod_cart_num());
	}
	
	@Override
	public void update(CartInfoEntity p) {
		String sql = "UPDATE `freeweb`.`cart_info` SET prod_id=?, shop_id=?, user_id=?, prod_cart_num=? WHERE cart_id=?";
		this.getJdbcTemplate().update(sql, p.get_prod_id(), p.get_shop_id(), p.get_user_id(), p.get_prod_cart_num(), p.get_cart_id());
	}
	
	@Override
	public void delete_by_prod_id(int id) {
		String sql = "DELETE FROM `freeweb`.`cart_info` WHERE prod_id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<CartInfoEntity> find_by_user_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`cart_info` WHERE user_id=? ORDER BY prod_cart_time DESC";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new CartInfoMapper());
	}
	
	@Override
	public List<CartInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`cart_info`";
		return this.getJdbcTemplate().query(sql, new CartInfoMapper());
	}
}
