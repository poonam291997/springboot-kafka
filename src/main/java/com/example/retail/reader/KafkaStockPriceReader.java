package com.example.retail.reader;

import com.example.retail.model.StockPrice;

import java.util.List;

public interface KafkaStockPriceReader {
    List<StockPrice> read();
}
