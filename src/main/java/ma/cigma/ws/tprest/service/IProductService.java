package ma.cigma.ws.tprest.service;

import ma.cigma.ws.tprest.model.Product;

import java.util.List;

public interface IProductService {
    Product getById(Long id);

    List<Product> getAll();

    void create(Product product);

    void update(Long id, Product product);

    void delete(Long id);
}