package pattern.insertPurchaseProduct;

import domain.OrderItemDto;
import lombok.Getter;
import lombok.Setter;
import pattern.State;
import pattern.SwitchContext;
import pattern.insertPurchaseProduct.ConfirmOrderState;
import pattern.insertPurchaseProduct.SelectManufacturerState;
import pattern.insertPurchaseProduct.SelectProductState;
import pattern.insertPurchaseProduct.SelectWholesaler;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InsertOrderState implements State {
    private int w_id; // 도매직원ID
    private int m_id; // 생산업체직원ID
    private List<OrderItemDto> orderItemDtos = new ArrayList<>(); // 제품 선택 및 수량

    @Override
    public void handle(SwitchContext context, int input) {
        if (input == 1) {
            context.setState(new SelectWholesaler(this));
        } else if (input == 2) {
            context.setState(new SelectManufacturerState(this));
        } else if (input == 3) {
            context.setState(new SelectProductState(this));
        } else if (input == 4){
            if(!checkAllProcess()) {
                System.out.println("""
                        모든 프로세스가 완료되지 않았습니다.
                        단계별로 발주 주문을 해주시기 바랍니다.
                        
                        """);
                return;
            }
            context.setState(new ConfirmOrderState(this));
        } else if (input == 5) {
            context.goBack();
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("발주 주문을 시작합니다");
        System.out.println("1. 도매 직원 선택 " + (w_id != 0 ? "(완료: 선택된 도매 직원 ID : " + w_id + ")" : ""));
        System.out.println("2. 생산업체 직원 선택 " + (m_id != 0 ? "(완료: 선택된 생산업체 직원 ID : " + m_id + ")" : ""));
        System.out.println("3. 제품 및 수량 선택 " + (!orderItemDtos.isEmpty() ? "(완료: 주문대기 건 수 : " + orderItemDtos.size() + ")" : ""));
        System.out.println("4. (1,2,3) 선택 완료 발주 주문하기");
        System.out.println("5. 뒤로가기");
    }

    public void addOrderItemDtos(OrderItemDto item) {
        this.orderItemDtos.add(item);
    }

    private boolean checkAllProcess() {
        if(w_id == 0 || m_id == 0 || orderItemDtos.isEmpty()) return false;
        return true;
    }

}
