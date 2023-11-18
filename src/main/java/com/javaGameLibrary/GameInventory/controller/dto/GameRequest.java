package com.javaGameLibrary.GameInventory.controller.dto;

import lombok.Data;

@Data
public class GameRequest {
    private String title;
    private String releaseDate;
    private String genre;
    private String platform;
    // You might add validation annotations here based on your requirements
}