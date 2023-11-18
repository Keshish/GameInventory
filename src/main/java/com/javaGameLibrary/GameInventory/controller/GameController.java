package com.javaGameLibrary.GameInventory.controller;

import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.repository.implementation.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/bets")
@RequiredArgsConstructor
@RestController
public class GameController {
   private final GameRepository gameRepository;

//    @GetMapping(value = "/{matchId}/{username}")
//    public BetDto getBet(@PathVariable Integer matchId, @PathVariable String username) {
//        Bet bet = gameRepository.findByMatchIdAndUsername(matchId, username);
//        return BetDto.fromBet(bet);
//    }

    @GetMapping
    public List<Game>  getAllGames() {

        return gameRepository.getAllGames();
    }

//    @GetMapping("/{gameId}")
//    public Game getGameById(@PathVariable Long gameId) {
//        return gameService.getGameById(gameId);
//    }
//
//    @PostMapping
//    public Game addGame(@RequestBody Game game) {
//        return gameService.addGame(game);
//    }
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


}