package com.assessment.stockapi.services;

import com.assessment.stockapi.api.response.Ticker;
import com.assessment.stockapi.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getAllStocks(int size, long userId);
    Ticker getStockByTicker(String ticker, long userId);
    String likeOrUnlikeStock(String ticker, long userId);
}
