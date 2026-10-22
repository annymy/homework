package ru.aston.hometask.check;

import ru.aston.hometask.helpers.SpyHandler;
import ru.aston.hometask.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckHandlerTest {
    @Test
    void whenBuildWithoutHandler_thenIllegalStateException(){
        assertThrows(IllegalStateException.class,
                ()-> new CheckChainBuilder()
                        .build());
    }

    @Test
    void whenBuildWithOneHandler_thenReturnThisHandler(){
        CheckHandler only = new StockCheckHandler();
        CheckHandler head = new CheckChainBuilder()
                .addHandler(only)
                .build();

        assertSame(only, head);
    }

    @Test
    void whenBuildWithAnyHandlers_thenReturnFirst(){
        CheckHandler first = new PriceCheckHandler();
        CheckHandler second = new ProductCheckHandler();
        CheckHandler third = new StockCheckHandler();

        CheckHandler chain = new CheckChainBuilder()
                .addHandler(first)
                .addHandler(second)
                .addHandler(third)
                .build();

        assertSame(first, chain);
    }

    @Test
    void whenBuildChain_thenHandlersLineUpInTheCorrectOrder(){
        List<String> log = new ArrayList<>();

        CheckHandler chain = new CheckChainBuilder()
                .addHandler(new SpyHandler("A", log))
                .addHandler(new SpyHandler("B", log))
                .addHandler(new SpyHandler("C", log))
                .build();

        chain.handle(List.of(new Product("name", 200, true)));

        assertEquals(List.of("A", "B", "C"), log);
    }
}
