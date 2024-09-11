package pattern;

public class InitState implements State {

    @Override
    public void handle(SwitchContext switchContext, int input) {
        if (input == 1) {
            switchContext.setState(new InsertOrderState());
        } else if (input == 2) {
            switchContext.setState(new InquiriesState());
        } else if(input == 3) {
            switchContext.setState(new ModifyState());
        } else if(input == 4) {

        } else {
            switchContext.setState(new EndState());
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("""
                    1. 발주하기
                    2. 조회하기
                    3. 수정하기
                    4. 삭제하기
                    5. 종료
                    """);
    }
}
