package springmvc.service;

import java.util.List;
import javax.transaction.Transactional;
import springmvc.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.dao.ProductDao;


@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao dao;
    
    @Override
    public Product findById (long id) {
        return dao.findById(id);
    }
    
    @Override
    public void saveProduct (Product product) {
        dao.saveProduct(product);
    }
    
    @Override
    public void saveOrUpdate (Product product) {
        Product entity = dao.findById(product.getId());
        if(entity != null){
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
            //dao.saveOrUpdate(product);
        }
    }
    
    @Override
    public void deleteProductById (long id) {
        dao.deleteProductById(id);
    }
    
    @Override
    public List<Product> findAllProducts() {
        return dao.findAllProducts();
    }
}
