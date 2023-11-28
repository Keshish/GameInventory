package com.javaGameLibrary.GameInventory.repository.implementation;

import com.javaGameLibrary.GameInventory.Domain.Publisher;
import com.javaGameLibrary.GameInventory.repository.abstraction.IPublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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



    @Override
    public Page<Publisher> getPaginatedPublishers(Pageable pageable) {
        Sort sort = pageable.getSort();
        String orderByClause = sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(", "));

        TypedQuery<Publisher> query = entityManager.createQuery("SELECT p FROM Publisher p ORDER BY " + orderByClause, Publisher.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Publisher> resultList = query.getResultList();

        return new PageImpl<>(resultList, pageable, getCount());
    }

    private long getCount() {
        return entityManager.createQuery("SELECT COUNT(p) FROM Publisher p", Long.class).getSingleResult();
    }

}
