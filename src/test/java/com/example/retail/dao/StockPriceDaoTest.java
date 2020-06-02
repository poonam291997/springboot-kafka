package com.example.retail.dao;

import com.example.retail.model.StockPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Sql(statements = {"delete from stock_price where stock_symbol like 'TEST%'"},
//        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
@ActiveProfiles("uat")
public class StockPriceDaoTest {

    @Autowired
    StockPriceDao stockPriceDao;

    @Test
    public void testDao() {
        LocalDate localDate = LocalDate.of(2020, 5, 16);

        StockPrice ibmStockPrice = new StockPrice("TEST-IBM", localDate, 90.00);
        stockPriceDao.insertStockPrice(ibmStockPrice);

        StockPrice mfstStockPrice = new StockPrice("TEST-MFST", localDate, 190.00);
        stockPriceDao.insertStockPrice(mfstStockPrice);

        StockPrice ibmStockPriceReadBackFromDB = stockPriceDao.selectStockPrice(ibmStockPrice.getStockSymbol(), ibmStockPrice.getDate());
        assertEquals(ibmStockPrice.getPrice(), ibmStockPriceReadBackFromDB.getPrice());

        StockPrice mfstStockPriceReadBackFromDB = stockPriceDao.selectStockPrice(mfstStockPrice.getStockSymbol(), mfstStockPrice.getDate());
        assertEquals(mfstStockPrice.getPrice(), mfstStockPriceReadBackFromDB.getPrice());

//        List<StockPrice> stockPrices = stockPriceDao.selectAllStockPrice();
//        assertNotNull(stockPrices);
//        assertEquals(2, stockPrices.size());
//        System.out.println(stockPrices);
//        assertTrue(stockPrices.containsAll(List.of(ibmStockPrice, mfstStockPrice)));
    }
}
