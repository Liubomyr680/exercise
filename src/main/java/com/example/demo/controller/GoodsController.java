package com.example.demo.controller;

import com.example.demo.model.Good;
import com.example.demo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private final GoodService goodService;

    @Autowired
    public GoodsController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/all")
    public List<Good> getAllGoods() {
        return goodService.getAllGoods();
    }

    @PostMapping("/add")
    public Good addGood(@RequestBody Good good) {
        return goodService.addGood(good);
    }

    @PutMapping("/{goodId}")
    public void updateGoodQuantity(@PathVariable Long goodId, @RequestParam int quantity) {
        goodService.updateGoodQuantity(goodId, quantity);
    }
}
