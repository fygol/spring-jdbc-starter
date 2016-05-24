package io.starter.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public List<User> findAllUsers() {
        List<User> users = jdbcTemplate
                .query("select * from users", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        long id = rs.getLong("id");
                        String name = rs.getString("name");

                        User user = new User(id, name);
                        return user;
                    }
                });

        // using java 8 lamdba expression
//        jdbcTemplate
//                .query("select * from users", (rs, rowNum) -> {
//                    long id = rs.getLong("id");
//                    String name = rs.getString("name");
//
//                    User user = new User(id, name);
//                    return user;
//                });


        return users;
    }

}
