package lz.service;

import lz.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public void save(Product product);

    Product findProductByid(String id);

    void update(Product product);

    void delete(String id);
}
