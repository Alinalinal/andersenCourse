package com.alinab.taskFiveStructure.dao.orderDAO;

import com.alinab.taskFiveStructure.model.product.Food;
import com.alinab.taskFiveStructure.model.product.NotFood;
import com.alinab.taskFiveStructure.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderListDAOTest {

    private final OrderListDAO orderListDAO = new OrderListDAO();

    private static final int UUID_1 = 1;
    private static final int UUID_2 = 2;
    private static final int UUID_3_NEW = 3;
    private static final int UUID_4 = 4;
    private static final int UUID_NOT_EXIST = 0;

    private static final Product PRODUCT_1;
    private static final Product PRODUCT_2;
    private static final Product PRODUCT_3_NEW;
    private static final Product PRODUCT_4;

    static {
        PRODUCT_1 = new Food(UUID_1, "Cucumber");
        PRODUCT_2 = new Food(UUID_2, "Chicken");
        PRODUCT_3_NEW = new Food(UUID_3_NEW, "Rice");

        PRODUCT_4 = new NotFood(UUID_4, "Laptop");
    }

    @Before
    public void setUp() {
        orderListDAO.addProduct(UUID_1);
        orderListDAO.addProduct(UUID_2);
        orderListDAO.addProduct(UUID_4);
    }

    @Test
    public void addExistInProdListProductToOrdersBucket() {
        assertTrue(orderListDAO.addProduct(UUID_3_NEW));
        assertEquals(4, orderListDAO.bucketSize());
        assertEquals(orderListDAO.getByUuid(UUID_3_NEW), PRODUCT_3_NEW);
    }

    @Test
    public void addNotExistInProdListProductToOrdersBucket() {
        assertFalse(orderListDAO.addProduct(UUID_NOT_EXIST));
        assertEquals(3, orderListDAO.bucketSize());
    }

    @Test
    public void deleteExistInProdListProductFromOrdersBucket() {
        assertTrue(orderListDAO.deleteProduct(UUID_1));
        assertEquals(2, orderListDAO.bucketSize());
        assertTrue(orderListDAO.deleteProduct(UUID_2));
        assertEquals(1, orderListDAO.bucketSize());
        assertTrue(orderListDAO.deleteProduct(UUID_4));
        assertEquals(0, orderListDAO.bucketSize());
    }

    @Test
    public void deleteNotExistInProdListProductFromOrdersBucket() {
        assertFalse(orderListDAO.deleteProduct(UUID_NOT_EXIST));
        assertEquals(3, orderListDAO.bucketSize());
    }

    @Test
    public void getAllProductsFromOrdersBucket() {
        List<Product> bucket1 = orderListDAO.getAllProducts();
        assertEquals(3, bucket1.size());
        List<Product> bucket2 = new ArrayList<>();
        bucket2.add(PRODUCT_1);
        bucket2.add(PRODUCT_2);
        bucket2.add(PRODUCT_4);
        assertEquals(bucket1, bucket2);
    }

    @Test
    public void clearOrdersBucket() {
        assertEquals(3, orderListDAO.bucketSize());
        orderListDAO.clear();
        assertEquals(0, orderListDAO.bucketSize());
    }

    @Test
    public void getTotalOfProductsInOrdersBucket() {
        assertEquals(3, orderListDAO.bucketSize());
        assertTrue(orderListDAO.addProduct(UUID_3_NEW));
        assertEquals(4, orderListDAO.bucketSize());
        assertTrue(orderListDAO.deleteProduct(UUID_1));
        assertEquals(3, orderListDAO.bucketSize());
        orderListDAO.clear();
        assertEquals(0, orderListDAO.bucketSize());
    }
}