package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Game;

import java.util.List;

public interface IGameRepository {

    public List<Game> getAllGames();

    public Game getGameById(int id);

    public Game addGame(Game game);

    public Game updateGame(Game game);

    public void deleteGame(int id);


}
