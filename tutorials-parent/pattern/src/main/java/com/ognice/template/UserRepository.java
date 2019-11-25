package com.ognice.template;

import com.ognice.template.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<?> queryUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        return jdbcTemplate.excuteQuery(sql, rs -> {
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString(2));
                user.setPsw(rs.getString(3));
                users.add(user);
            }
            return users;
        }, new Object[]{});
    }
}
