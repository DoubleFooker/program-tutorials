package com.ognice.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultMapper {
    List<?> pareseResult(ResultSet resultSet) throws SQLException;
}
