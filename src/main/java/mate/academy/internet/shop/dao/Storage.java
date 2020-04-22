package mate.academy.internet.shop.dao;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public class Storage {
    public static List<Item> items = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Bucket> buckets = new ArrayList<>();
    private static Long itemId = 0L;
    private static Long userId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;

    public static void addItem(Item item) {
        item.setId(++itemId);
        items.add(item);
    }

    public static void addUser(User user) {
        user.setId(++userId);
        users.add(user);
    }

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addBucket(Bucket bucket) {
        bucket.setId(++bucketId);
        buckets.add(bucket);
    }
}
