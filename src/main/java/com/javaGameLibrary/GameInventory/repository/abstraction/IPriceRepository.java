package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Price;

public interface IPriceRepository {
    Price savePrice(Price price);

    void deletePrice(Price price);
}
