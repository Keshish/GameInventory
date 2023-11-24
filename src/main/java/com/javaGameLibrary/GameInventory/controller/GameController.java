package com.javaGameLibrary.GameInventory.controller;
import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.Domain.Price;
import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.controller.dto.GameRequest;
import com.javaGameLibrary.GameInventory.repository.abstraction.IGameRepository;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPriceRepository;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final IGameRepository gameRepository;
    private final IPublisherRepository publisherRepository;
    private final IPriceRepository priceRepository;

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Game> createGame(@RequestBody GameRequest gameDto) {
        try {
            Publisher publisher = publisherRepository.getPublisherById(gameDto.getPublisherId())
                    .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

            Price price = new Price();
            price.setValue(gameDto.getPriceValue());
            price.setCurrency(gameDto.getCurrency());

            Game game = new Game();
            game.setTitle(gameDto.getTitle());
            game.setReleaseDate(gameDto.getReleaseDate());
            game.setGenre(gameDto.getGenre());
            game.setPlatform(gameDto.getPlatform());
            game.setPrice(price);

            price.setGame(game);

            game.setPublisher(publisher);

            priceRepository.savePrice(price);

            Game createdGame = gameRepository.addGame(game);

            return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception appropriately
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody GameRequest gameDto) {
        try {
            Game existingGame = gameRepository.getGameById(id);

            if (existingGame == null) {
                throw new IllegalArgumentException("Game not found");
            }

            Publisher publisher = publisherRepository.getPublisherById(gameDto.getPublisherId())
                    .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

            existingGame.setTitle(gameDto.getTitle());
            existingGame.setReleaseDate(gameDto.getReleaseDate());
            existingGame.setGenre(gameDto.getGenre());
            existingGame.setPlatform(gameDto.getPlatform());

            Price price = existingGame.getPrice();
            price.setValue(gameDto.getPriceValue());
            price.setCurrency(gameDto.getCurrency());

            price.setGame(existingGame);

            existingGame.setPublisher(publisher);

            priceRepository.savePrice(price);

            Game updatedGame = gameRepository.updateGame(existingGame);

            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (Exception e) {
            // Handle the exception appropriately
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}