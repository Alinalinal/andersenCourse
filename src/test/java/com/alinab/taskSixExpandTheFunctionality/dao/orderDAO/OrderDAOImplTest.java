package com.alinab.taskSixExpandTheFunctionality.dao.orderDAO;

import com.alinab.taskSixExpandTheFunctionality.model.order.Order;
import com.alinab.taskSixExpandTheFunctionality.model.product.Food;
import com.alinab.taskSixExpandTheFunctionality.model.product.NotFood;
import com.alinab.taskSixExpandTheFunctionality.model.product.Product;
import com.alinab.taskSixExpandTheFunctionality.model.product.currency.CurrencyCode;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderDAOImplTest {

    private final Order order = new Order(12345);
    private final OrderDAO orderDAO = new OrderDAOImpl(order);

    private static final int UUID_1 = 1;
    private static final int UUID_2 = 2;
    private static final int UUID_4_NEW = 4;
    private static final int UUID_5 = 5;
    private static final int UUID_NOT_EXIST = 0;

    private static final Product PRODUCT_1;
    private static final Product PRODUCT_2;
    private static final Product PRODUCT_4_NEW;
    private static final Product PRODUCT_5;

    private static final int SET_UP_BUCKET_SIZE = 8;
    private static final int FOOD_SHELF_LIFE = 5;
    private static final int NOT_FOOD_SHELF_LIFE = 365;

    static {
        PRODUCT_1 = new Food(UUID_1, "Cucumber 1kg", CurrencyCode.UAH, BigDecimal.valueOf(65.99),
                2023, 2, 14);
        PRODUCT_1.setShelfLifeDays(FOOD_SHELF_LIFE);

        PRODUCT_2 = new Food(UUID_2, "Chicken 1kg", CurrencyCode.UAH, BigDecimal.valueOf(112.47),
                2023, 2, 14);
        PRODUCT_2.setShelfLifeDays(FOOD_SHELF_LIFE);

        PRODUCT_4_NEW = new Food(UUID_4_NEW, "Milk 900ml", CurrencyCode.GEL, BigDecimal.valueOf(2.4),
                2023, 2, 14);
        PRODUCT_4_NEW.setShelfLifeDays(FOOD_SHELF_LIFE);

        PRODUCT_5 = new NotFood(UUID_5, "Laptop", CurrencyCode.USD, BigDecimal.valueOf(2499.0),
                2022, 12, 17);
        PRODUCT_5.setShelfLifeDays(NOT_FOOD_SHELF_LIFE);
    }

    @Before
    public void setUp() {
        orderDAO.addProduct(UUID_1, 2);
        orderDAO.addProduct(UUID_2, 5);
        orderDAO.addProduct(UUID_5, 1);
    }

    @Test
    public void addExistInProdListProductToOrdersBucket() {
        assertTrue(orderDAO.addProduct(UUID_4_NEW, 20));
        assertEquals(SET_UP_BUCKET_SIZE + 20, orderDAO.bucketSize());
        assertFalse(orderDAO.addProduct(UUID_4_NEW, 20));
        assertEquals(SET_UP_BUCKET_SIZE + 20, orderDAO.bucketSize());
        assertEquals(orderDAO.getByUuid(UUID_4_NEW), PRODUCT_4_NEW);
    }

    @Test
    public void addNotExistInProdListProductToOrdersBucket() {
        assertFalse(orderDAO.addProduct(UUID_NOT_EXIST, 1));
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
    }

    @Test
    public void addMoreThanExistInProdListProductsToOrdersBucket() {
        assertFalse(orderDAO.addProduct(UUID_1, 150));
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
    }

    @Test
    public void deleteExistInProdListProductFromOrdersBucket() {
        assertTrue(orderDAO.deleteProduct(UUID_1, 1));
        assertEquals(PRODUCT_1, orderDAO.getByUuid(UUID_1));
        assertEquals(SET_UP_BUCKET_SIZE - 1, orderDAO.bucketSize());
        assertTrue(orderDAO.deleteProduct(UUID_1, 1));
        assertNull(orderDAO.getByUuid(UUID_1));
        assertEquals(SET_UP_BUCKET_SIZE - 2, orderDAO.bucketSize());
        assertFalse(orderDAO.deleteProduct(UUID_1, 1));
        assertEquals(SET_UP_BUCKET_SIZE - 2, orderDAO.bucketSize());
    }

    @Test
    public void deleteMoreThanExistProductsFromOrdersBucket() {
        assertFalse(orderDAO.deleteProduct(UUID_1, 100));
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
    }

    @Test
    public void deleteNotExistInProdListProductFromOrdersBucket() {
        assertFalse(orderDAO.deleteProduct(UUID_NOT_EXIST, 1));
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
    }

    @Test
    public void getAllProductsFromOrdersBucket() {
        Map<Product, Integer> bucket1 = orderDAO.getAllProducts();
        int bucket1Size = 0;

        for (Map.Entry<Product, Integer> pair : bucket1.entrySet()) {
            bucket1Size += pair.getValue();
        }

        assertEquals(SET_UP_BUCKET_SIZE, bucket1Size);
        Map<Product, Integer> bucket2 = new HashMap<>();
        bucket2.put(PRODUCT_1, 2);
        bucket2.put(PRODUCT_2, 5);
        bucket2.put(PRODUCT_5, 1);
        assertEquals(bucket1, bucket2);
    }

    @Test
    public void clearOrdersBucket() {
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
        orderDAO.clear();
        assertEquals(0, orderDAO.bucketSize());
    }

    @Test
    public void getTotalOfProductsInOrdersBucket() {
        assertEquals(SET_UP_BUCKET_SIZE, orderDAO.bucketSize());
        assertTrue(orderDAO.addProduct(UUID_4_NEW, 15));
        assertEquals(SET_UP_BUCKET_SIZE + 15, orderDAO.bucketSize());
        assertTrue(orderDAO.deleteProduct(UUID_1, 1));
        assertEquals(SET_UP_BUCKET_SIZE + 15 - 1, orderDAO.bucketSize());
        orderDAO.clear();
        assertEquals(0, orderDAO.bucketSize());
    }
}