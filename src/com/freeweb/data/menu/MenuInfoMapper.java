package com.freeweb.data.menu;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MenuInfoMapper implements RowMapper<MenuInfoEntity> {
	public MenuInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		MenuInfoEntity menu = new MenuInfoEntity();
		menu.set_id(rs.getInt("id"));
		menu.set_class_id(rs.getInt("class_id"));
		menu.set_priority(rs.getInt("priority"));
		menu.set_menu_name(rs.getString("menu_name"));
		menu.set_link_url(rs.getString("link_url"));
		return menu;
	}
}
