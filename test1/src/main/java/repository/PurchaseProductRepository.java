package repository;

import domain.PurchaseProductJoinQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseProductRepository extends JDBCTemplate {

    private final String year;
    private final String month;

    public PurchaseProductRepository(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public void selectPurchaseProduct() {
       execute("SELECT pp.pp_id, pp.pp_orderDate, pp.pp_receivedDate, p.p_name, pp.pp_costPrice, pp.pp_receivedCount, pp.pp_manufactureDate, pp.pp_expirationDate, p.p_amount, p.p_etc\n" +
               "FROM product p, purchaseProduct pp\n" +
               "where p.p_id = pp.p_id and pp_orderDate like ?");
    }

    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {
        List<PurchaseProductJoinQuery> list = new ArrayList<>();
        while (rs.next()) {

            PurchaseProductJoinQuery purchaseProductJoinQuery = new PurchaseProductJoinQuery();
            purchaseProductJoinQuery.setPp_id(rs.getInt("pp_id"));
            purchaseProductJoinQuery.setPp_orderDate(rs.getDate("pp_orderDate"));
            purchaseProductJoinQuery.setPp_receivedDate(rs.getDate("pp_receivedDate"));
            purchaseProductJoinQuery.setPp_costPrice(rs.getInt("pp_costPrice"));
            purchaseProductJoinQuery.setP_amount(rs.getInt("p_amount"));
            purchaseProductJoinQuery.setPp_receivedCount(rs.getInt("pp_receivedCount"));
            purchaseProductJoinQuery.setPp_manufactureDate(rs.getDate("pp_manufactureDate"));
            purchaseProductJoinQuery.setPp_expirationDate(rs.getDate("pp_expirationDate"));
            purchaseProductJoinQuery.setP_name(rs.getString("p_name"));
            purchaseProductJoinQuery.setP_etc(rs.getString("p_etc"));
            list.add(purchaseProductJoinQuery);
        }
        list.stream().forEach(System.out::println);
    }

    @Override
    protected void setParameter(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1,year + "-" + month + "%");
    }
}
