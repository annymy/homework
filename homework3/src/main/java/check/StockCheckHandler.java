package check;

import model.Product;

import java.util.List;

public class StockCheckHandler extends CheckHandler{
    @Override
    protected boolean status(List<Product> products) {
        for (Product product:products){
            if(!product.isInStock()){
                System.out.println("Товара нет на складе");
                return false;
            }
        }
        return true;
    }
}
