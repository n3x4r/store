package com.store.core.service;

import com.store.core.domain.Price;
import com.store.core.domain.PriceRepository;
import com.store.core.dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> findAll() {
        return StreamSupport.stream(priceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Price savePrice(Price newPrice) {
        return priceRepository.save(newPrice);
    }

    public Price updatePrice(Integer id, Price updateSourcePrice) {
        return priceRepository.save(updateSourcePrice);
    }

    public void deletePrice(Integer id) {
        priceRepository.deleteById(id);
    }

    public Optional<PriceDTO> searchPriceByDate(Date appDate, Integer productId, Integer branchId){
        return Optional.ofNullable(priceRepository.getActualPrice(appDate, productId, branchId)).map(PriceDTO::from);
    }
}
