package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;
import domain.PurchaseProduct;
import domain.PurchaseProductJoinQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseProductRepository {
    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public List<PurchaseProductJoinQuery> selectPurchaseProduct(String year, String month) {
        try (Connection connection = dataSource.getConnection();
        ) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT pp.pp_id, pp.pp_orderDate, pp.pp_receivedDate, p.p_name, pp.pp_costPrice, pp.pp_receivedCount, pp.pp_manufactureDate, pp.pp_expirationDate, p.p_etc\n" +
                    "FROM product p, purchaseProduct pp\n" +
                    "where p.p_id = pp.p_id and pp_orderDate like ?");
            pstmt.setString(1,year + "-" + month + "%");
            ResultSet rs = pstmt.executeQuery();
            List<PurchaseProductJoinQuery> list = new ArrayList<>();
            while (rs.next()) {
                PurchaseProductJoinQuery purchaseProductJoinQuery = new PurchaseProductJoinQuery();
                purchaseProductJoinQuery.setPp_id(rs.getInt("pp_id"));
                purchaseProductJoinQuery.setPp_orderDate(rs.getDate("pp_orderDate"));
                purchaseProductJoinQuery.setPp_receivedDate(rs.getDate("pp_receivedDate"));
                purchaseProductJoinQuery.setPp_costPrice(rs.getInt("pp_costPrice"));
//                purchaseProductJoinQuery.setPp_sellingPrice(rs.getInt("pp_sellingPrice"));
                purchaseProductJoinQuery.setPp_receivedCount(rs.getInt("pp_receivedCount"));
                purchaseProductJoinQuery.setPp_manufactureDate(rs.getDate("pp_manufactureDate"));
                purchaseProductJoinQuery.setPp_expirationDate(rs.getDate("pp_expirationDate"));
                purchaseProductJoinQuery.setP_name(rs.getString("p_name"));
                purchaseProductJoinQuery.setP_etc(rs.getString("p_etc"));
                list.add(purchaseProductJoinQuery);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
