package calvin.inventarios.service;

import calvin.inventarios.model.Product;
import calvin.inventarios.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService implements IProductService
{
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long idProduct) {
        Product product;
        product = this.productRepository.findById(idProduct).orElse(null);
        return product;
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long idProduct) {
        this.productRepository.deleteById(idProduct);
    }
}
