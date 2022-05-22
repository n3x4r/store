package com.store.core.configuration;

import com.store.core.common.StoreUtil;
import com.store.core.domain.Branch;
import com.store.core.domain.Price;
import com.store.core.domain.Product;
import com.store.core.service.BranchService;
import com.store.core.service.PriceService;
import com.store.core.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class InitData {

    private final PriceService priceService;
    private final ProductService productService;
    private final BranchService branchService;

    public InitData(PriceService priceService, ProductService productService,
                    BranchService branchService) {
        this.priceService = priceService;
        this.branchService = branchService;
        this.productService = productService;
    }

    @PostConstruct
    private void init(){
        log.info("Start initData");
        createScenario();
        log.info("End initData");
    }

    private void createScenario(){
        Product p1 = productService.saveProduct(new Product(null, "slim-fits trousers", "trousers"));
        Product p2 = productService.saveProduct(new Product(null, "slim t-shirt", "shirt"));
        Product p3 = productService.saveProduct(new Product(null, "sock", "sock"));
        Product p4 = productService.saveProduct(new Product(null, "coat", "coat"));

        Branch b1 = branchService.saveBranch(new Branch(null, "ZARA", "calle nueva 1"));
        Branch b2 = branchService.saveBranch(new Branch(null, "NIKE", "calle nueva 2"));
        Branch b3 = branchService.saveBranch(new Branch(null, "ADIDAS", "calle nueva 3"));

        priceService.savePrice(new Price(null, b1, StoreUtil.getDate("2020-06-14-00.00.00"), StoreUtil.getDate("2020-12-31-23.59.59"), p1, 0, Double.parseDouble("35.50"), "EUR" ));
        priceService.savePrice(new Price(null, b1, StoreUtil.getDate("2020-06-14-15.00.00"), StoreUtil.getDate("2020-06-14-18.30.00"), p1, 1, Double.parseDouble("25.45"), "EUR" ));
        priceService.savePrice(new Price(null, b1, StoreUtil.getDate("2020-06-15-00.00.00"), StoreUtil.getDate("2020-06-15-11.00.00"), p1, 1, Double.parseDouble("30.50"), "EUR" ));
        priceService.savePrice(new Price(null, b1, StoreUtil.getDate("2020-06-15-16.00.00"), StoreUtil.getDate("2020-12-31-23.59.59"), p1, 1, Double.parseDouble("38.95"), "EUR" ));
    }

}
