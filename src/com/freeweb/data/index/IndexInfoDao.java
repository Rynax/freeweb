package com.freeweb.data.index;

import java.util.List;

public interface IndexInfoDao {
	public void add(IndexInfoEntity p);
	public void update(IndexInfoEntity p);
	public void delete(IndexInfoEntity p);
	public List<IndexInfoEntity> find_all();
}
