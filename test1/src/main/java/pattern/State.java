package pattern;

public interface State {
    void handle(SwitchContext context, int input); // 각 상태를 바꾸기 위한 인터페이스
    void displayOptions(); // System.out.println 용도
}
