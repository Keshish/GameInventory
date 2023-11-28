package com.javaGameLibrary.GameInventory.controller;

import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPublisherRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final IPublisherRepository publisherRepository;

    @GetMapping("/all")
    public List<Publisher> getAllPublishers() {
        return publisherRepository.getAllPublishers();

    }

    @Operation(summary = "Retrieve paginated list of publishers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated list of publishers retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid url params supplied")
    })
    @GetMapping("/paginated-publishers")
    public Page<Publisher> getPaginatedPublishers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "publisherId") String sortField,
            @RequestParam(name = "order", defaultValue = "asc") String sortOrder
    ) {
        if (!sortOrder.equalsIgnoreCase("desc") && !sortOrder.equalsIgnoreCase("asc")) {
            throw new IllegalArgumentException("Invalid sorting param!!!");
        }

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        return publisherRepository.getPaginatedPublishers(pageable);
    }



}
