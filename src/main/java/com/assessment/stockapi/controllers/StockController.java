package com.assessment.stockapi.controllers;

import com.assessment.stockapi.api.response.Ticker;
import com.assessment.stockapi.model.Stock;
import com.assessment.stockapi.payload.responses.MessageResponse;
import com.assessment.stockapi.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
@CrossOrigin
public class StockController {
    private final StockService stockService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Stock>> getAllStocks(@PathVariable long userId,
                                                    @RequestParam int pageSize) {
        log.info("Getting all stocks");
        return ResponseEntity.ok(stockService.getAllStocks(pageSize, userId));
    }

    @GetMapping("/{userId}/{ticker}")
    public ResponseEntity<Ticker> getStockByTicker(@PathVariable long userId,
                                                   @PathVariable String ticker) {
        log.info("Getting stock by ticker");
        return ResponseEntity.ok(stockService.getStockByTicker(ticker, userId));
    }

    @PostMapping("/{userId}/like/{ticker}")
    public ResponseEntity<MessageResponse> likeOrDislikeStock(@PathVariable long userId,
                                                              @PathVariable String ticker) {
        log.info("Liking or disliking stock");
        return ResponseEntity.ok(new MessageResponse(stockService.likeOrUnlikeStock(ticker, userId)));
    }
}
