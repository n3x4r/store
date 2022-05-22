package com.store.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.store.core.domain.Branch;
import com.store.core.domain.Price;
import com.store.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PriceDTO implements Serializable {

    private Integer id;
    private Branch branch;
    private Date startDate;
    private Date endDate;
    private Product product;
    private Integer priority;
    private Double price;
    private String currency;


    public static PriceDTO from(Price price){
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBranch(price.getBranch());
        priceDTO.setProduct(price.getProduct());
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setEndDate(price.getEndDate());
        priceDTO.setPrice(price.getPrice());
        return priceDTO;
    }

}
