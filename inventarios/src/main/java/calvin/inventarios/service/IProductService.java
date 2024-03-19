package calvin.inventarios.service;
import calvin.inventarios.model.Product;
import java.util.List;

public interface IProductService
{
    public List<Product> getAllProducts();
    public Product getProductById(Long idProduct);
    public Product saveProduct(Product product);
    public void deleteProductById(Long idProduct);
}
