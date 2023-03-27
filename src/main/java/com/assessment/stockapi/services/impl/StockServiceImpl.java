package com.assessment.stockapi.services.impl;

import com.assessment.stockapi.api.response.StockResponse;
import com.assessment.stockapi.api.response.Ticker;
import com.assessment.stockapi.exceptions.StockNotFoundException;
import com.assessment.stockapi.exceptions.UserNotFoundException;
import com.assessment.stockapi.exceptions.UserNotLoggedInException;
import com.assessment.stockapi.model.Like;
import com.assessment.stockapi.model.Stock;
import com.assessment.stockapi.model.User;
import com.assessment.stockapi.repositories.LikeRepository;
import com.assessment.stockapi.repositories.StocksRepository;
import com.assessment.stockapi.repositories.UserRepository;
import com.assessment.stockapi.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StocksRepository stocksRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    @Value("${polygon.api.key}")
    private String apiKey;
    @Value("${polygon.api.url}")
    private String apiUrl;

    @Override
    public List<Stock> getAllStocks(int size, long userId) {
        log.info("Getting all stocks");
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        if (!user.isActive())
            throw new UserNotLoggedInException("User is not logged in");
        String url = apiUrl+"?market=stocks&active=true&limit="+size+"&apiKey="+apiKey;
        log.info("Url: {}", url);
        ResponseEntity<StockResponse> response = restTemplate.getForEntity(url, StockResponse.class);
        if (response.getStatusCode().value() == 200) {
            log.info("Response from polygon: {}", Objects.requireNonNull(response.getBody()).getRequestId());
            List<Stock> stocks = new ArrayList<>();
            StockResponse stockResponse = response.getBody();
            stockResponse.getResults().forEach(result -> {
                Stock stock = stocksRepository.findByTicker(result.getTicker()).orElse(new Stock());
                modelMapper.map(result, stock);
                stocks.add(stock);
            });
            stocksRepository.saveAll(stocks);
            return stocks;
        }
        throw new RuntimeException("Error getting stocks");
    }

    @Override
    public Ticker getStockByTicker(String ticker, long userId) {
        log.info("Getting stock by ticker");
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (!user.isActive())
            throw new RuntimeException("User is not logged in");
        String url = apiUrl+"/"+ticker+"?apiKey="+apiKey;
        ResponseEntity<Ticker> response = restTemplate.getForEntity(url, Ticker.class);
        if (response.getStatusCode().value() == 200) {
            log.info("Response from polygon: {}", Objects.requireNonNull(response.getBody()).getRequestId());
            return response.getBody();

        }
        throw new RuntimeException("Error getting stocks");
    }

    @Override
    public String likeOrUnlikeStock(String ticker, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        if (!user.isActive()) {
            throw new UserNotLoggedInException("User is not logged in");
        }
        Stock stock = stocksRepository.findByTicker(ticker)
                .orElseThrow(()-> new StockNotFoundException("Stock not found"));
        Like like = likeRepository.findByUserIdAndStockId(userId, stock.getId())
                .orElse(new Like());
        if (like.getId() == null) {
            like.setStockId(stock.getId());
            like.setUserId(userId);
            likeRepository.save(like);
            stock.setNoOfLikes(stock.getNoOfLikes() + 1);
            stocksRepository.save(stock);
            return "Stock liked";
        } else {
            likeRepository.delete(like);
            stock.setNoOfLikes(stock.getNoOfLikes() - 1);
            stocksRepository.save(stock);
            return "Stock unliked";
        }
    }
}
