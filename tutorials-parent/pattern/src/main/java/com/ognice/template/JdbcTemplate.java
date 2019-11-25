package com.ognice.template;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * 模版模式
 * 通过抽象类定义抽象方法 实现用户自定义部分功能
 */
@RequiredArgsConstructor
public class JdbcTemplate {
    private static MysqlDataSource mysqlDataSource;

    private void generDataSource(String url, String username, String psw) {
        if (mysqlDataSource == null) {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setPassword(psw);
            mysqlDataSource.setUser(username);
            mysqlDataSource.setURL(url);
        }
    }

    public List<?> excuteQuery(String sql, ResultMapper resultMapper, Object[] values) {
        generDataSource("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "123456");
        //获得连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<?> list = Collections.emptyList();
        try {
            connection = mysqlDataSource.getConnection();
            //创建句柄
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i, values[i]);
            }
            //执行语句
            resultSet = preparedStatement.executeQuery();
            //解析结果
            //这个环节通过用户自定义，其他环节由模板定义。不限于抽象类，接口也可实现
            list = resultMapper.pareseResult(resultSet);
            //关闭资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection(connection);
                this.closePreparedStatement(preparedStatement);
                this.closeResultSet(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
