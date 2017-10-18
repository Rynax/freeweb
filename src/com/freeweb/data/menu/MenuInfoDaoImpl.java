package com.freeweb.data.menu;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MenuInfoDaoImpl extends JdbcDaoSupport implements MenuInfoDao {
	@Override
	public void add(MenuInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`menu_info` VALUES (null, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_class_id(), p.get_priority(), p.get_menu_name(), p.get_link_url());
	}
	
	@Override
	public void update(MenuInfoEntity p) {
		String sql = "UPDATE `freeweb`.`menu_info` SET class_id=?, priority=?, menu_name=?, link_url=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_class_id(), p.get_priority(), p.get_menu_name(), p.get_link_url(), p.get_id());
	}
	
	@Override
	public void delete(MenuInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`menu_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_id());
	}
	
	@Override
	public List<MenuInfoEntity> find_by_class_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`menu_info` WHERE class_id=? ORDER BY class_id";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new MenuInfoMapper());
	}
	
	@Override
	public List<MenuInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`menu_info` ORDER BY class_id";
		return this.getJdbcTemplate().query(sql, new MenuInfoMapper());
	}
}
