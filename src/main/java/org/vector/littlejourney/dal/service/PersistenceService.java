package org.vector.littlejourney.dal.service;

import java.util.List;

public interface PersistenceService<T> {

    List<T> findAll();

    T findById(Long id);

    void add(T e);

    void update(T e);

    void delete(Long id);
}
