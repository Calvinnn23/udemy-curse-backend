package calvin.inventarios.repository;

import calvin.inventarios.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long>
{

}
