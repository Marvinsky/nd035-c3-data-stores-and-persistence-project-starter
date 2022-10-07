package com.udacity.jdnd.course3.critter.configuration;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "com.udacity.course.datasource")
  public DataSource getDatasource() {
    DataSourceBuilder dsb = DataSourceBuilder.create();
    dsb.url("jdbc:mysql://localhost:3306/critter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    return dsb.build();
  }

}
