package com.freeweb.data.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductInfoMapper implements RowMapper<ProductInfoEntity> {
	public ProductInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ProductInfoEntity prod = new ProductInfoEntity();
		prod.set_prod_id(rs.getInt("prod_id"));
		prod.set_shop_id(rs.getInt("shop_id"));
		prod.set_prod_type_id(rs.getInt("prod_type_id"));
		prod.set_prod_name(rs.getString("prod_name"));
		prod.set_prod_src_price(rs.getInt("prod_src_price"));
		prod.set_prod_cur_price(rs.getInt("prod_cur_price"));
		prod.set_prod_save(rs.getInt("prod_save"));
		prod.set_prod_desc(rs.getString("prod_desc"));
		prod.set_prod_spict(rs.getString("prod_spict"));
		prod.set_prod_mpict(rs.getString("prod_mpict"));
		prod.set_prod_lpict(rs.getString("prod_lpict"));
		prod.set_prod_status(rs.getInt("prod_status"));
		return prod;
	}
}
