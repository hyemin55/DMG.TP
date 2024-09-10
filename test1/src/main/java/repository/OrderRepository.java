package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.OrderItemDto;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class OrderRepository {
    private static HikariDataSource dataSource = HikariCP.createDataSource();

    /**
     *
     * @param w_id 도매직원 ID
     * @param m_id 공장직원 ID
     * @param orderItemDtos 주문한 제품ID 및 수량
     */
    public boolean insertOrder(int w_id, int m_id, List<OrderItemDto> orderItemDtos) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        String stockCheckQuery = "select p_amount from product where p_id = ? for update";
        try {
            conn = dataSource.getConnection();

            conn.setAutoCommit(false); // 자동커밋 비활성화 트랜잭션 시작

            /*
            발주날짜 now
            입고날짜 now + 4
            상품판매가격
            제조일자 now - 7
            유통기한 now + 365
             */
            for (OrderItemDto orderItem : orderItemDtos) {
                pstmt = conn.prepareStatement(stockCheckQuery);
                pstmt.setInt(1, orderItem.getP_id());
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    int stockAmount = rs.getInt("p_amount");

                    if (stockAmount - orderItem.getP_amount() < 0) {
                        System.out.println("제품 ID: " + orderItem.getP_id() + "번의 재고 수량이 부족합니다");
                        return false;
                    }
                }


                pstmt = conn.prepareStatement(
                        "insert into purchaseProduct(pp_orderDate, pp_receivedDate, pp_costPrice, pp_sellingPrice, " +
                                "pp_receivedCount, pp_manufactureDate, pp_expirationDate, m_id, w_id, p_id) "
                                + "values (?,?,?,?,?,?,?,?,?,?)"

                );
                pstmt.setDate(1, Date.valueOf(LocalDate.now()));
                pstmt.setDate(2, Date.valueOf(LocalDate.now().plus(4, ChronoUnit.DAYS)));
                int randomCostPrice = new Random().nextInt(1_000_000 / 5_000 - 100_000 / 5_000 + 1) + 100_000 / 5_000 * 5_000;
                pstmt.setInt(3, randomCostPrice);
                pstmt.setInt(4, (int) (randomCostPrice * 1.3));
                pstmt.setDate(6, Date.valueOf(LocalDate.now().minus(7, ChronoUnit.DAYS)));
                pstmt.setDate(7, Date.valueOf(LocalDate.now().plus(1, ChronoUnit.YEARS)));
                pstmt.setInt(8, m_id);
                pstmt.setInt(9, w_id);
                pstmt.setInt(5, orderItem.getP_amount());
                pstmt.setInt(10, orderItem.getP_id());
                pstmt.executeUpdate();

            }

            for (OrderItemDto orderItemDto : orderItemDtos) {
                pstmt = conn.prepareStatement(
                        "update product set p_amount = p_amount - ? where p_id = ?"
                );
                pstmt.setInt(1, orderItemDto.getP_amount());
                pstmt.setInt(2, orderItemDto.getP_id());

                pstmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            if (conn != null) {
                try {
                    conn.rollback(); // 오류 시 롤백
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
