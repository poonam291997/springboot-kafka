package com.example.retail.writer;

import com.example.retail.model.StockPrice;
import com.example.retail.reader.KafkaStockPriceReaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPriceUatWriterImpl implements StockPriceUatWriter {
    @Autowired
    private KafkaStockPriceReaderImpl kafkaStockPriceReader;

    private volatile boolean isTerminated = false;

    @Autowired
    private StockPriceWriter stockPriceWriter;

    @Override
    public void writeToUat() {
        while (!isTerminated) {
            List<StockPrice> stockPrices = kafkaStockPriceReader.read();
            for (int i = 0; i < stockPrices.size(); i++) {
                stockPriceWriter.writeStockPrice(stockPrices.get(i));
                if (i % 2 == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            // commiting offset- data has been read till this offset
            kafkaStockPriceReader.commit();
        }
    }
}
