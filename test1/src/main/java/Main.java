import com.mysql.cj.x.protobuf.MysqlxCrud;
import repository.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {


        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("""
                    1. 발주하기
                    2. 조회하기
                    3. 수정하기
                    4. 삭제하기
                    5. 종료
                    """);
            int input = scan.nextInt();
            if (input == 5) {
                System.out.println("종료되었습니다. 다음에 또 이용해주세요:)");
                break;
            }

            switch (input) {
                case 1:
//                    OrdersRepository ordersRepository = new OrdersRepository();
                    break;
                case 2:
                    System.out.println("""
                            1. 상품조회
                            2. 발주조회
                            3. 직원조회
                            4. 업체조회
                            """);
                    switch (scan.nextInt()) {
                        case 1:
                            ProductRepository productRepository = new ProductRepository();
                            productRepository.selectproduct();
                            break;
                        case 2:
                            while (true) {
                                boolean isExist = false;
                                System.out.println("""
                                        조회하실 기준을 선택해 주세요.
                                        1. 발주날짜(월단위)
                                        2. 상품명
                                        3. 직원명
                                        4. 공장직원명
                                        5. 뒤로가기
                                        """);

                                switch (scan.nextInt()) {
                                    case 1:
                                        System.out.println("조회하실 연도 입력해주세요");
                                        String year = scan.next();
                                        System.out.println("조회하실 월을 입력해주세요");
                                        String month = scan.next();
                                        PurchaseProductRepository purchaseProductRepository = new PurchaseProductRepository(year, month);
                                        purchaseProductRepository.selectPurchaseProduct();
                                        break;
                                    case 2:
                                    case 3:
                                        System.out.println("조회하실 직원명을 입력해주세요");
                                        String w_name = scan.next();
                                        PurchaseProductRepository3 purchaseProductRepository3 = new PurchaseProductRepository3(w_name);
                                        purchaseProductRepository3.selectPurchaseProduct3();
                                        break;
                                    case 4:
                                        System.out.println("조회하실 공장명을 입력해주세요");
                                        String m_name = scan.next();
                                        PurchaseProductRepository4 purchaseProductRepository4 = new PurchaseProductRepository4(m_name);
                                        purchaseProductRepository4.selectPurchaseProduct4();
                                        break;
                                    case 5:
                                        isExist = true;
                                        break;
                                }

                                if (isExist) break;
                            }
                            break;
                        case 3:
                            WholesalerRepository wholesalerRepository = new WholesalerRepository();
                            wholesalerRepository.selectwholesaler();

                            break;
                        case 4:

                            break;
                        default:
                            System.out.println("원하는 숫자를 입력해주세요.");
                    }
                case 3:
                    while (true) {
                        boolean isExist = false;
                        System.out.println("""
                                1. 발주수량 수정
                                2. 업체등록
                                3. 뒤로가기
                                """);
                        switch (scan.nextInt()) {
                            case 1:
                                LocalDate today = LocalDate.now();
                                String year = String.valueOf(today.getYear());

//                            월을 MM으로 바꿔야함.
                                String month = String.valueOf(today.getMonthValue());
                                PurchaseProductRepository purchaseProductRepository = new PurchaseProductRepository(year, month);
                                purchaseProductRepository.selectPurchaseProduct();

                                System.out.println("수정할 발주 번호(pp_id)를 입력해주세요.");
                                int updateppid = scan.nextInt();
                                System.out.println("수량을 입력해주세요.");
                                int updateppreceivedCount = scan.nextInt();
                                UpdatePurchaseProduct updatePurchaseProduct = new UpdatePurchaseProduct(updateppid, updateppreceivedCount);
                                break;
                            case 2:
                                System.out.println("사업자 번호를 입력해주세요.");
                                String mBusinessID = scan.next();
                                System.out.println("공장 상호명을 입력해주세요.");
                                String mName = scan.next();
                                System.out.println("공장 주소를 입력해주세요.");
                                String mAdress = scan.next();
                                System.out.println("담당자 이름을 입력해주세요.");
                                String mPerson = scan.next();
                                System.out.println("담당자 연락처를 입력해주세요.");
                                String mPhone = scan.next();
//                                선택사항으로 입력 안하는 방법 추가 어떻게?
                                System.out.println("담당자 부서를 입력해주세요.");
                                String mDepartment = scan.next();
                                System.out.println("담당자 직급을 입력해주세요.");
                                String mJobTitle = scan.next();
                                addmanufacturer addmanufacturer = new addmanufacturer(mBusinessID,mName,mAdress,mPerson,mPhone,mDepartment,mJobTitle);
                            case 3:
                                isExist = true;
                                break;
                        }
                        if (isExist) break;
                    }
                case 4:
                    break;
            }
        }
    }
}
