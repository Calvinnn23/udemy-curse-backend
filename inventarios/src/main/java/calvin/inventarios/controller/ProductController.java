package calvin.inventarios.controller;

import calvin.inventarios.exception.ResourceNotFindedException;
import calvin.inventarios.model.Product;
import calvin.inventarios.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//  http://localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    //http://localhost:8080/inventario-app/products
    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        List<Product> products;
        products = this.productService.getAllProducts();
        logger.info("Finded Products");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        logger.info("Product to add: "+ product);
        return this.productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Product product = this.productService.getProductById(id);
        if(product != null)
        {
            return ResponseEntity.ok(product);
        }
        else
        {
            throw new ResourceNotFindedException("Id: " + id + "not founded");
        }
    }
}
