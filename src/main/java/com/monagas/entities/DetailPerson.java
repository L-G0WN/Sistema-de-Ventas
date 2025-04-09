package com.monagas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DetailsPersons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailPerson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detaillPerson_id")
    private Long detailPersonId;

    @Column(name = "phone", length = 12, nullable = true)
    private String phone;
}
