package pattern.insertPurchaseProduct;

import domain.OrderItemDto;
import pattern.InsertOrderState;
import pattern.State;
import pattern.SwitchContext;
import repository.ProductRepository;

import static pattern.SwitchContext.SCANNER;

public class SelectProductState implements State {
    private InsertOrderState parentState;

    public SelectProductState(InsertOrderState parentState) {
        this.parentState = parentState;
    }

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == -1) {
            context.goBack();
            return;
        }

        System.out.println("수량을 입력하세요: ");
        int quantity = SCANNER.nextInt();

        parentState.addOrderItemDtos(new OrderItemDto(input, quantity));

        System.out.println("1. 제품 추가 계속하기");
        System.out.println("2. 주문 완료하기");
        int choice = SCANNER.nextInt();
        if (choice == 2) {
            context.setState(parentState);
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("제품 목록:");
        ProductRepository productRepository = new ProductRepository();
        productRepository.selectProduct();
        System.out.println("제품 ID를 선택하세요 (-1 입력시 뒤로가기):");
    }
}
