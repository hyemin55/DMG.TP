package pattern;

import java.util.Scanner;
import java.util.Stack;

public class SwitchContext {
    static final Scanner SCANNER = new Scanner(System.in);
//    private State state;
    private Stack<State> stateStack = new Stack<>();
    private boolean isRunning = true;

    public SwitchContext() {
//        this.state = new InitState();
        this.stateStack.push(new InitState());
    }

    public void setState(State state) {
//        this.state = state;
        this.stateStack.push(state);

        if (state instanceof EndState) {
            state.displayOptions();
            isRunning = false;
        }
    }

    public void goBack() {
        if (stateStack.size() > 1) {
            stateStack.pop(); // 현재 상태 제거
        }
    }

    public State getCurrentState() {
        return stateStack.peek();
    }

    public boolean isRunning() {
        return isRunning;
    }
    public void handleInput(int input) {
//        state.handle(this, input);
        getCurrentState().handle(this, input);
    }

    public void run() {
        while (isRunning()) {
//            state.displayOptions();
            getCurrentState().displayOptions();
            handleInput(SCANNER.nextInt());
        }
        SCANNER.close();
    }

}
