package mate.academy.internet.shop;

import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        productService.create(new Product("product", 42.0));
        productService.create(new Product("product1", 452.0));
        productService.create(new Product("product2", 66.34));
        productService.create(new Product("product3", 4.50));

        productService.getAll().forEach(it -> System.out.println(it.toString()));

        Product product1 = productService.get(2L);
        System.out.println(product1.toString());

        productService.delete(2L);
        productService.update(productService.get(3L));

        productService.getAll().forEach(it -> System.out.println(it.toString()));
    }
}
