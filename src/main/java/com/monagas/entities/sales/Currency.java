package com.monagas.entities.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Currencys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {

    @Id
    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "status", nullable = false)
    private Boolean status;
}
