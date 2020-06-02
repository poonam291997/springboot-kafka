package com.example.retail.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public  class StockPrice {
        @CsvBindByName(required = true, column = "stockSymbol")
        private String stockSymbol;

        @CsvBindByName(required = true, column = "date")
        @CsvDate("dd-MM-yyyy")
        private LocalDate date;

        @CsvBindByName(required = true, column = "price")
        private Double price;

        public StockPrice(String s, LocalDate localDate, double v) {
                this.stockSymbol =s;
                this.date=localDate;
                this.price=v;
        }

        public StockPrice() {

        }

        public String getStockSymbol() {
                return stockSymbol;
        }

        public void setStockSymbol(String stockSymbol) {
                this.stockSymbol = stockSymbol;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }
}