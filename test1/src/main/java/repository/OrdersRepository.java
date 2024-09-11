//package repository;
//
//import domain.Product;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class OrdersRepository extends JDBCTemplate{
//
//    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
//
//    public OrdersRepository() {
//
//    }
//
//    public void insertOrders(Date orderDate, Date receivedDate,
//                             int costPrice, int sellingPrice,
//                             int receivedCount, Date manufactureDate,
//                             Date expirationDate, int mId,
//                             int wId, int pId) {
//        execute("insert into purchaseproduct(pp_orderDate,pp_receivedDate,pp_costPrice,pp_sellingPrice,pp_receivedCount,pp_manufactureDate,pp_expirationDate,m_id,w_id,p_id)values \n" +
//                "(?,?,?,?,?,?,?,?,?,?);");
//    }
//
//    @Override
//    protected void handleResultSet(ResultSet rs) throws SQLException {
//        List<insertOrders> list = new ArrayList<>();
//        while (rs.next()) {
//            Product product = new Product();
//            pstmt.
//        }
//        pstmt.executeUpdate();
//    }
//
//    @Override
//    protected void setParameter(PreparedStatement pstmt) throws SQLException {
//        pstmt.setString();
//    }
//}
