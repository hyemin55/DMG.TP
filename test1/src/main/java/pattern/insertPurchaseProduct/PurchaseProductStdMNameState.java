package pattern.insertPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.ManufacturerRepository;
import repository.PurchaseProductRepository4;

import static pattern.SwitchContext.SCANNER;

public class PurchaseProductStdMNameState implements State {

    private String m_name;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == 1) {
            this.m_name = SCANNER.next();
            PurchaseProductRepository4 purchaseProductRepository4 = new PurchaseProductRepository4(m_name);
            purchaseProductRepository4.selectPurchaseProduct4();
        } else if (input == 2) {
            context.goBack();
        }
    }

    @Override
    public void displayOptions() {
        if (!inputReceived) {
            ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
            manufacturerRepository.selectManufacturer();
            inputReceived = true;
        }
        System.out.println("1. 공장직원명를 입력");
        System.out.println("2. 뒤로가기");
    }
}
