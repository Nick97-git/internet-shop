package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.exceptions.DataProcessingException;

public interface GenericService<T, R> {

    T create(T element) throws DataProcessingException;

    T get(R id) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;

    T update(T element) throws DataProcessingException;

    boolean delete(R id) throws DataProcessingException;
}
