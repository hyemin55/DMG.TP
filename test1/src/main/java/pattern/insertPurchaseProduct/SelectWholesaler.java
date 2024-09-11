package pattern.insertPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.WholesalerRepository;

public class SelectWholesaler implements State {

    private InsertOrderState parentState;

    public SelectWholesaler(InsertOrderState parentState) {
        this.parentState = parentState;
    }

    @Override
    public void handle(SwitchContext context, int input) {
        parentState.setW_id(input);
        context.setState(parentState); // 위로 돌아가기
    }

    @Override
    public void displayOptions() {
        System.out.println("직원 목록: ");
        WholesalerRepository wholesalerRepository = new WholesalerRepository();
        wholesalerRepository.selectWholesaler();
        System.out.println("직원 ID를 선택하세요: ");
    }
}
