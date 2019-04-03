/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.dao;

import java.util.List;
import springmvc.models.Product;

/**
 *
 * @author jnap
 */
public interface ProductDao {

    Product findById(long id);

    void saveProduct(Product product);

    void saveOrUpdate(Product product);

    void deleteProductById(long id);

    List<Product> findAllProducts();

}
