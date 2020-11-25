package org.vector.littlejourney.database.service;

public interface CrudRepository<E> {

    E get(int id);

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}
