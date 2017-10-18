package com.freeweb.data.shop;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ShopInfoDaoImpl extends JdbcDaoSupport implements ShopInfoDao {
	@Override
	public void add(ShopInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`shop_info` (user_id, shop_name, shop_status) VALUES(?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_user_id(), p.get_shop_name(), p.get_shop_status());
	}
	
	@Override
	public void update(ShopInfoEntity p) {
		String sql = "UPDATE `freeweb`.`shop_info` SET user_id=?, shop_name=?, shop_status=? WHERE shop_id=?";
		this.getJdbcTemplate().update(sql, p.get_user_id(), p.get_shop_name(), p.get_shop_status(), p.get_shop_id());
	}
	
	@Override
	public void delete(ShopInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`shop_info` WHERE shop_id=?";
		this.getJdbcTemplate().update(sql, p.get_shop_id());
	}
	
	@Override
	public List<ShopInfoEntity> find_by_user_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`shop_info` WHERE user_id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ShopInfoMapper());
	}
	
	@Override
	public List<ShopInfoEntity> find_by_shop_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`shop_info` WHERE shop_id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ShopInfoMapper());
	}
	
	@Override
	public List<ShopInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`shop_info`";
		return this.getJdbcTemplate().query(sql, new ShopInfoMapper());
	}
}
