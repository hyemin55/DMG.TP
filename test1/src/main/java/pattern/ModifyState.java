package pattern;

import pattern.modifyPurchaseProduct.InsertManufacturer;
import pattern.modifyPurchaseProduct.ModifyPurchaseProductAmount;

public class ModifyState implements State {

    @Override
    public void handle(SwitchContext context, int input) {
        switch (input) {
            case 1:
                context.setState(new ModifyPurchaseProductAmount());
                break;
            case 2:
                context.setState(new InsertManufacturer());
                break;
            case 3:
                context.goBack();
                break;
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("""
                                1. 발주수량 수정
                                2. 업체등록
                                3. 뒤로가기
                                """);
    }
}
