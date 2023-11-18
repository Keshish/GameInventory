package com.javaGameLibrary.GameInventory.controller;

import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.Domain.Price;
import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.controller.dto.GameRequest;
import com.javaGameLibrary.GameInventory.repository.implementation.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/games")
@RequiredArgsConstructor
@RestController
public class GameController {
   private final GameRepository gameRepository;

//    @GetMapping(value = "/{matchId}/{username}")
//    public BetDto getBet(@PathVariable Integer matchId, @PathVariable String username) {
//        Bet bet = gameRepository.findByMatchIdAndUsername(matchId, username);
//        return BetDto.fromBet(bet);
//    }

    @GetMapping("/all")
    public List<Game>  getAllGames() {
        var games = gameRepository.getAllGames();
        return gameRepository.getAllGames();
    }


//    @GetMapping("/{gameId}")
//    public Game getGameById(@PathVariable Long gameId) {
//        return gameService.getGameById(gameId);
//    }
//
@PostMapping
public Game addGame(@RequestBody GameRequest gameRequest) {
    // Convert the GameRequest to the internal Game entity
    Game game = convertToEntity(gameRequest);
    // Save the entity using the repository
    return gameRepository.addGame(game);
}
//
//    @PutMapping("/{gameId}")
//    public Game updateGame(@PathVariable Long gameId, @RequestBody Game game) {
//        return gameService.updateGame(gameId, game);
//    }
//
//    @DeleteMapping("/{gameId}")
//    public void deleteGame(@PathVariable Long gameId) {
//        gameService.deleteGame(gameId);
//    }


    private Game convertToEntity(GameRequest gameRequest) {
        // You can manually map fields from the request to the entity
        Game game = new Game();
        game.setTitle(gameRequest.getTitle());
        game.setReleaseDate(gameRequest.getReleaseDate());
        game.setGenre(gameRequest.getGenre());
        game.setPlatform(gameRequest.getPlatform());
        // You might want to set other properties if needed
        return game;
    }
}