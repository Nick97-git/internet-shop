package mate.academy.internet.shop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internet.shop.model.Item;

public class Storage {
    private static Long itemId = 0L;
    public static List<Item> items = new ArrayList<>();

    public static void addItem(Item item) {
        item.setId(++itemId);
        items.add(item);
    }
}
