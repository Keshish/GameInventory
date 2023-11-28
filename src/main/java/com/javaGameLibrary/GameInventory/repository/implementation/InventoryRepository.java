package com.javaGameLibrary.GameInventory.repository.implementation;

import com.javaGameLibrary.GameInventory.Domain.Inventory;
import com.javaGameLibrary.GameInventory.repository.abstraction.IInventoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Repository
@Service
@RequiredArgsConstructor
public class InventoryRepository implements IInventoryRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public List<Inventory> getAllInventories() {
        try {
            return entityManager.createQuery("SELECT i FROM Inventory i", Inventory.class)
                    .getResultList();
        } catch (Exception e) {
            log.error("Error in getAllInventory: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public Inventory getinventoryById(int id) {
        try {
            return entityManager.createQuery("SELECT i FROM Inventory i WHERE i.id = :id", Inventory.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            log.error("Error in getinventoryById: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Inventory getinventoryByGameId(int id) {
        try {
            return entityManager.createQuery("SELECT i FROM Inventory i WHERE i.game.id = :id", Inventory.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            log.error("Error in getinventoryByGameId: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public void deleteGameById(int id) {
        try {
            entityManager.createQuery("DELETE FROM Game g WHERE g.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            log.error("Error in deleteGameById: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteInventoryById(int id) {
        try {
            entityManager.createQuery("DELETE FROM Inventory i WHERE i.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            log.error("Error in deleteInventoryById: " + e.getMessage());
            throw e;
        }
    }
}
