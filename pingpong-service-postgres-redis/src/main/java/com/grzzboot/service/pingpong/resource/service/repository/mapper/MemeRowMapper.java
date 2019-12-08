package com.grzzboot.service.pingpong.resource.service.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grzzboot.service.pingpong.resource.service.domain.MemeEntity;

public class MemeRowMapper implements RowMapper<MemeEntity> {

	@Override
	public MemeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MemeEntity(rs.getString("MEME"));
	}

}
