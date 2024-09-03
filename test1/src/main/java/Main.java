import domain.Product;
import domain.Wholesaler;
import repository.ProductRepository;
import repository.WholesalerRepository;

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
                    int input2 = scan.nextInt();
                    switch (input2) {
                        case 1:
                            ProductRepository productRepository = new ProductRepository();
                            List<Product> prolist = productRepository.selectproduct();
                            int i = 0;
                            while (true) {
                                if (i == prolist.size()) {
                                    break;
                                }
                                System.out.println(prolist.get(i));
                                i++;
                            }
                            break;
                        case 2:

                            break;
                        case 3:
                            WholesalerRepository wholesalerRepository = new WholesalerRepository();
                            List<Wholesaler> wrlist = wholesalerRepository.selectwholesaler();
                            i = 0;
                            while (true) {
                                if (i == wrlist.size()) {
                                    break;
                                }
                                System.out.println(wrlist.get(i));
                                i++;
                            }

                            break;
                        case 4:

                            break;
                        default:
                            System.out.println("원하는 숫자를 입력해주세요.");
                    }
            }
        }
    }
}
