package pattern.insertPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.PurchaseProductRepository3;
import repository.WholesalerRepository;

import static pattern.SwitchContext.SCANNER;

public class PurchaseProductStdWNameState implements State {

    private String w_name;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == 1) {
            this.w_name = SCANNER.next();
            PurchaseProductRepository3 purchaseProductRepository3 = new PurchaseProductRepository3(w_name);
            purchaseProductRepository3.selectPurchaseProduct3();
        } else if (input == 2) {
            context.goBack();
        }
    }

    @Override
    public void displayOptions() {
        if (!inputReceived) {
            WholesalerRepository wholesalerRepository = new WholesalerRepository();
            wholesalerRepository.selectWholesaler();
            inputReceived = true;
        }
        System.out.println("1. 직원명를 입력");
        System.out.println("2. 뒤로가기");
    }
}
