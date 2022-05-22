package com.store.core.api;

import com.store.core.dto.PriceDTO;
import com.store.core.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = {"/price"})
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceDTO> getActualPriceByDateAndProductIdAndBranchId(@RequestParam(value = "appDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") Date appDate,
                                                                                @RequestParam(value = "productId") Integer productId,
                                                                                @RequestParam(value = "branchId") Integer branchId)
    {
        return  ResponseEntity.of(priceService.searchPriceByDate(appDate, productId, branchId));
    }

}