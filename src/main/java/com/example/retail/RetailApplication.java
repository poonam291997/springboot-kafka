package com.example.retail;

import com.example.retail.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@MapperScan("com.example.retail.mapper")
@Configuration
public class RetailApplication {

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "orderMapper")
    public MapperFactoryBean orderMapper(final SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean mapper = new MapperFactoryBean();
        mapper.setMapperInterface(OrderMapper.class);
        mapper.setSqlSessionTemplate(sqlSessionTemplate(sqlSessionFactory));
        return mapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(RetailApplication.class, args);
    }

}