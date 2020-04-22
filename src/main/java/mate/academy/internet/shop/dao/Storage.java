package mate.academy.internet.shop.dao;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internet.shop.model.Item;

public class Storage {
    public static List<Item> items = new ArrayList<>();
    private static Long itemId = 0L;
    private static Long userId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;

    public static void addItem(Item item) {
        item.setId(++itemId);
        items.add(item);
    }
}
