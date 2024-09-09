import domain.*;
import repository.*;
import repository.ManufacturerRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {


        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("""
                    1. 발주하기
                    2. 조회하기
                    3. 종료
                    """);
            int input = scan.nextInt();
            if (input == 3) {
                System.out.println("종료되었습니다. 다음에 또 이용해주세요:)");
                break;
            }

            switch (input) {
                case 1:
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
                                        5. 발주내역 삭제
                                        6. 뒤로가기
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
                                        ProductRepository ppppp = new ProductRepository();
                                        ppppp.selectproduct();
                                        System.out.println("조회하실 상품 id을 골라주세요");
                                        int p_id= scan.nextInt();
                                        PP_PnameRepository pp_pnameRepository = new PP_PnameRepository(p_id);
                                        pp_pnameRepository.selectPP_PnameRepository();
                                    case 5:
                                        ProductRepository vvs = new ProductRepository();
                                        vvs.selectproduct();
                                        System.out.println("삭제하실 상품 id을 골라주세요");
                                         p_id= scan.nextInt();
                                        DeleteRepository deleteRepository = new DeleteRepository(p_id);
                                        deleteRepository.delete();

                                    case 6:
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
                            ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
                            manufacturerRepository.selectManufacturer();

                            break;
                        default:
                            System.out.println("원하는 숫자를 입력해주세요.");
                    }
            }
        }
    }
}
