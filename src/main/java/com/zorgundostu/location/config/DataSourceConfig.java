package com.zorgundostu.location.config;

import javax.sql.DataSource;

import com.zorgundostu.location.constant.LocationConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;


@Getter
@Configuration
public class DataSourceConfig {
    @Bean(name = LocationConstants.LOCATION_DATA_SOURCE)
    @ConfigurationProperties(prefix = "mp.datasource.location")
    public DataSource createMetadataDataSource() {
        return  DataSourceBuilder.create()
                .build();
    }

    @Bean(name = LocationConstants.LOCATION_JDBC_TEMPLATE)
    @Primary
    public JdbcTemplate createMetadataJdbcTemplate(@Qualifier(LocationConstants.LOCATION_DATA_SOURCE) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
