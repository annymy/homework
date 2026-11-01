package helpers;

import check.CheckHandler;
import model.Product;

import java.util.List;

public class SpyHandler extends CheckHandler {
    String name;
    List<String> log;

    public SpyHandler(String name, List<String> log) {
        this.name = name;
        this.log = log;
    }

    @Override
    protected boolean status(List<Product> products) {
        log.add(name);
        return true;
    }
}
