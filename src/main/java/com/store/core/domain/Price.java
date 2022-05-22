package com.store.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Price")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="price_list")
    private Integer id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="branch_id")
    private Branch branch;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
    private Integer priority;
    private Double price;
    private String currency;
}

