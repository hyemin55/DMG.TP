package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePurchaseProduct {
    private static HikariDataSource dataSource = HikariCP.createDataSource();
    String sql = "UPDATE purchaseproduct SET pp_receivedCount = ?  WHERE pp_id = ?";

    public UpdatePurchaseProduct(int updateppId, int updateppReceivedCount) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, updateppReceivedCount);
            pstmt.setInt(2, updateppId);
            pstmt.executeUpdate();
            System.out.println(updateppId+". 수량 "+updateppReceivedCount+"개로 수정이 완료되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}