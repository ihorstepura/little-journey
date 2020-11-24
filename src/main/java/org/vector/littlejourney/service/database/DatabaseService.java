package org.vector.littlejourney.service.database;

public interface DatabaseService<E> {

    E add(E entity);

    void update(E entity);

    void delete(E entity);
}
