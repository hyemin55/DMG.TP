package pattern;

public class EndState implements State {
    @Override
    public void handle(SwitchContext switchContext, int input) {

    }

    @Override
    public void displayOptions() {
        System.out.println("종료되었습니다. 다음에 또 이용해주세요:)");
    }
}
