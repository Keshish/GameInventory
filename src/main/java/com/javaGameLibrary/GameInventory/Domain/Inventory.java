package com.javaGameLibrary.GameInventory.Domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "inventory")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    private int quantity;

    // Constructors, getters, and setters
}
