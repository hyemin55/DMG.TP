package pattern.inquiries;

import pattern.State;
import pattern.SwitchContext;
import repository.PP_PnameRepository;
import repository.ProductRepository;

import static pattern.SwitchContext.SCANNER;

public class PurchaseProductStdProductNameState implements State {
    private int productId;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == 1) {
            PP_PnameRepository ppPnameRepository = new PP_PnameRepository(productId);
            ppPnameRepository.selectPP_PnameRepository();
        } else {
            context.goBack();
        }
    }

    @Override
    public void displayOptions() {
        if(!inputReceived) {
            ProductRepository productRepository = new ProductRepository();
            productRepository.selectProduct();
            System.out.println("상품 ID를 입력해주세요");
            this.productId = SCANNER.nextInt();
            inputReceived = true;
        }
        System.out.println("1. 상품명으로 조회하기");
        System.out.println("2. 뒤로가기");
    }
}
