package ru.aston.hometask.model;

import org.junit.jupiter.api.Test;
import ru.aston.hometask.model.Product;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    void whenCreateProductWithValidData_thenProductIsCreated(){
        Product product = new Product("Cake", 100, true);

        assertNotEquals(null, product);
        assertEquals("Cake", product.getName());
        assertEquals(100, product.getPrice());
        assertTrue(product.isInStock());
    }

    @Test
    void whenCreateProductWithNullName_thenNullPointerException(){
        assertThrows(NullPointerException.class, ()->new Product(null, 100, true));
    }

    @Test
    void whenSetPrice_thenGetPriceReturnNewValue(){
        Product product = new Product("Cake", 100, true);
        int newPrice = 200;
        product.setPrice(newPrice);

        assertEquals(newPrice, product.getPrice());
    }

    @Test
    void whenSetInStock_thenIsInStockReturnNewValue(){
        Product product = new Product("Cake", 100, true);
        boolean newFlag = false;
        product.setInStock(newFlag);

        assertEquals(newFlag, product.isInStock());
    }
}
