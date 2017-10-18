package com.freeweb.data.user;

import java.util.List;

public interface UserInfoDao {
	public void add(UserInfoEntity p);
	public void update(UserInfoEntity p);
	public void delete(UserInfoEntity p);
	public List<UserInfoEntity> find_by_name(String name);
	public List<UserInfoEntity> find_all();
}
