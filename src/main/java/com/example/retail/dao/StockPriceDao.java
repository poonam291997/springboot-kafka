package com.example.retail.dao;

import com.example.retail.model.StockPrice;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceDao {
    void insertStockPrice(StockPrice stockPrice);

    StockPrice selectStockPrice(String stockSymbol, LocalDate date);

    List<StockPrice> selectAllStockPrice();

    void deleteStockPrice(String stockSymbol, LocalDate date);
}