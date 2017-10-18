package com.freeweb.data.shop;

import java.util.List;

public interface ShopInfoDao {
	public void add(ShopInfoEntity p);
	public void update(ShopInfoEntity p);
	public void delete(ShopInfoEntity p);
	public List<ShopInfoEntity> find_by_user_id(int id);
	public List<ShopInfoEntity> find_by_shop_id(int id);
	public List<ShopInfoEntity> find_all();
}
