package org.vector.littlejourney.database.repository;

public interface CrudRepository<E> {

    E get(E entity);

    E add(E entity);

    E update(E entity);

    void delete(E entity);
}
