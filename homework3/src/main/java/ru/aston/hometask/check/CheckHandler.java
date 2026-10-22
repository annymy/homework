package ru.aston.hometask.check;

import ru.aston.hometask.model.Product;

import java.util.List;

//реализация цепочки событий
public abstract class CheckHandler {
    private CheckHandler next;

    public void setNext(CheckHandler next) {
        this.next = next;
    }

    public boolean handle(List<Product> products){
        if(products == null){
            throw new IllegalArgumentException("Список товаров не может быть null");
        }
        if(!status(products)) return false;
        return next == null || next.handle(products);
    }

    protected abstract boolean status(List<Product> products);
}
