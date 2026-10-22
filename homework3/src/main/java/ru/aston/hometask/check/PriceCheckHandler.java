package ru.aston.hometask.check;

import ru.aston.hometask.model.Product;

import java.util.List;

public class PriceCheckHandler extends CheckHandler{
    @Override
    protected boolean status(List<Product> products) {
        for (Product product : products) {
            if (product.getPrice() <= 0) {
                System.out.println("Добавлен товар с некорректной ценой");
                return false;
            }
        }
        return true;
    }
}
