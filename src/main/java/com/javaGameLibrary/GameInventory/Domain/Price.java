package com.javaGameLibrary.GameInventory.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "price_value")
    private double value;

    private String currency;

    @OneToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;

    // Constructors, getters, setters, etc.

    @Override
    public String toString() {
        return "Price{" +
                "priceId=" + priceId +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                // Avoid including the associated Game in the toString to prevent cyclic reference
                '}';
    }
}

