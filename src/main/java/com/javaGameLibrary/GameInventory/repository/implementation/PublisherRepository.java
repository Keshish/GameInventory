package com.javaGameLibrary.GameInventory.repository.implementation;

import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PublisherRepository implements IPublisherRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Publisher> getAllPublishers() {
        return entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }

    @Override
    public Optional<Publisher> getPublisherById(Long id) {
        return Optional.ofNullable(entityManager.find(Publisher.class, id));
    }

    @Override
    @Transactional
    public Publisher addPublisher(Publisher publisher) {
        entityManager.persist(publisher);
        return publisher;
    }

    @Override
    @Transactional
    public Publisher updatePublisher(Publisher publisher) {
        return entityManager.merge(publisher);
    }

    @Override
    @Transactional
    public void deletePublisher(Long id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        if (publisher != null) {
            entityManager.remove(publisher);
        }
    }
}
