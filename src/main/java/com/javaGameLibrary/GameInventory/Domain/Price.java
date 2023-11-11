package com.javaGameLibrary.GameInventory.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "price")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    private double value;
    private String currency;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
