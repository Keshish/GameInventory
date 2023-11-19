package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.Publisher;

import java.util.List;
import java.util.Optional;

public interface IPublisherRepository {

    List<Publisher> getAllPublishers();

    Optional<Publisher> getPublisherById(Long id);

    Publisher addPublisher(Publisher publisher);

    Publisher updatePublisher(Publisher publisher);

    void deletePublisher(Long id);
}
