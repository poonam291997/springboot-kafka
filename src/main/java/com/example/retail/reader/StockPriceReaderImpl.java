package com.example.retail.reader;

import com.example.retail.model.StockPrice;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class StockPriceReaderImpl implements StockPriceReader {

    @Value("${feed.filename}")
    private String fileName;

    @Override
    public List<StockPrice> readStockPrices() {
        try (Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(fileName).toURI()))) {
            List<StockPrice> stockPrices = new CsvToBeanBuilder<StockPrice>(reader)
                    .withType(StockPrice.class)
                    .build().parse();
            return stockPrices;
        } catch (Exception e) {
            throw new RuntimeException("Unable to read data from " + fileName + e.getMessage());
        }
    }
}


