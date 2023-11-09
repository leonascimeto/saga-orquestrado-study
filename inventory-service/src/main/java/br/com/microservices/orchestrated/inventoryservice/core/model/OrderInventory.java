package br.com.microservices.orchestrated.inventoryservice.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_invetory")
public class OrderInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;
    @Column(nullable = false)
    private String orderId;
    @Column(nullable = false)
    private String transactionId;
    @Column(nullable = false)
    private Integer orderQuantity;
    @Column(nullable = false)
    private Integer oldQuantity;
    @Column(nullable = false)
    private Integer newQuantity;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        var now = LocalDateTime.now();
        createdAt = now;
        updateAt = now;
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = LocalDateTime.now();
    }
}
