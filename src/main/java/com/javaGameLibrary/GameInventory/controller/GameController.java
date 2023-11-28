package com.javaGameLibrary.GameInventory.controller;
import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.Domain.Inventory;
import com.javaGameLibrary.GameInventory.Domain.Price;
import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.controller.dto.GameRequest;
import com.javaGameLibrary.GameInventory.repository.abstraction.IGameRepository;
import com.javaGameLibrary.GameInventory.repository.abstraction.IInventoryRepository;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPriceRepository;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPublisherRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final IInventoryRepository inventoryRepository;

    @Operation(summary = "Retrieve list of all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all games retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid URL parameters supplied")
    })
    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }


    @Operation(summary = "Create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters or Publisher not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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


    @Operation(summary = "Update an existing game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters, Game not found, or Publisher not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Delete a game by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{gameId}")
    @Transactional
    public ResponseEntity<String> deleteGame(@PathVariable int gameId) {
        Game game = gameRepository.getGameById(gameId);

        if (game == null) {
            return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
        }

        try {
            Price price = game.getPrice();
            if (price != null) {
                priceRepository.deletePrice(price); // Assuming you have a deletePrice method
            }
            Inventory inventory = inventoryRepository.getinventoryById(gameId);
            if (inventory != null) {
                inventoryRepository.deleteInventoryById(inventory.getInventoryId().intValue());
            }
            gameRepository.deleteGame(gameId);

            return new ResponseEntity<>("Game deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting game: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}