package pattern;

import pattern.inquiries.InquiriesState;
import pattern.insertPurchaseProduct.InsertOrderState;

public class InitState implements State {

    @Override
    public void handle(SwitchContext switchContext, int input) {
        if (input == 1) {
            switchContext.setState(new InsertOrderState());
        } else if (input == 2) {
            switchContext.setState(new InquiriesState());
        } else {
            switchContext.setState(new EndState());
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("""
                    1. 발주하기
                    2. 조회하기
                    3. 종료
                    """);
    }
}
