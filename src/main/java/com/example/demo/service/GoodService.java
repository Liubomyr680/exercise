package com.example.demo.service;

import com.example.demo.model.Good;
import com.example.demo.repository.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    private final GoodsRepo goodRepository;

    @Autowired
    public GoodService(GoodsRepo goodRepository) {
        this.goodRepository = goodRepository;
    }

    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    public Good addGood(Good good) {
        return goodRepository.save(good);
    }

    public void updateGoodQuantity(Long goodId, int quantity) {
        Good existingGood = goodRepository.findById(goodId)
                .orElseThrow(() -> new RuntimeException("Good not found"));
        existingGood.setQuantity(quantity);
        goodRepository.save(existingGood);
    }
}