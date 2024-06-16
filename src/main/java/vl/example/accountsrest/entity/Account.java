package vl.example.accountsrest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_account", schema = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_id")
    private Coin coin;

    @Column(name = "c_number")
    private String number;

    @Column(name = "c_quantity")
    private Double quantity;

    @Column(name = "c_price")
    private Double price;

    @Column(name = "c_amount")
    private Double amount;

    @Column(name = "c_created_at")
    private LocalDateTime createdAt;

    @Column(name = "c_updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "c_status")
    private Status status;
}
