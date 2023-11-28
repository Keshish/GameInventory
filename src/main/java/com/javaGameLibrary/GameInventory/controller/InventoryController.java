package com.javaGameLibrary.GameInventory.controller;


import com.javaGameLibrary.GameInventory.Domain.Game;
import com.javaGameLibrary.GameInventory.Domain.Inventory;
import com.javaGameLibrary.GameInventory.repository.implementation.InventoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryRepository inventoryRepository;


    @Operation(summary = "Retrieve list of all inventories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all inventories retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid URL parameters supplied")
    })
    @GetMapping("/all")
    public List<Inventory> getAllInventories() {
        return inventoryRepository.getAllInventories();
    }
}
