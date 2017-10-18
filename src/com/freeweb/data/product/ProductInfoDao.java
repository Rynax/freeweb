package com.freeweb.data.product;

import java.util.List;

public interface ProductInfoDao {
	public int add(ProductInfoEntity p);
	public void update(ProductInfoEntity p);
	public void delete(ProductInfoEntity p);
	public List<ProductInfoEntity> find_by_id(int id);
	public List<ProductInfoEntity> find_all();
}
