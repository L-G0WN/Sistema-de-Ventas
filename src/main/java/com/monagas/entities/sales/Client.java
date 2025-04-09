package com.monagas.entities.sales;

import com.monagas.entities.DetailPerson;
import com.monagas.entities.Person;
import com.monagas.entities.login.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "cedula", length = 10, nullable = false)
    private String cedula;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id", updatable = false, nullable = false)
    private User registeredBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id", nullable = true)
    private User updatedBy;
}
