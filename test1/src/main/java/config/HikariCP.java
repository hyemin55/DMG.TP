package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {


    public static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/alcohol_retail_store");
        config.setUsername("root");
        config.setPassword("1234");

        // 추가 설정 (필요에 따라 조정)
        config.setMaximumPoolSize(10); // 최대 커넥션 수
        config.setMinimumIdle(5); // 최소 유휴 커넥션 수
        config.setIdleTimeout(30000); // 유휴 커넥션 타임아웃
        config.setConnectionTimeout(30000); // 커넥션 타임아웃
        config.setMaxLifetime(1800000); // 커넥션 최대 생명 주기

        return new HikariDataSource(config);
    }
}
