package mate.academy.internet.shop;

import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.ItemService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internet.shop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        itemService.create(new Item("item", 42.0));
        itemService.create(new Item("item1", 452.0));
        itemService.create(new Item("item2", 66.34));
        itemService.create(new Item("item3", 4.50));

        itemService.getAll().forEach(it -> System.out.println(it.toString()));

        Item item1 = itemService.get(2L);
        System.out.println(item1.toString());

        itemService.delete(2L);
        itemService.update(itemService.get(3L));

        itemService.getAll().forEach(it -> System.out.println(it.toString()));
    }
}
