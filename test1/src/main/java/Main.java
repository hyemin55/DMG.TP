import domain.Product;
import domain.PurchaseProduct;
import domain.PurchaseProductJoinQuery;
import domain.Wholesaler;
import pattern.SwitchContext;
import repository.ProductRepository;
import repository.PurchaseProductRepository;
import repository.WholesalerRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SwitchContext switchContext = new SwitchContext();
        switchContext.run();
    }
}
