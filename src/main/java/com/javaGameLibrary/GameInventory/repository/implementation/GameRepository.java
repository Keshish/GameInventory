package com.javaGameLibrary.GameInventory.repository.implementation;

import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.repository.abstraction.IGameRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class GameRepository implements IGameRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Game> getAllGames() {
        return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }

    @Override
    public Game getGameById(int id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        entityManager.persist(game);
        return game;
    }

    @Override
    @Transactional
    public Game updateGame(Game game) {
        return entityManager.merge(game);
    }

    @Override
    @Transactional
    public void deleteGame(int id) {
        Game game = entityManager.find(Game.class, id);
        if (game != null) {
            entityManager.remove(game);
        }
    }
}
