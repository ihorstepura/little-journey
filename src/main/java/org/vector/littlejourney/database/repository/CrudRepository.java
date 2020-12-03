package org.vector.littlejourney.database.repository;

public interface CrudRepository<E> {

    E getById(int id);

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}
