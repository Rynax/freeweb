package com.freeweb.data.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ProductInfoDaoImpl extends JdbcDaoSupport implements ProductInfoDao {
	@Override
	public int add(ProductInfoEntity p) {
		String sql = "INSERT INTO `freeweb`.`product_info` (shop_id, prod_name) VALUES (?, ?)";
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    this.getJdbcTemplate().update(
	    	new PreparedStatementCreator() {
	    		public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
	    			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    			ps.setInt(1, p.get_shop_id());
	    			ps.setString(2, p.get_prod_name());
	    			return ps;
	    		}
	    	}, keyHolder);
	    return keyHolder.getKey().intValue();
	}
	
	@Override
	public void update(ProductInfoEntity p) {
		String sql = "UPDATE `freeweb`.`product_info` SET prod_type_id=?, prod_src_price=?, prod_cur_price=?, prod_save=?, prod_desc=?, prod_spict=?, prod_mpict=?, prod_lpict=?, prod_status=? WHERE prod_id=?";
		this.getJdbcTemplate().update(sql, p.get_prod_type_id(), p.get_prod_src_price(), p.get_prod_cur_price(), p.get_prod_save(), p.get_prod_desc(), p.get_prod_spict(), p.get_prod_mpict(), p.get_prod_lpict(), p.get_prod_status(), p.get_prod_id());
	}
	
	@Override
	public void delete(ProductInfoEntity p) {
		String sql = "DELETE FROM `freeweb`.`product_info` WHERE prod_id=?";
		this.getJdbcTemplate().update(sql, p.get_prod_id());
	}
	
	@Override
	public List<ProductInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freeweb`.`product_info` WHERE prod_id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ProductInfoMapper());
	}
	
	@Override
	public List<ProductInfoEntity> find_all() {
		String sql = "SELECT * FROM `freeweb`.`product_info`";
		return this.getJdbcTemplate().query(sql, new ProductInfoMapper());
	}
}
