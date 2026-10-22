package ru.aston.hometask.check;

import ru.aston.hometask.model.Product;

import java.util.List;

public class ProductCheckHandler extends CheckHandler{
    @Override
    protected boolean status(List<Product> products) {
        for (Product product: products){
            String name = product.getName();
            if(name.isBlank()){
                System.out.println("Один или несколько товаров введены некорректно");
                return false;
            }
        }
        return true;
    }
}
