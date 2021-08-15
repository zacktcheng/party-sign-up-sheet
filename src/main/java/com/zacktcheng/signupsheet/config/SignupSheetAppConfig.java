package com.zacktcheng.signupsheet.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.zacktcheng.signupsheet")
public class SignupSheetAppConfig implements WebMvcConfigurer {

    private final String CLEARDB_PartySignupSheet_Username = System.getenv("CLEARDB_PartySignupSheet_Username");
    private final String CLEARDB_PartySignupSheet_Password = System.getenv("CLEARDB_PartySignupSheet_Password");
    
    @Bean
    InternalResourceViewResolver viewResolver() {
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean
    JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(CLEARDB_PartySignupSheet_Username);
        dataSource.setPassword(CLEARDB_PartySignupSheet_Password);
        dataSource.setUrl("jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_b7898bf0cee6a84?useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/addedResourceHandler/**").addResourceLocations("/");
    }
}
