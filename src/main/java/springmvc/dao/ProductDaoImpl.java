package springmvc.dao;

import java.util.List;
import springmvc.models.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jnap
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

    @Override
    public Product findById(long id) {
        return getByKey(id);
    }

    @Override
    public void saveProduct(Product product) {
        persist(product);
    }

    @Override
    public void saveOrUpdate(Product product) {
        super.saveOrUpdate(product);
    }

    @Override
    public void deleteProductById(long id) {
        Query query = getSession().createSQLQuery("delete from product where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findAllProducts() {
        Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list();
    }

}
