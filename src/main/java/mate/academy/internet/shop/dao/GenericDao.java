package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.exceptions.DataProcessingException;

public interface GenericDao<T,R> {

    T create(T element) throws DataProcessingException;

    Optional<T> get(R id) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;

    T update(T element) throws DataProcessingException;

    boolean delete(R id) throws DataProcessingException;
}
