package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.dao.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.addItem(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Item item) {
        IntStream.range(0, Storage.items.size())
                .filter(x -> item.getId().equals(Storage.items.get(x).getId()))
                .forEach(i -> Storage.items.set(i, item));
        return item;
    }

    @Override
    public boolean delete(Long id) {
        Item item = get(id).orElseThrow(
                () -> new NoSuchElementException("Can't find item with such id " + id));
        Storage.items.remove(item);
        return true;
    }
}
