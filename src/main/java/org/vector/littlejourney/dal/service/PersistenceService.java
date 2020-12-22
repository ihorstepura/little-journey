package org.vector.littlejourney.dal.service;

import java.util.List;

public interface PersistenceService<T> {

    T getById(Long id);

    List<T> getAll();

    T insert(T e);

    T update(T e);

    void delete(Long id);
}
