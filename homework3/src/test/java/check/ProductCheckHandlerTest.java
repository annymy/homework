package check;

import check.CheckChainBuilder;
import check.CheckHandler;
import check.ProductCheckHandler;
import model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCheckHandlerTest {
    @Test
    void whenEmptyList_thenReturnTrue(){
        CheckHandler check = new CheckChainBuilder()
                .addHandler(new ProductCheckHandler())
                .build();

        assertTrue(check.handle(List.of()));
    }

    @Test
    void whenProductsNull_thenThrowIllegalArgumentException(){
        CheckHandler check = new CheckChainBuilder()
                .addHandler(new ProductCheckHandler())
                .build();

        assertThrows(IllegalArgumentException.class,
                ()->check.handle(null));
    }

    @Test
    void whenNamesCorrect_thenReturnTrue(){
        Product product1 = new Product("Product1", 200, true);
        Product product2 = new Product("Product2", 200, true);
        Product product3 = new Product("Product3", 200, true);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new ProductCheckHandler())
                .build();

        assertTrue(check.handle(List.of(product1, product2, product3)));
    }

    @Test
    void whenNameEmpty_thenReturnFalse(){
        Product product1 = new Product("", 200, true);
        Product product2 = new Product("Product2", 200, true);
        Product product3 = new Product("Product3", 200, true);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new ProductCheckHandler())
                .build();

        assertFalse(check.handle(List.of(product1, product2, product3)));
    }

    @Test
    void whenNameEmptySymbol_thenReturnFalse(){
        Product product1 = new Product(" ", 200, true);
        Product product2 = new Product("Product2", 200, true);
        Product product3 = new Product("Product3", 200, true);

        CheckHandler check = new CheckChainBuilder()
                .addHandler(new ProductCheckHandler())
                .build();

        assertFalse(check.handle(List.of(product1, product2, product3)));
    }
}
