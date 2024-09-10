package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdatePurchaseProduct {
    private static HikariDataSource dataSource = HikariCP.createDataSource();
    String updatePurchaseProductSql = "UPDATE purchaseProduct SET pp_receivedCount = ?  WHERE pp_id = ?";
    String selectPurchaseProductSql = "select p_amount, p_id from purchaseProduct, product where pp_id = ? and purchaseProduct.p_id=product.p_id";
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

            pstmt = conn.prepareStatement(selectPurchaseProductSql);
            pstmt.setInt(1, updateppId);

            List list = new ArrayList();
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs.getInt("p_id"));
            while (rs.next()) {
                Product product = new Product();
                product.setP_id(rs.getInt("p_id"));
                product.setP_amount(rs.getInt("p_amount"));
                list.add(product);
            }

            if (rs.next()) {
                int p_id = rs.getInt("p_id");
                int p_amount = rs.getInt("p_amount");
                int addP_amount = p_amount + updateppReceivedCount;

                pstmt = conn.prepareStatement(updateProductSql);
                pstmt.setInt(1, addP_amount);
                pstmt.setInt(2, p_id);
                pstmt.executeUpdate();
            }
            conn.commit();
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