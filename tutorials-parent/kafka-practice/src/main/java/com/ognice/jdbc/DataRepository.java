package com.ognice.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/25
 */
@Component
public class DataRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void jdbcInsertData(String sql) {
        jdbcTemplate.execute(sql);

    }
}
