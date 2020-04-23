package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internet.shop.dao.ProductDao;
import mate.academy.internet.shop.dao.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(x -> product.getId().equals(Storage.products.get(x).getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product product = get(id).orElseThrow(
                () -> new NoSuchElementException("Can't find product with such id " + id));
        Storage.products.remove(product);
        return true;
    }
}
