package com.freeweb.data.menu;

import java.util.List;

public interface MenuInfoDao {
	public void add(MenuInfoEntity p);
	public void update(MenuInfoEntity p);
	public void delete(MenuInfoEntity p);
	public List<MenuInfoEntity> find_by_class_id(int id);
	public List<MenuInfoEntity> find_all();
}
