package pattern;

import domain.Wholesaler;
import repository.ProductRepository;
import repository.WholesalerRepository;


public class InquiriesState implements State {
    @Override
    public void handle(SwitchContext switchContext, int input) {
        if (input == 1) {
            ProductRepository productRepository = new ProductRepository();
            productRepository.selectProduct();
        } else if (input == 2) {
            switchContext.setState(new PurchaseProductInquiresState());
        } else if (input == 3) {
            WholesalerRepository wholesalerRepository = new WholesalerRepository();
            wholesalerRepository.selectWholesaler();
        }else if (input == 5) {
            switchContext.goBack();
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("""
                            1. 상품조회
                            2. 발주조회
                            3. 직원조회
                            4. 업체조회
                            5. 뒤로가기
                            """);
    }
}
