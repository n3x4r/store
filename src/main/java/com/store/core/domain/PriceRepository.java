package com.store.core.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {
    @Query(value =
            "SELECT p.*  FROM Price p INNER JOIN " +
                    "(SELECT MAX(priority) AS pri , branch_id, product_id " +
                    "FROM Price " +
                    "WHERE branch_id = :branchId " +
                    "AND product_id = :productId " +
                    "AND :appDate BETWEEN start_date AND end_date ) priceTemp " +
                    "ON  p.priority = priceTemp.pri " +
                    "AND p.branch_id = priceTemp.branch_id " +
                    "AND p.product_id = priceTemp.product_id " +
                    "WHERE :appDate BETWEEN p.start_date AND p.end_date", nativeQuery = true)
    Price getActualPrice(@Param("appDate") Date appDate, @Param("productId") Integer productId, @Param("branchId") Integer branchId);
}
