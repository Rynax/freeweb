package com.freeweb.data.user_detail;

import java.util.List;

public interface UserDetailInfoDao {
	public void add(UserDetailInfoEntity p);
	public void update(UserDetailInfoEntity p);
	public void delete(UserDetailInfoEntity p);
	public List<UserDetailInfoEntity> find_by_id(int id);
	public List<UserDetailInfoEntity> find_all();
	public void register(String name);
}
