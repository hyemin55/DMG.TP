package pattern.insertPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.PurchaseProductRepository;

import static pattern.SwitchContext.SCANNER;

public class PurchaseProductStdMonthState implements State {

    private String year;
    private String month;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext switchContext, int input) {
        if (!inputReceived) {
            System.out.println("먼저 연도와 월을 입력해주세요.");
            displayOptions();
            return;
        }

        switch (input) {
            case 1:
                PurchaseProductRepository purchaseProductRepository = new PurchaseProductRepository(year, month);
                purchaseProductRepository.selectPurchaseProduct();
                break;
            case 2:
                switchContext.goBack(); // 뒤로 가기
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    @Override
    public void displayOptions() {
        if (!inputReceived) {
            System.out.println("조회하실 연도를 입력해주세요");
            this.year = SCANNER.next();
            System.out.println("조회하실 월을 입력해주세요");
            this.month = SCANNER.next();
            inputReceived = true;
        }

        System.out.println("1. 조회하기");
        System.out.println("2. 뒤로 가기");
    }
}
