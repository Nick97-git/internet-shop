package mate.academy.internet.shop.service;

import java.util.List;
import mate.academy.internet.shop.model.Item;

public interface ItemService {

    Item create(Item item);

    Item get(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);
}
