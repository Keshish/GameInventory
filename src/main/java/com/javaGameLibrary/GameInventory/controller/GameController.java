package com.javaGameLibrary.GameInventory.controller;

import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.Domain.Price;
import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.controller.dto.GameRequest;
import com.javaGameLibrary.GameInventory.repository.implementation.GameRepository;
import com.javaGameLibrary.GameInventory.repository.implementation.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/games")
@RequiredArgsConstructor
@RestController
public class GameController {
   private final GameRepository gameRepository;
   private  final PublisherRepository publisherRepository;

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
public ResponseEntity<Game> createGame(@RequestBody GameRequest gameDto) {
    Publisher publisher = publisherRepository.getPublisherById(gameDto.getPublisherId())
            .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

    Game game = new Game();
    game.setTitle(gameDto.getTitle());
    game.setReleaseDate(gameDto.getReleaseDate());
    game.setGenre(gameDto.getGenre());
    game.setPlatform(gameDto.getPlatform());

    Price price = new Price();
    price.setValue(gameDto.getPriceValue());
    price.setCurrency(gameDto.getCurrency());
    price.setGame(game);

    game.setPrice(price);
    game.setPublisher(publisher);

    // Perform the repository operation directly in the controller
    Game createdGame = gameRepository.addGame(game);

    return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
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