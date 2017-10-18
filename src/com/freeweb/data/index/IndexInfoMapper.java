package com.freeweb.data.index;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IndexInfoMapper implements RowMapper<IndexInfoEntity> {
	public IndexInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		IndexInfoEntity index = new IndexInfoEntity();
		index.set_prod_id(rs.getInt("prod_id"));
		index.set_id(rs.getInt("id"));
		index.set_priority(rs.getInt("priority"));
		return index;
	}
}
