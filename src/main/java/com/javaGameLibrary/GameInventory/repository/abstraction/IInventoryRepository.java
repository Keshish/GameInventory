package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Inventory;

import java.util.List;

public interface IInventoryRepository {

    List<Inventory> getAllInventories();
    Inventory getinventoryById(int id);
    Inventory getinventoryByGameId(int id);
    void deleteGameById(int id);

    void deleteInventoryById(int id);

}
