package repository;


import domain.PurchaseProductJoinQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteRepository extends JDBCTemplate {

    private int pp_id;

    public DeleteRepository(int pp_id) {
        this.pp_id = pp_id;
    }

    public void delete(){
        execute("""
                DELETE FROM purchaseProduct
                WHERE pp_id = ?
                """);
    }

    protected void handleResultSet(ResultSet rs) throws SQLException{
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

    protected void setParameter(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, String.valueOf(pp_id));
    }
}
