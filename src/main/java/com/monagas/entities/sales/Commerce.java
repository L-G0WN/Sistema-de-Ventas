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
@Table(name = "Commerce")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commerce implements Serializable {

    @Id
    @Column(name = "commerce_id")
    private Long commerceId;

    @Column(name = "type", length = 2, nullable = false)
    private String type;

    @Column(name = "rif", length = 50, nullable = false)
    private String rif;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
