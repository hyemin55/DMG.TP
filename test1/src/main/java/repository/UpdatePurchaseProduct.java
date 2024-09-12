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

    private final int updateppId;
    private final int updateppReceivedCount;

    public UpdatePurchaseProduct(int updateppId, int updateppReceivedCount) {
        this.updateppId = updateppId;
        this.updateppReceivedCount = updateppReceivedCount;
    }

    String selectPurchaseProductSql = "select p.p_amount, pp.pp_receivedCount, p.p_id from purchaseProduct pp, product p where pp.pp_id = ? and pp.p_id = p.p_id";
    String updatePurchaseProductSql = "UPDATE purchaseProduct SET pp_receivedCount = ?  WHERE pp_id = ?";
    String updateProductMinusSql = "UPDATE product SET p_amount = p_amount - ? WHERE p_id = ?";
    String updateProductPlusSql = "UPDATE product SET p_amount = p_amount + ? WHERE p_id = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;

    public boolean updatePurchaseProduct() {
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false); //수동 커밋으로 변경

            pstmt = conn.prepareStatement(selectPurchaseProductSql);
            pstmt.setInt(1, updateppId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int p_id = rs.getInt("p_id");
                int p_amount = rs.getInt("p_amount");
                int pp_receivedCount = rs.getInt("pp_receivedCount");
                System.out.println(pp_receivedCount);
                if (updateppReceivedCount == pp_receivedCount) {
                    System.out.println("수정할 수량이 기존과 동일합니다.");
                    conn.rollback();
                    return true;
                }

                /**
                 * 사용자가 수량 수정하는데
                 * 1. 기존보다 많이 수정하는 경우
                 * - 입력한 수량에서 기존에 있던 수량의 차를 구해야함
                 * - 위에서 구한 수량과 현재 제품수량의 차가 음수가 되는지 확인
                 * - 차를 제품수량에 뺀다
                 * 2. 기존보다 적게 수정하는 경우
                 * - 기존 제품수량에서 입력한 수량을 뺀다
                 * - 제품수량에 더한다
                 */
                boolean isAmountPlus;
                int finalAmount = 0;
                System.out.println(pp_receivedCount);
                if(updateppReceivedCount > pp_receivedCount) {
                    finalAmount = updateppReceivedCount - pp_receivedCount;
                    isAmountPlus = true;
                } else {
                    finalAmount = pp_receivedCount - updateppReceivedCount;
                    isAmountPlus = false;
                }

//                System.out.println("수량의차 " + finalAmount);
//                System.out.println("기존 제품 수량 " + p_amount);
                if(!isAmountPlus && p_amount - finalAmount < 0) {
                    System.out.println("재고수량이 부족합니다");
                    conn.rollback();
                    return false;
                }

//                System.out.println(isAmountPlus);
                if(isAmountPlus){
                    pstmt = conn.prepareStatement(updateProductPlusSql);
                } else {
                    pstmt = conn.prepareStatement(updateProductMinusSql);
                }
                pstmt.setInt(1, finalAmount);
                pstmt.setInt(2, p_id);
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement(updatePurchaseProductSql);
                pstmt.setInt(1, updateppReceivedCount);
                pstmt.setInt(2, updateppId);
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println(updateppId + ". 수량 " + updateppReceivedCount + "개로 수정이 완료되었습니다.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return false;
    }
}