package com.javaGameLibrary.GameInventory.repository.implementation;

import com.javaGameLibrary.GameInventory.Domain.Price;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPriceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class PriceRepository implements IPriceRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    //save price implementation
    @Override
    public Price savePrice(Price price) {
        try {
            entityManager.persist(price);
            return price;
        } catch (Exception e) {
            log.error("Error in savePrice: " + e.getMessage());
            throw e;
        }
    }

}
