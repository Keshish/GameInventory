package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Inventory;

public interface IInventoryRepository {

    Inventory getinventoryById(int id);
    Inventory getinventoryByGameId(int id);
    void deleteGameById(int id);

    void deleteInventoryById(int id);

}
