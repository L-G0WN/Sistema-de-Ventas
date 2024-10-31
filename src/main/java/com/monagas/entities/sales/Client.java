package com.monagas.entities.sales;

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
import lombok.ToString;
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

    @Column(name = "type", length = 2, nullable = false)
    private String type;

    @Column(name = "cedula", length = 50, nullable = false)
    private String cedula;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "code", length = 4, nullable = false)
    private String code;

    @Column(name = "phone", length = 7, nullable = false)
    private String phone;

    @Column(name = "address", length = 255, nullable = true)
    private String address;

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
