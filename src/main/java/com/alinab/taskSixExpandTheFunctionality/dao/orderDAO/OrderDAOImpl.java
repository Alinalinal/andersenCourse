package com.alinab.taskSixExpandTheFunctionality.dao.orderDAO;

import com.alinab.taskSixExpandTheFunctionality.model.order.Order;
import com.alinab.taskSixExpandTheFunctionality.model.product.Product;
import com.alinab.taskSixExpandTheFunctionality.storage.Warehouse;
import com.alinab.taskSixExpandTheFunctionality.storage.WarehouseImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Log4j
public class OrderDAOImpl implements OrderDAO {

    static final int MIN_QUANTITY = 1;
    static final String SER_PATH = "./src/main/resources/ordersStorage/order.txt";

    final Warehouse warehouse = new WarehouseImpl();

    Order order;
    Map<Product, Integer> bucket;

    public OrderDAOImpl(Order order) {
        this.order = order;
        this.bucket = order.getBucket();
    }

    @Override
    public boolean addProduct(int uuid, int quantity) {
        log.info("Add a product with ID " + uuid + " to the order's bucket");
        Product product = warehouse.getByUuid(uuid);
        if (product != null && warehouse.removeProduct(product, quantity)) {
            if (bucket.containsKey(product)) {
                int oldQuantity = bucket.get(product);
                bucket.put(product, oldQuantity + quantity);
            } else {
                bucket.put(product, quantity);
            }
            order.setBucketSize(order.getBucketSize() + quantity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int uuid, int quantity) {
        log.info("Delete a product with ID " + uuid + " from the order's bucket");
        Product product = getByUuid(uuid);
        if (product != null) {
            int oldQuantity = bucket.get(product);
            if (quantity < MIN_QUANTITY || oldQuantity < quantity) {
                return false;
            } else if (oldQuantity == quantity) {
                bucket.remove(product);
            } else {
                bucket.put(product, oldQuantity - quantity);
            }
            warehouse.addProduct(product, quantity);
            order.setBucketSize(order.getBucketSize() - quantity);
            return true;
        }
        return false;
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        log.info("Get all products from the order's bucket");
        return bucket;
    }

    @Override
    public void clear() {
        log.info("Clear the order's bucket");
        bucket.clear();
        order.setBucketSize(0);
    }

    @Override
    public int bucketSize() {
        log.info("Get size of the order's bucket");
        return order.getBucketSize();
    }

    @Override
    public void saveOrder() {
        log.info("Save order to a file");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SER_PATH))) {
            oos.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadOrder() {
        log.info("Upload order from a file");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SER_PATH))) {
            order = (Order) ois.readObject();
            bucket = order.getBucket();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Product getByUuid(int uuid) {
        for (Product product : bucket.keySet()) {
            if (product.getUuid() == uuid) {
                return product;
            }
        }
        return null;
    }
}
