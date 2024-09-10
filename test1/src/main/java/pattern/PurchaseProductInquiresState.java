package pattern;

public class PurchaseProductInquiresState implements State {
    @Override
    public void handle(SwitchContext switchContext, int input) {

        if (input == 1) {
            switchContext.setState(new PurchaseProductStdMonthState());
        } else if (input == 2) {

        } else if (input == 3) {

        } else if (input == 4) {

        } else if (input == 5){
            switchContext.goBack();
        } else {

        }
    }

    @Override
    public void displayOptions() {
        System.out.println("""
                조회하실 기준을 선택해 주세요.
                1. 발주날짜(월단위)
                2. 상품명
                3. 직원명
                4. 공장직원명
                5. 뒤로가기
                """);
    }
}
