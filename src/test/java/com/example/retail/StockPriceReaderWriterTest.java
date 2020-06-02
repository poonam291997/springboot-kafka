package com.example.retail;

import com.example.retail.model.StockPrice;
import com.example.retail.reader.StockPriceReader;
import com.example.retail.writer.StockPriceUatWriter;
import com.example.retail.writer.StockPriceWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class StockPriceReaderWriterTest {

    @Autowired
    StockPriceWriter stockPriceWriter;

    @Autowired
    StockPriceReader stockPriceReader;

    @Autowired
    StockPriceUatWriter stockPriceUatWriter;

    @Test
    public void testStockPriceReadWrite() {
        List<StockPrice> list = stockPriceReader.readStockPrices();
        for(StockPrice s : list) {
            stockPriceWriter.writeStockPrice(s);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWriteSingleStockPrice() {
        StockPrice stockPrice = new StockPrice("test",
                LocalDate.of(2222, 10, 4),
                50);
        stockPriceWriter.writeStockPrice(stockPrice);
    }

}
