package com.alinab.taskFiveStructure.dao.orderDAO;

import com.alinab.taskFiveStructure.dao.productDAO.ProductListDAO;
import com.alinab.taskFiveStructure.model.order.Order;
import com.alinab.taskFiveStructure.model.product.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j
public class OrderListDAO implements OrderDAO {

    Order order;
    List<Product> bucket;

    ProductListDAO productListDAO = new ProductListDAO();

    public OrderListDAO() {
        this.order = new Order();
        this.bucket = order.getBucket();
    }

    @Override
    public boolean addProduct(int uuid) {
        log.info("Add a product with ID " + uuid + " to the order's bucket");
        Product product = productListDAO.get(uuid);
        if (product != null) {
            return bucket.add(product);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int uuid) {
        log.info("Delete a product with ID " + uuid + " from the order's bucket");
        Product product = getByUuid(uuid);
        return bucket.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Get all products from the order's bucket");
        return bucket;
    }

    @Override
    public void clear() {
        log.info("Clear the order's bucket");
        bucket.clear();
    }

    @Override
    public int bucketSize() {
        log.info("Get size of the order's bucket");
        return bucket.size();
    }

    public Product getByUuid(int uuid) {
        for (Product product : bucket) {
            if (product.getUuid() == uuid) {
                return product;
            }
        }
        return null;
    }
}
