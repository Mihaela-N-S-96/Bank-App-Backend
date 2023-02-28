//package com.spring.test.SpringSecurity_JWT_test.configurations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@Configuration
//public class OtpScheduler {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    public void init() throws SQLException {
//        Connection connection = dataSource.getConnection();
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//        //ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/delete_old_otps.sql"));
//
//     //   jdbcTemplate.execute("CREATE EVENT delete_old_otps_event ON SCHEDULE EVERY 2 MINUTE DO CALL delete_old_otps()");
//
//        connection.close();
//    }
//}
