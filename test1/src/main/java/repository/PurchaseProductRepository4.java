package repository;

import domain.SelectPurchaseProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseProductRepository4 extends JDBCTemplate {

    private final String m_name;

    public PurchaseProductRepository4(String m_name) {
        this.m_name = m_name;
    }

    public void selectPurchaseProduct4() {
        execute("select * from selectPurchaseProduct where m_name like ?");
    }

    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {
        List<SelectPurchaseProduct > list = new ArrayList<>();
        while (rs.next()) {
            SelectPurchaseProduct selectPurchaseProduct = new SelectPurchaseProduct();
            selectPurchaseProduct.setPp_id(rs.getInt("pp_id"));
            selectPurchaseProduct.setPp_orderDate(rs.getDate("pp_orderDate"));
            selectPurchaseProduct.setPp_receivedDate(rs.getDate("pp_receivedDate"));
            selectPurchaseProduct.setM_name(rs.getString("m_name"));
            selectPurchaseProduct.setW_name(rs.getString("w_name"));
            selectPurchaseProduct.setP_kind(rs.getInt("p_kind"));
            selectPurchaseProduct.setP_brand(rs.getString("p_brand"));
            selectPurchaseProduct.setP_capacity(rs.getInt("p_capacity"));
            selectPurchaseProduct.setPp_costPrice(rs.getInt("pp_costPrice"));
            selectPurchaseProduct.setP_name(rs.getString("p_name"));
            selectPurchaseProduct.setPp_receivedCount(rs.getInt("pp_receivedCount"));
            list.add(selectPurchaseProduct);
        }
        list.stream().forEach(System.out::println);
    }

    @Override
    protected void setParameter(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1,"%"+ m_name+"%");
    }
}
