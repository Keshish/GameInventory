package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Price;

public interface IPriceRepository {
    //save price
    Price savePrice(Price price);
}
