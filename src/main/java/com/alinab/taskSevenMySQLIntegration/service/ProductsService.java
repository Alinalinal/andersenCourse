package com.alinab.taskSevenMySQLIntegration.service;

import com.alinab.taskSevenMySQLIntegration.models.Product;
import com.alinab.taskSevenMySQLIntegration.models.currency.Currency;
import com.alinab.taskSevenMySQLIntegration.repositories.ProductsRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Log4j
public class ProductsService {

    static int FOOD_SHELF_LIFE = 5;
    static int NOT_FOOD_SHELF_LIFE = 365;

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productsRepository.findById(id);

        return foundProduct.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        log.info("Add a product(s) " + product.getName() + " to the warehouse");

        product.setSellingPrice(calcSellingPrice(product));
        product.setShelfLifeDays(calcShelfLifeDays(product));

        productsRepository.save(product);
    }

    private BigDecimal calcSellingPrice(Product product) {
        Currency currency = new Currency(product.getCurrencyCode());

        return product.getPurchasePrice().multiply(BigDecimal.valueOf(currency.getExchangeRate())
                        .multiply(BigDecimal.valueOf(currency.getMultiplicationConst())))
                .setScale(2, RoundingMode.CEILING);
    }

    private int calcShelfLifeDays(Product product) {

        return product.getIsFood().equals("true") ? FOOD_SHELF_LIFE : NOT_FOOD_SHELF_LIFE;
    }
}
