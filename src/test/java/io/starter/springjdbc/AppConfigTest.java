package io.starter.springjdbc;


import io.starter.springjdbc.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AppConfigTest {

    @Test
    public void testAppConfig() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);

        Connection conn = ds.getConnection();
        assertNotNull(conn);
        conn.close();

        List<User> users = ctx.getBean(UserRepository.class).findAllUsers();
        assertEquals(3, users.size());
    }
}
