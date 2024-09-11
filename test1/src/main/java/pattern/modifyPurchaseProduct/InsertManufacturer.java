package pattern.modifyPurchaseProduct;

import pattern.State;
import pattern.SwitchContext;
import repository.addmanufacturer;

import static pattern.SwitchContext.SCANNER;

public class InsertManufacturer implements State {

    private boolean inputReceived = false;

    @Override
    public void handle(SwitchContext context, int input) {
        if (!inputReceived) {
            System.out.println("먼저 연도와 월을 입력해주세요.");
            displayOptions();
            return;
        }

        context.goBack();
    }

    @Override
    public void displayOptions() {
        if (!inputReceived) {
            System.out.println("사업자 번호를 입력해주세요.");
            String mBusinessID = SCANNER.next();
            System.out.println("공장 상호명을 입력해주세요.");
            String mName = SCANNER.next();
            System.out.println("공장 주소를 입력해주세요.");
            String mAdress = SCANNER.next();
            System.out.println("담당자 이름을 입력해주세요.");
            String mPerson = SCANNER.next();
            System.out.println("담당자 연락처를 입력해주세요.");
            String mPhone = SCANNER.next();
            System.out.println("담당자 부서를 입력해주세요.(선택사항: 기입하지 않으려면 0을 입력해주세요)");
            String mDepartment = SCANNER.next().equals("0") ? "" : SCANNER.next();
            System.out.println("담당자 직급을 입력해주세요.(선택사항: 기입하지 않으려면 0을 입력해주세요)");
            String mJobTitle = SCANNER.next().equals("0") ? "" : SCANNER.next();
            new addmanufacturer(mBusinessID,mName,mAdress,mPerson,mPhone,mDepartment,mJobTitle);
            inputReceived = true;
        }

        System.out.println("1. 뒤로가기");
    }
}
