import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public abstract class OrderTemplate implements Runnable {

    private static HikariDataSource dataSource;
    private static final Semaphore SEMAPHORE = new Semaphore(5);
    protected final int customerId;

    public OrderTemplate(int customerId) {
        this.customerId = customerId;
    }

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/alcohol_retail_store");
        config.setUsername("root");
        config.setPassword("1234");
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void run() {
        try {
            SEMAPHORE.acquire();
            System.out.println("고객 " + customerId + "님이 로그인 하였습니다.");
            try (Connection connection = getConnection()){
                connection.setAutoCommit(false); // 트랜잭션 시작

                // 1. 제품리스트 가져오기
                List<Integer> productList = getProductList(connection);

                // 2. 랜덤 제품 선택
                int selectedProduct = selectProduct(productList);
                System.out.println("고객 " + customerId + "님이 " + selectedProduct + "번 제품을 선택하였습니다.");

                // 3. 주문 처리 : 템플릿 메서드를 통해 하위 클래스에서 구현
                processOrder(connection, selectedProduct);

                connection.commit(); // 트랜잭션 커밋

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SEMAPHORE.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getProductList(Connection connection) throws SQLException {
        List<Integer> productList = new ArrayList<>();
        String query = "select p_id from product";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productList.add(rs.getInt("p_id"));
            }
        }
        return productList;
    }

    private int selectProduct(List<Integer> productList) {
        Random random = new Random();
        return productList.get(random.nextInt(productList.size()));
    }

    protected abstract void processOrder(Connection connection, int selectedProduct) throws SQLException;

    public static void closeDataSource() {
        dataSource.close();
    }
}
