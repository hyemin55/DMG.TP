import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BulkOrder extends OrderTemplate {
    public BulkOrder(int customerId) {
        super(customerId);
    }

    @Override
    protected void processOrder(Connection connection, int productId) throws SQLException {
        String stockCheckQuery = "SELECT p_amount FROM product WHERE p_id = ? FOR UPDATE";
        try (PreparedStatement checkStmt = connection.prepareStatement(stockCheckQuery)) {
            checkStmt.setInt(1, productId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int currentStock = rs.getInt("p_amount");
                    if (currentStock >= 20) { // 대량 주문은 최소 20개가 필요하다고 가정
                        // 재고가 충분하면 대량 주문 삽입 및 재고 감소
                        String orderQuery = "INSERT INTO customerOrders (simulated_customer_id, p_id, quantity, status) VALUES (?, ?, ?, ?)";
                        String updateStockQuery = "UPDATE product SET p_amount = p_amount - 20 WHERE p_id = ?";

                        try (PreparedStatement orderStmt = connection.prepareStatement(orderQuery);
                             PreparedStatement updateStmt = connection.prepareStatement(updateStockQuery)) {

                            orderStmt.setInt(1, customerId);
                            orderStmt.setInt(2, productId);
                            orderStmt.setInt(3, 20);
                            orderStmt.setString(4, "SUCCESS");
                            orderStmt.executeUpdate();

                            updateStmt.setInt(1, productId);
                            updateStmt.executeUpdate();

                            System.out.println("제품 ID : " + productId + "가 주문되었습니다.");
                        }
                    } else {
                        System.out.println("고객 ID : " + customerId + "님이 제품 ID : " + productId + "의 재고수량이 부족으로 인해 주문을 실패하였습니다.");
                        connection.rollback(); // 재고 부족 시 롤백
                    }
                }
            }
        }
    }
}
