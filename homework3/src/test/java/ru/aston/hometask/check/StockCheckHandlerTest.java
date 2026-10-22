package ru.aston.hometask.check;

import ru.aston.hometask.model.Product;
import org.junit.jupiter.api.Test;
import ru.aston.hometask.check.CheckChainBuilder;
import ru.aston.hometask.check.CheckHandler;
import ru.aston.hometask.check.StockCheckHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StockCheckHandlerTest {
    @Test
    void whenAllProductsInStock_thenReturnTrue(){
        Product product1 = new Product("Product1", 200, true);
        Product product2 = new Product("Product2", 200, true);
        Product product3 = new Product("Product3", 200, true);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new StockCheckHandler())
                .build();

        assertTrue(check.handle(List.of(product1, product2, product3)));
    }

    @Test
    void whenOneProductNotInStock_thenReturnFalse(){
        Product product1 = new Product("Product1", 200, true);
        Product product2 = new Product("Product2", 200, true);
        Product product3 = new Product("Product3", 200, false);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new StockCheckHandler())
                .build();

        assertFalse(check.handle(List.of(product1, product2, product3)));
    }

    @Test
    void whenEmptyList_thenReturnTrue(){
        CheckHandler check = new CheckChainBuilder()
                .addHandler(new StockCheckHandler())
                .build();

        assertTrue(check.handle(List.of()));
    }

    @Test
    void whenProductsNull_thenThrowIllegalArgumentException(){
        CheckHandler check = new CheckChainBuilder()
                .addHandler(new StockCheckHandler())
                .build();

        assertThrows(IllegalArgumentException.class,
                ()->check.handle(null));
    }
}
