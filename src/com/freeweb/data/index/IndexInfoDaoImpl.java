package com.freeweb.data.index;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class IndexInfoDaoImpl extends JdbcDaoSupport implements IndexInfoDao {
	@Override
	public void add(IndexInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`index_info` VALUES (null, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_prod_id(), p.get_priority());
	}
	
	@Override
	public void update(IndexInfoEntity p) {
		String sql = "UPDATE `freeweb`.`index_info` SET prod_id=?, priority=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_prod_id(), p.get_priority(), p.get_id());
	}
	
	@Override
	public void delete(IndexInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`index_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_id());
	}
	
	@Override
	public List<IndexInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`index_info`";
		return this.getJdbcTemplate().query(sql, new IndexInfoMapper());
	}
}
