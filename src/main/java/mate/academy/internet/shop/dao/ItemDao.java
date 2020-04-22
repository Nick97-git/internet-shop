package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.model.Item;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);

}
