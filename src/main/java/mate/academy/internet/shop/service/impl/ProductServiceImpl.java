package mate.academy.internet.shop.service.impl;

import java.util.List;
import mate.academy.internet.shop.dao.ProductDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) throws DataProcessingException {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) throws DataProcessingException {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> getAll() throws DataProcessingException {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) throws DataProcessingException {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) throws DataProcessingException {
        return productDao.delete(id);
    }
}
