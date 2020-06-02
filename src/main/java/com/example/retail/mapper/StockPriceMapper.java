package com.example.retail.mapper;


import com.example.retail.dao.StockPriceDao;
import com.example.retail.model.StockPrice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StockPriceMapper extends StockPriceDao {

    @Insert("INSERT INTO stock_price(stock_symbol, date, price) VALUES ( #{stockSymbol}, #{date}, #{price} )")
    void insertStockPrice(StockPrice stockPrice);

    @Select("SELECT stock_symbol AS stockSymbol, date, price FROM stock_price WHERE stock_symbol = #{stockSymbol} AND date = #{date}")
    StockPrice selectStockPrice(String stockSymbol, LocalDate date);

    @Select("SELECT stock_symbol as stockSymbol, date, price FROM stock_price")
    List<StockPrice> selectAllStockPrice();

    @Delete("DELETE FROM stock_price WHERE stock_symbol = #{stockSymbol} AND date = #{date}")
    void deleteStockPrice(String stockSymbol, LocalDate date);
}