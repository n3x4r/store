package com.store.core;

import com.store.core.common.StoreUtil;
import com.store.core.domain.Branch;
import com.store.core.domain.Price;
import com.store.core.domain.Product;
import com.store.core.dto.PriceDTO;
import com.store.core.service.BranchService;
import com.store.core.service.PriceService;
import com.store.core.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith( SpringExtension.class )
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreApplicationTests {

	@Autowired
	private PriceService priceService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BranchService branchService;

	@Autowired
	public TestRestTemplate template;

	@BeforeEach
	public void initData() {

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

	@ParameterizedTest
	@CsvSource({
			"2020-06-14-10.15.10,1,1,35.50",
			"2020-06-14-16.15.10,1,1,25.45",
			"2020-06-14-21.15.10,1,1,35.50",
			"2020-06-15-10.15.10,1,1,30.50",
			"2020-06-15-21.15.10,1,1,38.95"})
	void testExpectPriceByDates(String appDate, Integer productId, Integer branchId, Double expectedPrice) {
		URI targetUrl = UriComponentsBuilder
				.fromUriString("/price")
				.queryParam("appDate", appDate)
				.queryParam("productId", productId)
				.queryParam("branchId",branchId)
				.build()
				.toUri();

		ResponseEntity<PriceDTO> response = template.getForEntity(targetUrl, PriceDTO.class );
		PriceDTO prices = response.getBody();
		assert prices != null;
		assertEquals( expectedPrice, prices.getPrice() );
	}
}
