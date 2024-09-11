package pattern.deletePurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.DeleteRepository;
import repository.PurchaseProductRepository;

import java.time.LocalDate;

import static pattern.SwitchContext.SCANNER;

public class DeletePurchaseProduct implements State {

    private int pp_id;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {

        DeleteRepository deleteRepository = new DeleteRepository(pp_id);
        deleteRepository.delete();

        if (input == 1) {
            context.goBack();
        }
    }

    @Override
    public void displayOptions() {

        if(!inputReceived) {
            LocalDate now = LocalDate.now();
            String dYear = ""+now.getYear();
            String dMonth = now.getMonthValue()<10? "0"+now.getMonthValue() : ""+now.getMonthValue();
            PurchaseProductRepository vvs = new PurchaseProductRepository(dYear, dMonth);
            vvs.selectPurchaseProduct();
            System.out.println("삭제하실 발주번호(pp_id)을 골라주세요");
            this.pp_id = SCANNER.nextInt();
            inputReceived = true;
        }

        System.out.println("1. 뒤로가기");
    }
}
