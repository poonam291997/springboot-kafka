package com.example.retail;

import com.example.retail.mapper.OrderMapper;
import com.example.retail.model.StockPrice;
import com.example.retail.reader.StockPriceReader;
import com.example.retail.writer.StockPriceWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

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
        ApplicationContext applicationContext = SpringApplication.run(RetailApplication.class, args);
        StockPriceReader stockPriceReader = applicationContext.getBean(StockPriceReader.class);
        List<StockPrice> stockPriceList = stockPriceReader.readStockPrices();

        StockPriceWriter stockPriceWriter = applicationContext.getBean(StockPriceWriter.class, args);
        for(StockPrice s : stockPriceList) {
            stockPriceWriter.writeStockPrice(s);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}