package pattern.insertPurchaseProduct;

import domain.OrderItemDto;
import pattern.InitState;
import pattern.InsertOrderState;
import pattern.State;
import pattern.SwitchContext;
import repository.OrderRepository;

public class ConfirmOrderState implements State {
    private InsertOrderState parentState;

    public ConfirmOrderState(InsertOrderState parentState) {
        this.parentState = parentState;
    }

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == 1) {
            OrderRepository orderRepository = new OrderRepository();
            boolean isCompleted = orderRepository.insertOrder(parentState.getW_id(),
                    parentState.getM_id(),
                    parentState.getOrderItemDtos());
            if(isCompleted) {
                System.out.println("주문이 정상적으로 입력되었습니다.");
            } else {
                System.out.println("주문 시 오류가 발생하였습니다.");
            }
        } else {
            System.out.println("주문이 취소되었습니다.");
        }
        context.setState(new InitState()); // 처음으로
    }

    @Override
    public void displayOptions() {
        System.out.println("주문 내역을 확인하세요:");
        System.out.println("도매 직원 ID: " + parentState.getW_id());
        System.out.println("생산업체 직원 ID: " + parentState.getM_id());
        for (OrderItemDto itemDto : parentState.getOrderItemDtos())
            System.out.println("제품 ID: " + itemDto.getP_id() + " 수량: " + itemDto.getP_amount());
        System.out.println("1. 주문 확정");
        System.out.println("2. 주문 취소");
    }
}
