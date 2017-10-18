package com.freeweb.data.cart;

import java.util.List;

public interface CartInfoDao {
	public void add(CartInfoEntity p);
	public void update(CartInfoEntity p);
	public void delete_by_prod_id(int id);
	public List<CartInfoEntity> find_by_user_id(int id);
	public List<CartInfoEntity> find_all();
}
