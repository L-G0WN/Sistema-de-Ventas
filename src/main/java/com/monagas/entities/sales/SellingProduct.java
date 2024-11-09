package com.monagas.entities.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "selling_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellingProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_product_id")
    private Long sellingProductId;

    @ManyToOne
    @JoinColumn(name = "selling_id", referencedColumnName = "selling_id", nullable = false)
    private Selling sellingId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "purchase", nullable = false)
    private Double purchase;

    @Column(name = "subTotal", nullable = false)
    private Double subTotal;

    @Column(name = "purchase_bs", nullable = false)
    private Double purchaseBs;

    @Column(name = "subTotal_bs", nullable = false)
    private Double subTotalBs;
}
