package com.example.demo.service;

import com.example.demo.model.Good;
import com.example.demo.repository.GoodsRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GoodServiceTest {

    @InjectMocks
    private GoodService goodService;
    @Mock
    private GoodsRepo goodRepository;
    @Test
    public void updateGoodQuantity() {

        Long goodId = 1L;
        int newQuantity = 10;
        Good existingGood = new Good();
        existingGood.setId(goodId);
        existingGood.setName("Iphone");

        when(goodRepository.findById(goodId)).thenReturn(Optional.of(existingGood));

        goodService.updateGoodQuantity(goodId, newQuantity);

        verify(goodRepository, times(1)).findById(goodId);
        verify(goodRepository, times(1)).save(existingGood);
        assertEquals(newQuantity, existingGood.getQuantity());
    }
}
