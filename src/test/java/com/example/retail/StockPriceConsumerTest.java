package com.example.retail;

import com.example.retail.writer.StockPriceUatWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("uat")
public class StockPriceConsumerTest {

    @Autowired
    private StockPriceUatWriter stockPriceUatWriter;

    @Test
    public void testConsumer() {
//        List<StockPrice> stockPrices = kafkaStockPriceReader.read();
        stockPriceUatWriter.writeToUat();

    }
}
