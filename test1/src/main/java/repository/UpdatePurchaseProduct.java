package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePurchaseProduct {
    private static HikariDataSource dataSource = HikariCP.createDataSource();
    String updatePurchaseProductSql = "UPDATE purchaseProduct SET pp_receivedCount = ?  WHERE pp_id = ?";
    String updateProductSql = "UPDATE product SET p_amount = ?  WHERE p_id = ?";

    Connection conn = null;

    public UpdatePurchaseProduct(int updateppId, int updateppReceivedCount) {
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false); //수동 커밋으로 변경

            PreparedStatement pstmt = conn.prepareStatement(updatePurchaseProductSql);
            pstmt.setInt(1, updateppReceivedCount);
            pstmt.setInt(2, updateppId);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(updateProductSql);
            Product product = new Product();
            pstmt.setInt(1,p_amount);
            pstmt.setInt(2,updateppId);



            System.out.println(updateppId + ". 수량 " + updateppReceivedCount + "개로 수정이 완료되었습니다.");
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}