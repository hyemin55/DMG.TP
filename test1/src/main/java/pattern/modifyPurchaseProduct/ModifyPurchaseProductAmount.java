package pattern.modifyPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.PurchaseProductRepository;
import repository.UpdatePurchaseProduct;

import java.time.LocalDate;

import static pattern.SwitchContext.SCANNER;

public class ModifyPurchaseProductAmount implements State {

    private String year;
    private String month;

    private int updateppid;
    private int updateppreceivedCount;
    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {


        switch (input) {
            case 1:
                UpdatePurchaseProduct updatePurchaseProduct = new UpdatePurchaseProduct(updateppid, updateppreceivedCount);
                updatePurchaseProduct.updatePurchaseProduct();
                break;
            case 2:
                context.goBack();
                break;
        }
    }

    @Override
    public void displayOptions() {
        if (!inputReceived) {
            LocalDate today = LocalDate.now();
            this.year = String.valueOf(today.getYear());
            this.month = String.valueOf(today.getMonthValue());
            checkLessThanOctober();
            PurchaseProductRepository purchaseProductRepository = new PurchaseProductRepository(year, month);
            purchaseProductRepository.selectPurchaseProduct();

            System.out.println("수정할 발주 번호(pp_id)를 입력해주세요.");
            this.updateppid = SCANNER.nextInt();
            System.out.println("수량을 입력해주세요.");
            this.updateppreceivedCount = SCANNER.nextInt();

            inputReceived = true;
        }

        System.out.println("1. 수정하기");
        System.out.println("2. 뒤로가기");

    }

    private void checkLessThanOctober() {
        if (Integer.parseInt(month) < 10)
            month = "0"+ month;
    }
}
