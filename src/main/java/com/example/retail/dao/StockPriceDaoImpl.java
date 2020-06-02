package com.example.retail.dao;

import com.example.retail.mapper.StockPriceMapper;
import com.example.retail.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class StockPriceDaoImpl implements StockPriceDao {

    @Autowired
    private StockPriceMapper stockPriceMapper;

    @Override
    public void insertStockPrice(StockPrice stockPrice) {
        stockPriceMapper.insertStockPrice(stockPrice);
    }

    @Override
    public StockPrice selectStockPrice(String stockSymbol, LocalDate date) {
        return stockPriceMapper.selectStockPrice(stockSymbol, date);
    }

    @Override
    public List<StockPrice> selectAllStockPrice() {
        return stockPriceMapper.selectAllStockPrice();
    }

    @Override
    public void deleteStockPrice(String stockSymbol, LocalDate date) {
        stockPriceMapper.deleteStockPrice(stockSymbol, date);
    }
}
