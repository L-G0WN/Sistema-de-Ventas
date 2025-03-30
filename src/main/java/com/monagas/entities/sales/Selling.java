package com.monagas.entities.sales;

import com.monagas.entities.login.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Sellings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Selling implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selling_id")
    private Long sellingId;

    @OneToMany(mappedBy = "sellingId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellingProduct> sellingProducts = new ArrayList<>();

    @Column(name = "amount_total", updatable = false, nullable = false)
    private Integer amountTotal;

    @Column(name = "total", updatable = false, nullable = false)
    private Double total;

    @Column(name = "total_bs", updatable = false, nullable = false)
    private Double totalBs;

    @Column(name = "method", updatable = false, nullable = false)
    private String method;
    
    @Column(name = "is_return", updatable = false, nullable = false)
    private boolean isReturn;
    
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", updatable = false, nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "registered_by", referencedColumnName = "user_id", updatable = false, nullable = false)
    private User registeredBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date createdAt;
    
    public void addSellingProduct(Product product, Integer amount, Double purchase, Double subTotal, Double purchaseBs, Double subTotalBs) {
        SellingProduct sellingProduct = new SellingProduct();
        sellingProduct.setSellingId(this);
        sellingProduct.setProduct(product);
        sellingProduct.setAmount(amount);
        sellingProduct.setPurchase(purchase);
        sellingProduct.setSubTotal(subTotal);
        sellingProduct.setPurchaseBs(purchaseBs);
        sellingProduct.setSubTotalBs(subTotalBs);
        this.sellingProducts.add(sellingProduct);
    }
}