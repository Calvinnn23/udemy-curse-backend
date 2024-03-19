package calvin.inventarios.controller;

import calvin.inventarios.exception.ResourceNotFindedException;
import calvin.inventarios.model.Product;
import calvin.inventarios.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new ResourceNotFindedException("Id: " + id + "not found");
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product productRecived){
        Product product = this.productService.getProductById(id);
        if(product == null){
            throw new ResourceNotFindedException("Id: " + id + "not found");
        }
        product.setDescription(productRecived.getDescription());
        product.setPrice(productRecived.getPrice());
        product.setQuantity(productRecived.getQuantity());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable long id){
        Product product = productService.getProductById(id);
        if(product == null){
            throw new ResourceNotFindedException("Id: " + id + "not found");
        }
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> ans = new HashMap<>();
        ans.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(ans);
    }
}
