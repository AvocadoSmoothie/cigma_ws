package ma.cigma.ws.tprest.controller;

import ma.cigma.ws.tprest.model.Product;
import ma.cigma.ws.tprest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    /**
     * @Autowired permet d'injecter le bean de type IProductService
     * (objet représentant la couche métier).
     * Ici, le Design Pattern qui est appliqué est l'IOC (Inversion Of Control).
     */
    @Autowired
    private IProductService service;

    /**
     * Pour chercher tous les produits
     */
    @GetMapping(value = "/products", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getAll() {
        return service.getAll();
    }

    /**
     * Pour chercher un produit par son id
     */
    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return service.getById(productId);
    }

    /**
     * Pour créer un nouveau produit
     */
    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        service.create(product);
        return new ResponseEntity<>("Product is created successfully",
                HttpStatus.CREATED);
    }

    /**
     * Pour modifier un produit par son id
     */
    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long
                                                        productId,
                                                @RequestBody Product product) {
        Product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.update(productId, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    /**
     * Pour supprimer un produit par son id
     */
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long
                                                        productId) {
        Product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.delete(productId);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    public IProductService getService() {
        return service;
    }

    public void setService(IProductService service) {
        this.service = service;
    }
}

