package mate.academy.internet.shop;

import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.ItemService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internet.shop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        System.out.println("Create Items");
        itemService.create(new Item("item", 42.0));
        itemService.create(new Item("item1", 452.0));
        itemService.create(new Item("item2", 66.34));
        itemService.create(new Item("item3", 4.50));

        System.out.println("All Items: ");
        itemService.getAll().forEach(it -> System.out.println(it.toString()));

        System.out.println("Item by ID: ");
        Item item1 = itemService.get(2L);
        System.out.println(item1.toString());

        System.out.println("Delete Item by ID.");
        itemService.delete(2L);

        System.out.println("Update Item");
        itemService.update(itemService.get(3L));

        System.out.println("Get All Items after delete and update: ");
        itemService.getAll().forEach(it -> System.out.println(it.toString()));
    }
}
